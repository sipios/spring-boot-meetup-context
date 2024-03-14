package fr.sipios.springmeetup.app1.api;

import fr.sipios.springmeetup.app1.domain.CustomerService;
import fr.sipios.springmeetup.app1.infrastructure.CustomerEntity;
import fr.sipios.springmeetup.utils.MeasureTestTimeUtils;
import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/customers")
@Slf4j
public class CustomersController {


  private final CustomerService customerService;

  public CustomersController(final CustomerService customerService) {
    MeasureTestTimeUtils.registerStartTime(this.getClass().getSimpleName());
    log.info("Sleeping during 5 secs");
    sleep(2);
    this.customerService = customerService;
  }

  @PostConstruct
  private void postConstruct() {
    log.info("PostConstruct method");
    MeasureTestTimeUtils.registerEndTime(this.getClass().getSimpleName());
  }

  @GetMapping
  public ResponseEntity<CustomersDto> getAll() {
    final List<CustomerEntity> entities = customerService.getInfo();
    final CustomersDto customersDto = CustomersDto.from(entities);

    return ResponseEntity.ok()
        .body(customersDto);
  }

  @PostMapping
  public ResponseEntity<CustomerEntity> create(@RequestBody final NewCustomerDto customerDto) {
    final CustomerEntity customerEntity = customerService.createCustomer(customerDto.toEntity());
    return ResponseEntity.status(201)
        .body(customerEntity);
  }
}
