package fr.sipios.springmeetup.utils;

public class SleepUtils {
  private SleepUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
