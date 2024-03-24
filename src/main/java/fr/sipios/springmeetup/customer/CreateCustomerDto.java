package fr.sipios.springmeetup.customer;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCustomerDto(
  @NotBlank
  String name,
  @NotBlank
  @Email
  String email,
  @NotBlank
  @Size(min = 8, max = 64)
  String password,
  @NotNull
  CustomerRole role
) {

  public Customer toCustomer() {
    return new Customer(name, email, password, role, true);
  }

}
