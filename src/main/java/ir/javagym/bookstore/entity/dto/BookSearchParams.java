package ir.javagym.bookstore.entity.dto;

import lombok.Getter;

@Getter
public class BookSearchParams {
    private String title;
    private String authorName;
    private String isbn;

    private String sortBy;
}
