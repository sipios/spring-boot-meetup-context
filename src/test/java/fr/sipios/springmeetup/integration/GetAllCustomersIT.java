package fr.sipios.springmeetup.integration;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetAllCustomersIT extends IntegrationTest {

  @Test
  void should_get_all_customers() throws Exception {
    mockMvc.perform(get("/customers"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers").isArray());
  }
}
