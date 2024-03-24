package fr.sipios.springmeetup.controller;

import fr.sipios.springmeetup.ControllerIT;
import fr.sipios.springmeetup.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerIT extends ControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_be_not_found_when_customer_does_not_exist() throws Exception {
    mockMvc.perform(get("/customers/404"))
        .andExpect(status().isNotFound());
  }
}
