package fr.sipios.springmeetup.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class CustomerEntity {

  private final String firstName;
  private final String lastName;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public CustomerEntity() {
    this(null, null);
  }

  public CustomerEntity(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}

