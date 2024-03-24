package fr.sipios.springmeetup.integration;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllCustomersIT {

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
