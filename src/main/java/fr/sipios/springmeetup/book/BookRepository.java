package fr.sipios.springmeetup.book;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> deleteBookById(Long id);
}
