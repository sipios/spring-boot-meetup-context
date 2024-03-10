package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.infrastructure.CustomerEventLogger;
import fr.sipios.springmeetup.infrastructure.CustomerJpaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CreateCustomerIT {

  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:15-alpine"
  );

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CustomerJpaRepository repository;

  @MockBean
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
