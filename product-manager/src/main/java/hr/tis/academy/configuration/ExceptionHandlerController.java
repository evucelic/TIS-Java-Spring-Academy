package hr.tis.academy.configuration;

import hr.tis.academy.repository.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<Map<String, Object>> handleNoProductFoundException(UserAlreadyExistsException userAlreadyExists){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Unhandled exception uuid: '{}'", uuid, userAlreadyExists);

        Map<String, Object> response = new HashMap<>();
        response.put("message", userAlreadyExists.getMessage());
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.badRequest().body(response);
    }
}
