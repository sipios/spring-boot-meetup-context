package fr.sipios.springmeetup.book;

public record BookDto(Long id, String title, String author) {

  public static BookDto fromBook(Book book) {
    return new BookDto(book.getId(), book.getTitle(), book.getAuthor());
  }

}
