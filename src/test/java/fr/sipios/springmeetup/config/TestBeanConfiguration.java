package fr.sipios.springmeetup.config;

import fr.sipios.springmeetup.customer.CustomerEventLogger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestBeanConfiguration {
  @MockBean
  private CustomerEventLogger customerEventLogger;
}
