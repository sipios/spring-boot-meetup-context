package fr.sipios.springmeetup.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MeasureTime {

  private MeasureTime() {
    throw new IllegalStateException("Utility class");
  }

  private static final Map<String, Long> MAP_TIME_IN_SECONDS = new HashMap<>();
  public static final String FILE_NAME = "measure_time.csv";
  private static final String DEFAULT_KEY = "default";

  public static void start(String key) {
    MAP_TIME_IN_SECONDS.put(key, System.currentTimeMillis());
  }

  public static void stop(String key) {
    MAP_TIME_IN_SECONDS.put(key, System.currentTimeMillis() - MAP_TIME_IN_SECONDS.get(key));
  }

  public static void start() {
    MAP_TIME_IN_SECONDS.clear();
  }

  private static void write() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
      for (Map.Entry<String, Long> entry : MAP_TIME_IN_SECONDS.entrySet()) {
        writer.write(entry.getKey() + "," + entry.getValue() + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void stop() {
    write();
    MAP_TIME_IN_SECONDS.clear();
  }
}
