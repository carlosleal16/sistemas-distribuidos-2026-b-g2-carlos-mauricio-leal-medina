package co.edu.corhuila.barbersaas.schedule.observability;

import java.security.SecureRandom;
import java.util.HexFormat;
import java.util.Optional;
import java.util.regex.Pattern;

/** W3C trace context of one span: 00-{trace_id}-{span_id}-{flags}. */
public record TraceContext(String traceId, String spanId, String parentSpanId) {

    private static final Pattern TRACEPARENT =
            Pattern.compile("^00-([0-9a-f]{32})-([0-9a-f]{16})-[0-9a-f]{2}$");
    private static final SecureRandom RANDOM = new SecureRandom();

    public static Optional<TraceContext> parse(String header) {
        if (header == null) {
            return Optional.empty();
        }
        var matcher = TRACEPARENT.matcher(header.trim());
        if (!matcher.matches() || matcher.group(1).equals("0".repeat(32))) {
            return Optional.empty();
        }
        return Optional.of(new TraceContext(matcher.group(1), matcher.group(2), null));
    }

    public static TraceContext newRoot() {
        return new TraceContext(randomHex(16), randomHex(8), null);
    }

    /** A new span of the same trace, whose parent is this span. */
    public TraceContext child() {
        return new TraceContext(traceId, randomHex(8), spanId);
    }

    public String traceparent() {
        return "00-" + traceId + "-" + spanId + "-01";
    }

    private static String randomHex(int bytes) {
        byte[] buffer = new byte[bytes];
        RANDOM.nextBytes(buffer);
        return HexFormat.of().formatHex(buffer);
    }
}
