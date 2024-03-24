package fr.sipios.springmeetup.customer;

import java.util.List;

public record CustomersDto(
    List<Customer> customers
) {
}
