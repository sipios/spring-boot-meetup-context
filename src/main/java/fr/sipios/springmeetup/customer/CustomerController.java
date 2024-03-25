package fr.sipios.springmeetup.customer;

import fr.sipios.springmeetup.utils.MeasureTime;
import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    MeasureTime.start(this.getClass().getSimpleName());
    sleep(3);
    this.customerService = customerService;
  }

  @PostConstruct
  public void postConstruct() {
    MeasureTime.stop(this.getClass().getSimpleName());
  }

  @GetMapping
  public ResponseEntity<CustomersDto> getCustomers() {
    final CustomersDto customersDto = new CustomersDto(customerService.getCustomers());
    return ResponseEntity.ok(customersDto);
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    return ResponseEntity.ok(customerService.getCustomer(id));
  }

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerDto customerDto) {
    final Customer customer = customerDto.toCustomer();
    return ResponseEntity.created(URI.create("/customers")).body(customerService.createCustomer(customer));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.ok().build();
  }
}
