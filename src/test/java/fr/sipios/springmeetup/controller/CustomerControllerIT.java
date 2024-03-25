package fr.sipios.springmeetup.controller;

import fr.sipios.springmeetup.customer.CustomerController;
import fr.sipios.springmeetup.customer.CustomerNotFound;
import fr.sipios.springmeetup.customer.CustomerService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @Test
  void should_be_not_found_when_customer_does_not_exist() throws Exception {
    doThrow(new CustomerNotFound(404L)).when(customerService).getCustomer(404L);

    mockMvc.perform(get("/customers/404"))
        .andExpect(status().isNotFound());
  }
}
