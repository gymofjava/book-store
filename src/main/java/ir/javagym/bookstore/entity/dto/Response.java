package ir.javagym.bookstore.entity.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class Response<T> {
    @Builder.Default
    private HttpStatus code = HttpStatus.OK;
    private String message;
    private T body;
}
