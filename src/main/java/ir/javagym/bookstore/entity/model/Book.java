package ir.javagym.bookstore.entity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
    @SequenceGenerator(name = "book_id_sequence", sequenceName = "book_id", allocationSize = 1)
    private Long id;
    private String title;
    private Integer edition;
    private String referenceUrl;
    private LocalDateTime releaseDate;
    private Integer pageNumber;
    private String lang;
    private String coverImage;
    private Integer price;
    private Integer referencePrice;
    private Integer offPercentage;
    private Boolean active;

    @ElementCollection
    @CollectionTable(name = "book_isbn", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "isbn")
    private List<String> isbnList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookFile> bookFiles;
    @OneToMany
    private List<CategoryTag> categories;
    @OneToMany
    private List<CategoryTag> tags;
    @OneToMany
    private List<Publisher> publishers;
    @OneToMany
    private List<Author> authors;
}
