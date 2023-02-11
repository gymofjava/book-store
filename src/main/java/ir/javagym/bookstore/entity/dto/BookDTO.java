package ir.javagym.bookstore.entity.dto;

import ir.javagym.bookstore.entity.model.Book;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BookDTO {
    private Long id;
    private String title;
    private Integer edition;
    private LocalDateTime releaseDate;
    private Integer pageNumber;
    private String lang;
    private String coverImage;
    private Integer price;
    private List<String> isbnList;

//    private List<CategoryTag> categories;
//    private List<CategoryTag> tags;
//    private List<Publisher> publishers;
//    private List<Author> authors;


    public BookDTO(Long id, String title, Integer edition, LocalDateTime releaseDate, Integer pageNumber, String lang, String coverImage, Integer price, List<String> isbnList) {
        this.id = id;
        this.title = title;
        this.edition = edition;
        this.releaseDate = releaseDate;
        this.pageNumber = pageNumber;
        this.lang = lang;
        this.coverImage = coverImage;
        this.price = price;
        this.isbnList = isbnList;
    }

    public static Page<BookDTO> convertFrom(Page<Book> bookPage) {
        return bookPage.map(BookDTO::createFrom);
    }

    public static BookDTO createFrom(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getEdition(),
                book.getReleaseDate(),
                book.getPageNumber(),
                book.getLang(),
                book.getCoverImage(),
                book.getPrice(),
                book.getIsbnList()
        );
    }
}
