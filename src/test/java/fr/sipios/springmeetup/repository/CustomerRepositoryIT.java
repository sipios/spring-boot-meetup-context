package fr.sipios.springmeetup.repository;

import fr.sipios.springmeetup.RepositoryIT;
import fr.sipios.springmeetup.customer.CustomerRepository;
import fr.sipios.springmeetup.customer.CustomerService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

public class CustomerRepositoryIT extends RepositoryIT {

  @Autowired
  private CustomerService customerService;

  @Test
  void should_find_all_customers() {
    assertEquals(Collections.emptyList(), customerService.getCustomers());
  }
}
