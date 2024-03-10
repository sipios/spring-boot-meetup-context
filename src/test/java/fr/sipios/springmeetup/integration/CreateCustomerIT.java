package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.infrastructure.CustomerEventLogger;
import fr.sipios.springmeetup.infrastructure.CustomerJpaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCustomerIT extends IntegrationTest {

  @Autowired
  private CustomerJpaRepository repository;

  @MockBean
  private CustomerEventLogger customerEventLogger;

  @Test
  void should_create_customer() throws Exception {
    givenMyDatabaseIsEmpty();
    doNothing().when(customerEventLogger).logCustomerEvent(any());

    whenICreateACustomer();

    thenMyDatabaseContainsOneCustomer();
  }

  private void thenMyDatabaseContainsOneCustomer() {
    assertThat(repository.findAll().size()).isEqualTo(1);
  }

  private void whenICreateACustomer() throws Exception {
    mockMvc.perform(
            post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"John\", \"lastName\": \"Doe\"}")
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"));
  }

  private void givenMyDatabaseIsEmpty() {
    assertThat(repository.findAll().size()).isEqualTo(0);
  }

}
