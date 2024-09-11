package hr.tis.academy.repository.exception;

public class PictureNotFoundException extends RuntimeException{
    public PictureNotFoundException(String message) {
        super(message);
    }

    public PictureNotFoundException() {
    }
}
