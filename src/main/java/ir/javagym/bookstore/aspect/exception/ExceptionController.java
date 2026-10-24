package ir.javagym.bookstore.aspect.exception;

import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response<Object>> handleCustomException(CustomException exception) {
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(exception.getCode()).body(exception.getData()).build();
        return new ResponseEntity<>(response, exception.getCode());
    }
}
