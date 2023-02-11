package ir.javagym.bookstore.aspect.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, null);
    }

    public BadRequestException(String message, Object data) {
        super(message, HttpStatus.BAD_REQUEST, data);
    }
}
