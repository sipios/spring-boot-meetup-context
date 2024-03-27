package fr.sipios.springmeetup.config;

import fr.sipios.springmeetup.utils.MeasureTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class MeasurableTestSuite {

  @BeforeAll
  public static void setup() {
    MeasureTime.start();
  }

  @AfterAll
  public static void tearDown() {
    MeasureTime.stop();
  }
}
