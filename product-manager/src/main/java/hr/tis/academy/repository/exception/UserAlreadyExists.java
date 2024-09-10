package hr.tis.academy.repository.exception;

import java.sql.Timestamp;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(message);
    }

    public UserAlreadyExists() {
    }
}
