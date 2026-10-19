package co.edu.corhuila.barbersaas.schedule.observability;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Reads traceparent and X-Correlation-Id, or creates them, opens the server span of
 * schedule-api and returns both headers in the response.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceContextFilter extends OncePerRequestFilter {

    public static final String TRACEPARENT = "traceparent";
    public static final String CORRELATION_ID = "X-Correlation-Id";

    private final StructuredLogger log;

    public TraceContextFilter(StructuredLogger log) {
        this.log = log;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        long start = System.nanoTime();
        TraceContext span = TraceContext.parse(request.getHeader(TRACEPARENT))
                .map(TraceContext::child)
                .orElseGet(TraceContext::newRoot);
        String correlationId = request.getHeader(CORRELATION_ID);
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }
        RequestContext.set(span, correlationId);
        response.setHeader(TRACEPARENT, span.traceparent());
        response.setHeader(CORRELATION_ID, correlationId);
        try {
            chain.doFilter(request, response);
        } finally {
            long durationMs = (System.nanoTime() - start) / 1_000_000;
            log.info("http.request.completed", request.getMethod() + " " + request.getRequestURI(),
                    Map.of("http_status", response.getStatus(), "duration_ms", durationMs));
            RequestContext.clear();
        }
    }
}
