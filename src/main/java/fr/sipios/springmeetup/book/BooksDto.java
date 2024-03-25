package fr.sipios.springmeetup.book;

import java.util.List;

public record BooksDto(
    List<BookDto> books
) {

  public static BooksDto fromBooks(List<Book> books) {
    return new BooksDto(books.stream().map(BookDto::fromBook).toList());
  }
}
