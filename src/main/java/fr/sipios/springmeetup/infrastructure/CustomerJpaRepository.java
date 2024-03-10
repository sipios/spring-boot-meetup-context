package fr.sipios.springmeetup.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
