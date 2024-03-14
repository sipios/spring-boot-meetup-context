package fr.sipios.springmeetup.app1.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
