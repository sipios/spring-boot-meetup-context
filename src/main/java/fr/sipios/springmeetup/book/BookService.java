package fr.sipios.springmeetup.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public Book getBook(Long id) throws BookNotFound {
    return bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));
  }

  public void deleteBook(Long id) throws BookNotFound {
    bookRepository.deleteBookById(id).orElseThrow(() -> new BookNotFound(id));
  }

  public Book updateBook(Long id, Book book) {
    Book existingBook = bookRepository.findById(id).orElse(null);
    if (existingBook == null) {
      return null;
    }
    existingBook.setTitle(book.getTitle());
    existingBook.setAuthor(book.getAuthor());
    return bookRepository.save(existingBook);
  }

  public List<Book> getBooks() {
    return bookRepository.findAll();
  }
}
