package fr.sipios.springmeetup.domain;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;
import fr.sipios.springmeetup.infrastructure.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    log.info("Starting CustomerService");
    this.customerRepository = customerRepository;
  }

  private static void assertNameIsValid(final String firstName) {
    if (firstName == null || firstName.isBlank()) {
      throw new IllegalArgumentException("First name cannot be empty");
    }
    final String regex = "^[a-zA-Z]*$";
    if (!firstName.matches(regex)) {
      throw new IllegalArgumentException("First name must contain only letters");
    }
  }

  private static void assertCustomerIsValid(CustomerEntity customerEntity) {
    if (customerEntity == null) {
      throw new IllegalArgumentException("Customer cannot be null");
    }
    assertNameIsValid(customerEntity.getFirstName());
    assertNameIsValid(customerEntity.getLastName());
  }

  public List<CustomerEntity> getInfo() {
    return customerRepository.getAllCustomers();
  }

  public CustomerEntity createCustomer(final CustomerEntity customerEntity) {
    assertCustomerIsValid(customerEntity);
    return customerRepository.createCustomer(customerEntity);
  }
}
