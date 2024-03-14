package fr.sipios.springmeetup.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("example")
public class ConfigExample {
  @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
  public BeanExample mySpringBean() {
    return new BeanExample();
  }
}
