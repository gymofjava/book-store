package ir.javagym.bookstore.aspect.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends  CustomException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, null);
    }

    public DataNotFoundException(String message, Object data) {
        super(message, HttpStatus.NOT_FOUND, data);
    }
}
