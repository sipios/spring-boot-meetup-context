package fr.sipios.springmeetup.integration;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomersControlIT extends IntegrationTest {

  @Test
  void should_return_400_when_first_name_is_not_valid() throws Exception {
    mockMvc.perform(
            post("/customers")
                .contentType("application/json")
                .content("{\"firstName\": \"&&&\", \"lastName\": \"Doe\"}"))
        .andExpect(status().isBadRequest());
  }
}
