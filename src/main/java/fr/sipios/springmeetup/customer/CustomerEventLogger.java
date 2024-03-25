package fr.sipios.springmeetup.customer;

import fr.sipios.springmeetup.utils.MeasureTime;
import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerEventLogger {

  public CustomerEventLogger() {
    MeasureTime.start(this.getClass().getSimpleName());
    sleep(10);
    log.info("CustomerEventLogger created");
  }

  @PostConstruct
  public void postConstruct() {
    MeasureTime.stop(this.getClass().getSimpleName());
  }

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
