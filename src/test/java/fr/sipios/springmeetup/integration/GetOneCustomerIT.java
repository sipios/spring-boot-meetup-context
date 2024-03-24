package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql("classpath:db/get_one_customer.sql")
public class GetOneCustomerIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_retrieve_one_customer() throws Exception {
    mockMvc.perform(get("/customers/202")
        .contentType("application/json"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.name").value("Toto"))
        .andExpect(jsonPath("$.email").value("toto@mail.com"))
        .andExpect(jsonPath("$.password").value("password123"))
        .andExpect(jsonPath("$.role").value("USER"))
        .andExpect(jsonPath("$.enabled").value(true))
    ;
  }
}
