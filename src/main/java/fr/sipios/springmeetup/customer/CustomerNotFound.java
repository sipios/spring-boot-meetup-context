package fr.sipios.springmeetup.customer;

public class CustomerNotFound extends Exception {
  public CustomerNotFound(Long customerId) {
    super("Customer with id " + customerId + " not found");
  }
}
