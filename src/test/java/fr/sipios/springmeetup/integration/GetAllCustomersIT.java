package fr.sipios.springmeetup.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class GetAllCustomersIT extends IntegrationTest {

  @Test
  void should_get_all_customers() throws Exception {
    log.info("should_get_all_customers");
    mockMvc.perform(get("/customers"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers").isArray());
  }
}
