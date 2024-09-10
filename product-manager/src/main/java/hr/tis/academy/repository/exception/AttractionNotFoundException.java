package hr.tis.academy.repository.exception;

public class AttractionNotFoundException extends RuntimeException {
    public AttractionNotFoundException(String message) {
        super(message);
    }

    public AttractionNotFoundException() {
    }
}
