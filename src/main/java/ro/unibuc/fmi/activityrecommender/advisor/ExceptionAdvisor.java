package ro.unibuc.fmi.activityrecommender.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvisor {

    private static String ERROR = "error";
    
    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<Map<String, String>> errorHandle(final GenericException genericException) {
        HashMap<String, String> body = new HashMap<>();
        body.put(ERROR, genericException.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }

}
