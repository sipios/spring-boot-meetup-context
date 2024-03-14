package fr.sipios.springmeetup.app1.infrastructure;

import fr.sipios.springmeetup.utils.MeasureTestTimeUtils;
import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CustomerRepository {

  private final CustomerJpaRepository customerJpaRepository;

  public CustomerRepository(CustomerJpaRepository customerJpaRepository) {
    MeasureTestTimeUtils.registerStartTime(this.getClass().getSimpleName());
    log.info("Sleeping during 5 secs");
    sleep(2);
    this.customerJpaRepository = customerJpaRepository;
  }

  public List<CustomerEntity> getAllCustomers() {
    return customerJpaRepository.findAll();
  }

  public CustomerEntity createCustomer(final CustomerEntity customerEntity) {
    return customerJpaRepository.save(customerEntity);
  }

  @PostConstruct
  private void postConstruct() {
    log.info("PostConstruct method");
    MeasureTestTimeUtils.registerEndTime(this.getClass().getSimpleName());
  }
}
