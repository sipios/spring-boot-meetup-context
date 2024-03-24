package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.IntegrationTest;
import fr.sipios.springmeetup.customer.CustomerEventLogger;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCustomerIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerEventLogger customerEventLogger;

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
    doNothing().when(customerEventLogger).logNewCustomer(ArgumentMatchers.any());

    mockMvc.perform(post("/customers")
        .contentType("application/json")
        .content(CREATE_CUSTOMER_REQUEST))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.name").value("John Doe"))
        .andExpect(jsonPath("$.email").value("john@mail.com"))
        .andExpect(jsonPath("$.role").value("USER"))
        .andExpect(jsonPath("$.enabled").value(true));

    verify(customerEventLogger).logNewCustomer(ArgumentMatchers.any());
  }
}
