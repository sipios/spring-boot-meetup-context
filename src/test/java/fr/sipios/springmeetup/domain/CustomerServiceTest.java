package fr.sipios.springmeetup.domain;

import fr.sipios.springmeetup.infrastructure.CustomerEntity;
import fr.sipios.springmeetup.infrastructure.CustomerRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService customerService;

  @Test
  void should_not_be_able_to_create_an_empty_customer() {
    assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(null));
  }


  @ParameterizedTest
  @ValueSource(strings = {"John-sasa", "1234", "é&é'(§è!çà'}"})
  @NullAndEmptySource
  void should_not_be_able_to_create_a_customer_with_an_invalid_first_name(final String firstName) {
    assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(new CustomerEntity(firstName, "Doe")));
  }

  @ParameterizedTest
  @ValueSource(strings = {"Doe-sasa", "1234", "é&é'(§è!çà'}"})
  @NullAndEmptySource
  void should_not_be_able_to_create_a_customer_with_an_empty_last_name(final String lastName) {
    assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(new CustomerEntity("John", lastName)));
  }

  @Test
  void should_create_new_customer_when_information_are_valid() {
    // GIVEN
    final CustomerEntity customerEntity = new CustomerEntity("John", "Doe");
    // WHEN
    customerService.createCustomer(customerEntity);
    // THEN
    verify(customerRepository).createCustomer(customerEntity);
  }
}