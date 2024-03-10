package fr.sipios.springmeetup.api;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;

import java.util.List;

public record CustomersDto(
    List<CustomerDto> customers
) {

  public static CustomersDto from(final List<CustomerEntity> customers) {
    return new CustomersDto(customers.stream().map(CustomerDto::from).toList());
  }
}
