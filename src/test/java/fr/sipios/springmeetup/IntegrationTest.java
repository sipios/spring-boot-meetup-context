package fr.sipios.springmeetup;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public abstract class IntegrationTest extends MeasurableTestSuite {
}
