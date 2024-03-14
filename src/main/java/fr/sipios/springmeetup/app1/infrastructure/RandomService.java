package fr.sipios.springmeetup.app1.infrastructure;

import static fr.sipios.springmeetup.utils.SleepUtils.sleep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RandomService {
  public RandomService() {
    log.info("RandomService initialized ans sleeping for 5 seconds");
    sleep(5);
  }
}
