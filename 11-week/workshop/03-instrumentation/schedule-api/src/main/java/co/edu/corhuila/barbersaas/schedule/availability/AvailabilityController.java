package co.edu.corhuila.barbersaas.schedule.availability;

import co.edu.corhuila.barbersaas.schedule.observability.StructuredLogger;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Minimal GET /api/v1/availability: reads the barber, then returns the free slots. */
@RestController
public class AvailabilityController {

    private final BarbershopClient barbershop;
    private final StructuredLogger log;
    private final MeterRegistry registry;
    private final Timer latency;

    public AvailabilityController(BarbershopClient barbershop, StructuredLogger log, MeterRegistry registry) {
        this.barbershop = barbershop;
        this.log = log;
        this.registry = registry;
        this.latency = Timer.builder("schedule.availability.request.duration")
                .description("Latency of GET /api/v1/availability")
                .serviceLevelObjectives(Duration.ofMillis(50), Duration.ofMillis(100), Duration.ofMillis(250),
                        Duration.ofMillis(500), Duration.ofSeconds(1), Duration.ofSeconds(3))
                .register(registry);
    }

    @GetMapping("/api/v1/availability")
    public Map<String, Object> availability(@RequestParam String barberId, @RequestParam String serviceId,
                                            @RequestParam String date) {
        Timer.Sample sample = Timer.start(registry);
        String outcome = "error";
        try {
            barbershop.barber(barberId);
            List<String> slots = List.of("14:30", "15:00");
            outcome = "success";
            log.info("availability.computed", "free slots computed",
                    Map.of("barber_id", barberId, "service_id", serviceId, "slots", slots.size()));
            return Map.of("barberId", barberId, "date", date, "slots", slots);
        } finally {
            sample.stop(latency);
            Counter.builder("schedule.availability.requests").tag("outcome", outcome).register(registry).increment();
        }
    }
}
