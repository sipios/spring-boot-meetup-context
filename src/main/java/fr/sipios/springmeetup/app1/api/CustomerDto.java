package fr.sipios.springmeetup.app1.api;

import fr.sipios.springmeetup.app1.infrastructure.CustomerEntity;

public record CustomerDto(String firstName, String lastName) {
  public static CustomerDto from(final CustomerEntity myEntity) {
    return new CustomerDto(myEntity.getFirstName(), myEntity.getLastName());
  }
}
