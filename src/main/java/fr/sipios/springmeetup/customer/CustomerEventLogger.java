package fr.sipios.springmeetup.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class CustomerEventLogger {
  public void logNewCustomer(Customer customer) {
    log.info("New customer created: {}", customer);
  }

  public void logCustomerDeleted(Long customerId) {
    log.info("Customer deleted: {}", customerId);
  }

  public void logCustomerAccess(Long customerId) {
    log.info("Customer accessed: {}", customerId);
  }

  public void logAllCustomersAccess() {
    log.info("All customers accessed");
  }
}
