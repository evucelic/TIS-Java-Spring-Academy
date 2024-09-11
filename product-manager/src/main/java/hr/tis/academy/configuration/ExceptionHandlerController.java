package hr.tis.academy.configuration;

import hr.tis.academy.repository.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExists){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("User already exists uuid: '{}'", uuid, userAlreadyExists);

        Map<String, Object> response = new HashMap<>();
        response.put("message", userAlreadyExists.getMessage());
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AttractionNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAttractionNotFoundException(AttractionNotFoundException attractionNotFoundException){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Attraction not found exception uuid: '{}'", uuid, attractionNotFoundException);

        Map<String, Object> response = new HashMap<>();
        response.put("message", attractionNotFoundException.getMessage());
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("User not found exception uuid: '{}'", uuid, userNotFoundException);

        Map<String, Object> response = new HashMap<>();
        response.put("message", userNotFoundException.getMessage());
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RatingNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleRatingNotValidException(RatingNotValidException ratingNotValidException){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Rating not valid exception uuid: '{}'", uuid, ratingNotValidException);

        Map<String, Object> response = new HashMap<>();
        response.put("message", ratingNotValidException.getMessage());
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FavoriteAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleRatingNotValidException(FavoriteAlreadyExistsException favoriteAlreadyExistsException){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Rating not valid exception uuid: '{}'", uuid, favoriteAlreadyExistsException);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Attraction is already in the user's favorites");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PictureNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePictureNotFoundException(PictureNotFoundException pictureNotFoundException){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Picture not found exception uuid: '{}'", uuid, pictureNotFoundException);

        Map<String, Object> response = new HashMap<>();
        response.put("message", pictureNotFoundException.getMessage());
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
