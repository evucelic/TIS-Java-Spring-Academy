package hr.tis.academy.repository.exception;

import java.io.IOException;

public class NoProductFoundException extends RuntimeException {
    public NoProductFoundException(String message) {
        super(message);
    }

    public NoProductFoundException(Exception e) {
        super(e);
    }
}
