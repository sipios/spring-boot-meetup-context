package fr.sipios.springmeetup.book;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
    final Book createdBook = bookService.createBook(createBookDto.toBook());
    return ResponseEntity.created(URI.create("/books")).body(BookDto.fromBook(createdBook));
  }

  @GetMapping
  public ResponseEntity<BooksDto> getBooks() {
    final List<Book> books = bookService.getBooks();
    return ResponseEntity.ok(BooksDto.fromBooks(books));
  }

  @DeleteMapping("/{id}")
  @SneakyThrows
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.ok().build();
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
    final Book book = bookService.getBook(id);
    return ResponseEntity.ok(BookDto.fromBook(book));
  }


}
