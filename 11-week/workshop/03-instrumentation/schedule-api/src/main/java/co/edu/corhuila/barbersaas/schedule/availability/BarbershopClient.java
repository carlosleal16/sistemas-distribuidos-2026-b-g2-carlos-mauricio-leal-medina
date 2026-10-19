package co.edu.corhuila.barbersaas.schedule.availability;

import co.edu.corhuila.barbersaas.schedule.observability.StructuredLogger;
import co.edu.corhuila.barbersaas.schedule.observability.TraceparentInterceptor;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/** Outbound hop 5 of the journey: schedule-api reads the barber from barbershop-api. */
@Component
public class BarbershopClient {

    private final RestClient client;
    private final StructuredLogger log;

    public BarbershopClient(RestClient.Builder builder, TraceparentInterceptor interceptor,
                            @Value("${barbershop.base-url}") String baseUrl, StructuredLogger log) {
        this.client = builder.baseUrl(baseUrl).requestInterceptor(interceptor).build();
        this.log = log;
    }

    public String barber(String barberId) {
        long start = System.nanoTime();
        String body;
        try {
            body = client.get().uri("/api/v1/barbers/{id}", barberId).retrieve().body(String.class);
        } catch (RestClientException e) {
            log.error("downstream.request.failed", "barbershop-api GET /api/v1/barbers/{id} failed",
                    Map.of("downstream", "barbershop-api", "barber_id", barberId,
                            "duration_ms", (System.nanoTime() - start) / 1_000_000));
            throw e;
        }
        log.info("downstream.request.completed", "barbershop-api GET /api/v1/barbers/{id}",
                Map.of("downstream", "barbershop-api", "barber_id", barberId,
                        "duration_ms", (System.nanoTime() - start) / 1_000_000));
        return body;
    }
}
