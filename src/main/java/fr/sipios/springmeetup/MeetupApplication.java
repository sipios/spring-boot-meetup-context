package fr.sipios.springmeetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeetupApplication {

  public static void main(String[] args) {
    final var context = SpringApplication.run(MeetupApplication.class, args);
    if (context.containsBean("meetupApplication")) {
       System.out.println("MeetupApplication bean is present");
    }

  }

}
