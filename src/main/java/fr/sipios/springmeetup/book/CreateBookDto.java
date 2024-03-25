package fr.sipios.springmeetup.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateBookDto(
    @NotBlank
    @NotNull
    String title,
    @NotBlank
    @NotNull
    @NotEmpty
    String author
) {
  public Book toBook() {
    return new Book(null, title, author);
  }
}
