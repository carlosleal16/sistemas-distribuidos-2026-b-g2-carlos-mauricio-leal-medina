package co.edu.corhuila.barbersaas.schedule.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

/**
 * Negative authorization test of barber-saas-docs#163 (STRIDE E, risk 6): only the owner of the
 * barber's barbershop may change the weekly schedule with PUT /api/v1/barber-schedules/{barberId}.
 */
class WeeklyScheduleAuthorizationHttpTest extends HttpTest {

    private static final String WEEK = "{\"slots\":[{\"dayOfWeek\":1,\"startTime\":\"08:00\",\"endTime\":\"12:00\"}]}";
    private static final String OTHER_WEEK = "{\"slots\":[{\"dayOfWeek\":2,\"startTime\":\"14:00\",\"endTime\":\"15:00\"}]}";

    private final UUID shop = UUID.randomUUID();
    private final UUID otherShop = UUID.randomUUID();
    private final UUID barberUser = UUID.randomUUID();
    private final BarbershopApiStub.Barber barber = BARBERSHOP_API.barber(shop, barberUser);
    private final String owner = bearer("ADMIN_BARBERSHOP", shop);

    private void ownerSetsTheWeek() throws Exception {
        http.perform(put("/api/v1/barber-schedules/" + barber.id()).header("Authorization", owner)
                        .contentType(MediaType.APPLICATION_JSON).content(WEEK))
                .andExpect(status().isOk());
    }

    @Test
    void the_owner_of_another_barbershop_gets_404_and_the_week_is_unchanged() throws Exception {
        ownerSetsTheWeek();

        http.perform(put("/api/v1/barber-schedules/" + barber.id())
                        .header("Authorization", bearer("ADMIN_BARBERSHOP", otherShop))
                        .contentType(MediaType.APPLICATION_JSON).content(OTHER_WEEK))
                .andExpect(status().isNotFound());

        http.perform(get("/api/v1/barber-schedules/" + barber.id()).header("Authorization", owner))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slots.length()").value(1))
                .andExpect(jsonPath("$.slots[0].startTime").value("08:00"));
    }

    @Test
    void the_barber_and_a_client_of_the_same_barbershop_get_403() throws Exception {
        ownerSetsTheWeek();

        http.perform(put("/api/v1/barber-schedules/" + barber.id())
                        .header("Authorization", bearer(barberUser, "BARBER", shop))
                        .contentType(MediaType.APPLICATION_JSON).content(OTHER_WEEK))
                .andExpect(status().isForbidden());
        http.perform(put("/api/v1/barber-schedules/" + barber.id())
                        .header("Authorization", bearer("CLIENT", shop))
                        .contentType(MediaType.APPLICATION_JSON).content(OTHER_WEEK))
                .andExpect(status().isForbidden());
    }

    @Test
    void a_request_without_a_token_gets_401() throws Exception {
        http.perform(put("/api/v1/barber-schedules/" + barber.id())
                        .contentType(MediaType.APPLICATION_JSON).content(OTHER_WEEK))
                .andExpect(status().isUnauthorized());
    }
}
