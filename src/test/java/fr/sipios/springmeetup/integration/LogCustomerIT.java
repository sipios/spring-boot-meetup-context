package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;
import fr.sipios.springmeetup.infrastructure.CustomerEventLogger;
import fr.sipios.springmeetup.infrastructure.CustomerJpaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LogCustomerIT {

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:15-alpine"
  );

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CustomerJpaRepository repository;
  @SpyBean
  private CustomerEventLogger customerEventLogger;

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

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
