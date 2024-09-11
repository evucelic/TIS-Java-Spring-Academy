package hr.tis.academy.repository.exception;

public class JournalNotFoundException extends RuntimeException{
    public JournalNotFoundException(String message) {
        super(message);
    }

    public JournalNotFoundException() {
    }
}
