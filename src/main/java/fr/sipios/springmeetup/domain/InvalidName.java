package fr.sipios.springmeetup.domain;

public class InvalidName extends RuntimeException {
  public InvalidName(String name) {
    super("Could not create customer with name : " + name);
  }
}
