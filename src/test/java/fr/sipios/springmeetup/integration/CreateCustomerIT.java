package fr.sipios.springmeetup.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateCustomerIT {

  @Autowired
  private MockMvc mockMvc;

  private static final String CREATE_CUSTOMER_REQUEST = """
      {
         "name": "John Doe",
         "email": "john@mail.com",
         "password": "password123",
         "role": "USER",
         "enabled": true
       }
       """;

  @Test
  void should_create_customer() throws Exception {
    mockMvc.perform(post("/customers")
        .contentType("application/json")
        .content(CREATE_CUSTOMER_REQUEST))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.name").value("John Doe"))
        .andExpect(jsonPath("$.email").value("john@mail.com"))
        .andExpect(jsonPath("$.role").value("USER"))
        .andExpect(jsonPath("$.enabled").value(true));
  }
}
