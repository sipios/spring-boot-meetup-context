package fr.sipios.springmeetup.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) throws CustomerNotFound {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFound(id));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
