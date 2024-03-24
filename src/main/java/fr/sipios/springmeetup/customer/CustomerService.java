package fr.sipios.springmeetup.customer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEventLogger customerEventLogger;

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
