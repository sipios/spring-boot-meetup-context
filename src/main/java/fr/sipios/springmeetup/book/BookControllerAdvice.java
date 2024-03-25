package fr.sipios.springmeetup.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
@Slf4j
public class BookControllerAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler(BookNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ProblemDetail bookNotFound(BookNotFound e) {
    log.warn(e.getMessage());
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problemDetail.setTitle("Book not found");
    problemDetail.setProperty("timestamp", System.currentTimeMillis());
    problemDetail.setType(URI.create("/books/not-found"));
    return problemDetail;
  }
}
