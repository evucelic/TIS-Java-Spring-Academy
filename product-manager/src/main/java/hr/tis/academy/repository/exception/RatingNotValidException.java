package hr.tis.academy.repository.exception;

public class RatingNotValidException extends RuntimeException{
    public RatingNotValidException(String message) {
        super(message);
    }

    public RatingNotValidException(){

        super();
    }
}
