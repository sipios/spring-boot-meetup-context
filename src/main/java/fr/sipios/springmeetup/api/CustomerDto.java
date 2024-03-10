package fr.sipios.springmeetup.api;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;

public record CustomerDto(String firstName, String lastName) {
  public static CustomerDto from(final CustomerEntity myEntity) {
    return new CustomerDto(myEntity.getFirstName(), myEntity.getLastName());
  }
}
