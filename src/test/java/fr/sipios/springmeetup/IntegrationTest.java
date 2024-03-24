package fr.sipios.springmeetup;


import fr.sipios.springmeetup.utils.MeasureTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public abstract class IntegrationTest {
  @BeforeAll
  public static void setup() {
    MeasureTime.start();
  }

  @AfterAll
  public static void tearDown() {
    MeasureTime.stop();
  }
}
