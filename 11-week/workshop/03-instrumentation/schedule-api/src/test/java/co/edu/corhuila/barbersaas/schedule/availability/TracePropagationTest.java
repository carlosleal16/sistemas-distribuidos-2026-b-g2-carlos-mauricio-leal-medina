package co.edu.corhuila.barbersaas.schedule.availability;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@AutoConfigureObservability
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TracePropagationTest {

    private static final String TRACE_ID = "7d3f0c9a1e5b4f2a8c6d0e1f2a3b4c5d";
    private static final String INCOMING = "00-" + TRACE_ID + "-2b3c4d5e6f708192-01";
    private static final AtomicReference<String> receivedByBarbershop = new AtomicReference<>();
    private static final HttpServer barbershopApi = startFakeBarbershopApi();

    @Autowired
    private TestRestTemplate rest;

    @DynamicPropertySource
    static void barbershopUrl(DynamicPropertyRegistry registry) {
        registry.add("barbershop.base-url", () -> "http://localhost:" + barbershopApi.getAddress().getPort());
    }

    @AfterAll
    static void stop() {
        barbershopApi.stop(0);
    }

    @Test
    void traceIdReceivedIsTheTraceIdSentToBarbershopApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("traceparent", INCOMING);
        ResponseEntity<String> response = rest.exchange(
                "/api/v1/availability?barberId=3fa85f64&serviceId=7c9e6679&date=2026-10-20",
                HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        String downstream = receivedByBarbershop.get();
        assertThat(downstream).startsWith("00-" + TRACE_ID + "-");
        assertThat(downstream).isNotEqualTo(INCOMING);
        assertThat(response.getHeaders().getFirst("traceparent")).contains(TRACE_ID);

        String metrics = rest.getForObject("/actuator/prometheus", String.class);
        assertThat(metrics).contains("schedule_availability_requests_total{outcome=\"success\"}");
        assertThat(metrics).contains("schedule_availability_request_duration_seconds_bucket");
    }

    private static HttpServer startFakeBarbershopApi() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            server.createContext("/api/v1/barbers/", exchange -> {
                receivedByBarbershop.set(exchange.getRequestHeaders().getFirst("traceparent"));
                byte[] body = "{\"id\":\"3fa85f64\"}".getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, body.length);
                exchange.getResponseBody().write(body);
                exchange.close();
            });
            server.start();
            return server;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
