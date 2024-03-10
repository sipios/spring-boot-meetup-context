package fr.sipios.springmeetup.domain;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;
import fr.sipios.springmeetup.infrastructure.CustomerEventLogger;
import fr.sipios.springmeetup.infrastructure.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final CustomerEventLogger customerEventLogger;

  public CustomerService(CustomerRepository customerRepository, CustomerEventLogger customerEventLogger) {
    log.info("Starting CustomerService");
    this.customerEventLogger = customerEventLogger;
    this.customerRepository = customerRepository;
  }

  private static void assertNameIsValid(final String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("First name cannot be empty");
    }
    final String regex = "^[a-zA-Z]*$";
    if (!name.matches(regex)) {
      throw new InvalidName(name);
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
    final CustomerEntity createdCustomer = customerRepository.createCustomer(customerEntity);
    customerEventLogger.logCustomerEvent(createdCustomer);
    return createdCustomer;
  }
}
