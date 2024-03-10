package fr.sipios.springmeetup.api;

import fr.sipios.springmeetup.domain.CustomerService;
import fr.sipios.springmeetup.infrastructure.CustomerEntity;
import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
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
    log.info("Sleeping during 5 secs");
    sleep(5);
    this.customerService = customerService;
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
