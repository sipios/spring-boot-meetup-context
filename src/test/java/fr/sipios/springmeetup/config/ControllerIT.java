package fr.sipios.springmeetup.config;

import fr.sipios.springmeetup.book.BookService;
import fr.sipios.springmeetup.customer.CustomerService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
public abstract class ControllerIT extends MeasurableTestSuite {
  @MockBean
  protected CustomerService customerService;

  @MockBean
  protected BookService bookService;
}
