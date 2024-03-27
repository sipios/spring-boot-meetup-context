package fr.sipios.springmeetup.config;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@AutoConfigureMockMvc
@SpringBootTest
@Import(TestBeanConfiguration.class)
public abstract class IntegrationTest extends MeasurableTestSuite {
}
