package co.edu.corhuila.barbersaas.schedule.observability;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/** Copies the current trace to every outbound call as a new client span of the same trace. */
@Component
public class TraceparentInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        TraceContext current = RequestContext.trace();
        if (current != null) {
            request.getHeaders().set(TraceContextFilter.TRACEPARENT, current.child().traceparent());
        }
        String correlationId = RequestContext.correlationId();
        if (correlationId != null) {
            request.getHeaders().set(TraceContextFilter.CORRELATION_ID, correlationId);
        }
        return execution.execute(request, body);
    }
}
