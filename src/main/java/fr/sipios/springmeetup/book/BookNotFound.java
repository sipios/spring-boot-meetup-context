package fr.sipios.springmeetup.book;

public class BookNotFound extends Exception {
  public BookNotFound(Long id) {
    super("Book with id " + id + " not found");
  }
}
