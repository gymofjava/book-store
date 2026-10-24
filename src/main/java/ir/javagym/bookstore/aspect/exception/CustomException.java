package ir.javagym.bookstore.aspect.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private HttpStatus code;
    private Object data;

    public CustomException() {}

    public CustomException(String message, HttpStatus code, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }
}
