package fr.sipios.springmeetup.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MeasureTestTimeUtils {

  private static final String FILE_NAME = "test-measure.txt";
  private static final Map<String, Pair<Long, Long>> TEST_MEASURE = new HashMap<>();

  public static void registerStartTime(String testName) {
    TEST_MEASURE.put(testName, Pair.of(System.currentTimeMillis(), 0L));
  }

  private static void writeSeparatorLineToFile(String className) {
    try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
      fileWriter.write("--------------------------------------------------\n");
      fileWriter.write("Context reloaded by : " + className + " - at : " + LocalTime.now() + "\n");
      fileWriter.write("--------------------------------------------------\n");
    } catch (IOException e) {
      log.error("Error while writing to file", e);
    }
  }

  public static void writeToFile() {
    try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
      for (Map.Entry<String, Pair<Long, Long>> entry : TEST_MEASURE.entrySet()) {
        fileWriter.write(entry.getKey() + " | " + (entry.getValue().getSecond() - entry.getValue().getFirst()) + "ms\n");
      }
    } catch (IOException e) {
      log.error("Error while writing to file", e);
    }
  }

  public static void registerEndTime(String testName) {
    Pair<Long, Long> pair = TEST_MEASURE.get(testName);
    TEST_MEASURE.put(testName, Pair.of(pair.getFirst(), System.currentTimeMillis()));
  }

  public static void initNewTestSuite(String className) {
    TEST_MEASURE.clear();
    writeSeparatorLineToFile(className);
  }

  public static void endTestSuite() {
    writeToFile();
  }
}
