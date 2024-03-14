package fr.sipios.springmeetup.app1.api;

import fr.sipios.springmeetup.app1.domain.InvalidName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = CustomersController.class)
public class CustomersControllerAdvice {

  @ExceptionHandler(InvalidName.class)
  public ResponseEntity<String> handleInvalidName(InvalidName e) {
    return ResponseEntity.badRequest()
        .body(e.getMessage());
  }
}
