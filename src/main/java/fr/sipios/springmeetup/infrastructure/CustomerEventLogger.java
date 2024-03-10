package fr.sipios.springmeetup.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerEventLogger {

  public void logCustomerEvent(CustomerEntity customerEntity) {
    log.info("Customer event: {}", customerEntity);
  }
}
