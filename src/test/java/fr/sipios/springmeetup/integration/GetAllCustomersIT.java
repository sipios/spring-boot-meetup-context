package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.config.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetAllCustomersIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_retrieve_all_customers() throws Exception {
    mockMvc.perform(get("/customers")
            .contentType("application/json"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers").isArray());
  }
}
