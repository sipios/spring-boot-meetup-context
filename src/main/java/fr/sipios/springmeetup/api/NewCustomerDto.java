package fr.sipios.springmeetup.api;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;
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
