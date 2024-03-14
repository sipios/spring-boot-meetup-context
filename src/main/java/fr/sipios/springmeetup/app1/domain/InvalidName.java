package fr.sipios.springmeetup.app1.domain;

public class InvalidName extends RuntimeException {
  public InvalidName(String name) {
    super("Could not create customer with name : " + name);
  }
}
