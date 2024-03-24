package fr.sipios.springmeetup.customer;

import fr.sipios.springmeetup.utils.MeasureTime;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEventLogger customerEventLogger;

public CustomerService(CustomerRepository customerRepository, CustomerEventLogger customerEventLogger) throws InterruptedException {
    MeasureTime.start(this.getClass().getSimpleName());
    this.customerRepository = customerRepository;
    this.customerEventLogger = customerEventLogger;
    Thread.sleep(3000);
  }

  @PostConstruct
  public void postConstruct() {
    MeasureTime.stop(this.getClass().getSimpleName());
  }

  public Customer createCustomer(Customer customer) {
        customerEventLogger.logNewCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) throws CustomerNotFound {
        customerEventLogger.logCustomerAccess(id);
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFound(id));
    }

    public void deleteCustomer(Long id) {
        customerEventLogger.logCustomerDeleted(id);
        customerRepository.deleteById(id);
    }

    public List<Customer> getCustomers() {
        customerEventLogger.logAllCustomersAccess();
        return customerRepository.findAll();
    }
}
