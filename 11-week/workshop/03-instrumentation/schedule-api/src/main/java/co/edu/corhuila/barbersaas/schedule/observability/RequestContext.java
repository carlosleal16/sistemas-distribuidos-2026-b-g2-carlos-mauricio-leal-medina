package co.edu.corhuila.barbersaas.schedule.observability;

/** Trace context and X-Correlation-Id of the request being served by this thread. */
public final class RequestContext {

    private static final ThreadLocal<TraceContext> TRACE = new ThreadLocal<>();
    private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<>();

    private RequestContext() {
    }

    static void set(TraceContext trace, String correlationId) {
        TRACE.set(trace);
        CORRELATION_ID.set(correlationId);
    }

    public static TraceContext trace() {
        return TRACE.get();
    }

    public static String correlationId() {
        return CORRELATION_ID.get();
    }

    static void clear() {
        TRACE.remove();
        CORRELATION_ID.remove();
    }
}
