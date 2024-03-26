package fr.sipios.springmeetup;

import fr.sipios.springmeetup.book.BookService;
import fr.sipios.springmeetup.customer.CustomerService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
public abstract class ControllerIT extends MeasurableTestSuite {

  @MockBean
  protected BookService bookService;

  @MockBean
  protected CustomerService customerService;


}
