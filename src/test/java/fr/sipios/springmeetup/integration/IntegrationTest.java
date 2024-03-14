package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.utils.MeasureTestTimeUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class IntegrationTest {

  @Autowired
  protected MockMvc mockMvc;

  @BeforeAll
  public static void init() {
    MeasureTestTimeUtils.initNewTestSuite(IntegrationTest.class.getName());
  }

  @AfterAll
  public static void end() {
    MeasureTestTimeUtils.endTestSuite();
  }
}