package hr.tis.academy.repository.exception;

public class FavoriteAlreadyExistsException extends RuntimeException {
    public FavoriteAlreadyExistsException(String message) {
        super(message);
    }

    public FavoriteAlreadyExistsException() {
    }
}
