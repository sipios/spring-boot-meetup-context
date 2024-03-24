package fr.sipios.springmeetup.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@Slf4j
@RestControllerAdvice(assignableTypes = CustomerController.class)
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomerNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ProblemDetail customerNotFound(CustomerNotFound e) {
    log.warn("Customer not found");
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problemDetail.setTitle("Customer not found");
    problemDetail.setProperty("timestamp", Instant.now());
    problemDetail.setType(URI.create("/customers/not-found"));
    return problemDetail;
  }

}
