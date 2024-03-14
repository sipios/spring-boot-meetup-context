package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.app1.infrastructure.CustomerEntity;
import fr.sipios.springmeetup.app1.infrastructure.CustomerEventLogger;
import fr.sipios.springmeetup.app1.infrastructure.CustomerJpaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class LogCustomerIT extends IntegrationTest {

  @MockBean
  private CustomerJpaRepository repository;
  @SpyBean
  private CustomerEventLogger customerEventLogger;

  @Test
  void should_create_customer() throws Exception {
    givenMyDatabaseIsEmpty();
    final CustomerEntity newCustomer = new CustomerEntity("John", "Doe");
    doReturn(newCustomer).when(repository).save(any());

    whenICreateACustomer();

    thenMyCustomerShouldBeLogged(newCustomer);
  }

  private void thenMyCustomerShouldBeLogged(final CustomerEntity newCustomer) {
    verify(customerEventLogger).logCustomerEvent(newCustomer);
  }

  private void whenICreateACustomer() throws Exception {
    mockMvc.perform(
        post("/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"firstName\": \"John\", \"lastName\": \"Doe\"}")
    ).andExpect(status().isCreated());
  }

  private void givenMyDatabaseIsEmpty() {
    assertThat(repository.findAll().size()).isEqualTo(0);
  }
}
