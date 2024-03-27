package fr.sipios.springmeetup.controller;

import fr.sipios.springmeetup.book.Book;
import fr.sipios.springmeetup.book.BookNotFound;
import fr.sipios.springmeetup.config.ControllerIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

class BookControllerIT extends ControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  class GetBook {
    @Test
    void should_be_not_found_when_book_does_not_exist() throws Exception {
      doThrow(new BookNotFound(404L)).when(bookService).getBook(404L);

      mockMvc.perform(get("/books/404"))
          .andExpect(status().isNotFound())
          .andExpect(jsonPath("$.type").value("/books/not-found"))
          .andExpect(jsonPath("$.title").value("Book not found"))
          .andExpect(jsonPath("$.status").value(404))
          .andExpect(jsonPath("$.detail").value("Book with id 404 not found"));
    }
  }

  @Nested
  class GetBooks {
    @Test
    void should_return_all_books() throws Exception {

      doReturn(List.of(new Book(1L, "title", "author"))).when(bookService).getBooks();

      mockMvc.perform(get("/books"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.books").isArray())
          .andExpect(jsonPath("$.books[0].id").value(1L))
          .andExpect(jsonPath("$.books[0].title").value("title"))
          .andExpect(jsonPath("$.books[0].author").value("author"));
    }
  }

  @Nested
  class CreateBook {
    @Test
    void should_create_book() throws Exception {
      Book book = new Book(1L, "title", "author");
      doReturn(book).when(bookService).createBook(any(Book.class));

      mockMvc.perform(post("/books").contentType("application/json")
              .content("{\"title\":\"title\",\"author\":\"author\"}"))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(1L))
          .andExpect(jsonPath("$.title").value("title"))
          .andExpect(jsonPath("$.author").value("author"));
    }

    @Test
    void should_create_book_with_right_info() throws Exception {
      final ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
      Book book = new Book(1L, "title", "author");
      doReturn(book).when(bookService).createBook(argumentCaptor.capture());

      mockMvc.perform(post("/books").contentType("application/json")
              .content("{\"title\":\"Harry Potter\",\"author\":\"J.K.\"}"))
          .andExpect(status().isCreated());

      Book bookArgument = argumentCaptor.getValue();
      assertEquals("Harry Potter", bookArgument.getTitle(), "title should be \"Harry Potter\"");
      assertEquals("J.K.", bookArgument.getAuthor(), "author should be \"J.K.\"");
    }

    @Test
    void should_return_bad_request_when_book_title_is_blank() throws Exception {
      mockMvc.perform(post("/books").contentType("application/json")
              .content("{\"title\":\"\",\"author\":\"author\"}"))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.title").value("Bad Request"))
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.detail").value("Invalid request content."));
    }

    @Test
    void should_return_bad_request_when_book_title_is_missing() throws Exception {
      mockMvc.perform(post("/books").contentType("application/json")
              .content("{\"title\": null,\"author\":\"author\"}"))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.title").value("Bad Request"))
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.detail").value("Invalid request content."));
    }

    @Test
    void should_return_bad_request_when_book_author_is_blank() throws Exception {
      mockMvc.perform(post("/books").contentType("application/json")
              .content("{\"title\":\"title\",\"author\":\"\"}"))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.title").value("Bad Request"))
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.detail").value("Invalid request content."));
    }

    @Test
    void should_return_bad_request_when_book_author_is_missing() throws Exception {
      mockMvc.perform(post("/books").contentType("application/json")
              .content("{\"title\":\"title\",\"author\":null}"))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.title").value("Bad Request"))
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.detail").value("Invalid request content."));
    }
  }

  @Nested
  class DeleteBook {
    @Test
    void should_delete_book() throws Exception {
      doNothing().when(bookService).deleteBook(1L);
      mockMvc.perform(delete("/books/1"))
          .andExpect(status().isOk());
    }

    @Test
    void should_return_not_found_when_book_does_not_exist() throws Exception {
      doThrow(new BookNotFound(404L)).when(bookService).deleteBook(404L);
      mockMvc.perform(delete("/books/404"))
          .andExpect(status().isNotFound())
          .andExpect(jsonPath("$.type").value("/books/not-found"))
          .andExpect(jsonPath("$.title").value("Book not found"))
          .andExpect(jsonPath("$.status").value(404))
          .andExpect(jsonPath("$.detail").value("Book with id 404 not found"));
    }
  }
}