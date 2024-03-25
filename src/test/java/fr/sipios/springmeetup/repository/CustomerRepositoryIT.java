package fr.sipios.springmeetup.repository;

import fr.sipios.springmeetup.RepositoryIT;
import fr.sipios.springmeetup.customer.CustomerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class CustomerRepositoryIT extends RepositoryIT {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void should_find_all_customers() {
    assertEquals(Collections.emptyList(), customerRepository.findAll(), "Should return an empty list of customers");
  }
}
