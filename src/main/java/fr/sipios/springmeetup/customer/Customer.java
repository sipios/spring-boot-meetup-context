package fr.sipios.springmeetup.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private CustomerRole role;
  private boolean enabled;

  public Customer(String name, String email, String password, CustomerRole role, boolean enabled) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
  }


  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", role=" + role +
        ", enabled=" + enabled +
        '}';
  }
}
