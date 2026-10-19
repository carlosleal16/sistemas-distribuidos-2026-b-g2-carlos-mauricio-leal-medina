package co.edu.corhuila.barbersaas.schedule.observability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Writes one JSON line per event to stdout, in the CP2 log contract format. */
@Component
public class StructuredLogger {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String service;
    private final String version;
    private final String environment;

    public StructuredLogger(@Value("${spring.application.name}") String service,
                            @Value("${app.version}") String version,
                            @Value("${app.environment}") String environment) {
        this.service = service;
        this.version = version;
        this.environment = environment;
    }

    public void info(String event, String message, Map<String, Object> fields) {
        write("INFO", event, message, fields);
    }

    public void warn(String event, String message, Map<String, Object> fields) {
        write("WARN", event, message, fields);
    }

    public void error(String event, String message, Map<String, Object> fields) {
        write("ERROR", event, message, fields);
    }

    private void write(String level, String event, String message, Map<String, Object> fields) {
        Map<String, Object> line = new LinkedHashMap<>();
        line.put("timestamp", Instant.now().toString());
        line.put("level", level);
        line.put("service", service);
        line.put("version", version);
        line.put("environment", environment);
        TraceContext trace = RequestContext.trace();
        line.put("trace_id", trace == null ? null : trace.traceId());
        line.put("span_id", trace == null ? null : trace.spanId());
        line.put("correlation_id", RequestContext.correlationId());
        line.put("event", event);
        line.put("message", message);
        line.putAll(fields);
        try {
            System.out.println(mapper.writeValueAsString(line));
        } catch (JsonProcessingException e) {
            System.out.println("{\"level\":\"ERROR\",\"event\":\"log.serialization.failed\"}");
        }
    }
}
