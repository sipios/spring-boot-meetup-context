package fr.sipios.springmeetup.app1.api;

import fr.sipios.springmeetup.app1.infrastructure.CustomerEntity;
import jakarta.annotation.Nonnull;

public record NewCustomerDto(
    @Nonnull
    String firstName,
    @Nonnull
    String lastName
) {

  public CustomerEntity toEntity() {
    return new CustomerEntity(firstName, lastName);
  }
}
