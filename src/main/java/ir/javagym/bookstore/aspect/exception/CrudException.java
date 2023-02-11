package ir.javagym.bookstore.aspect.exception;

import org.springframework.http.HttpStatus;

public class CrudException extends CustomException {

    public CrudException() {
        super();
    }

    public CrudException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public CrudException(String message, HttpStatus code) {
        super(message, code, null);
    }

    public CrudException(String message, HttpStatus code, Object data) {
        super(message, code, data);
    }
}
