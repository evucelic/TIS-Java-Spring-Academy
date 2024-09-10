package hr.tis.academy.repository.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

  public UserNotFoundException() {
  }
}
