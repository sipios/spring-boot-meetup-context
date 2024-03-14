package fr.sipios.springmeetup.infrastructure;

import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerRepository {

  private final CustomerJpaRepository customerJpaRepository;

  public CustomerRepository(CustomerJpaRepository customerJpaRepository) {
    log.info("Sleeping during 5 secs");
    sleep(5);
    this.customerJpaRepository = customerJpaRepository;
  }

  public List<CustomerEntity> getAllCustomers() {
    return customerJpaRepository.findAll();
  }

  public CustomerEntity createCustomer(final CustomerEntity customerEntity) {
    return customerJpaRepository.save(customerEntity);
  }
}
