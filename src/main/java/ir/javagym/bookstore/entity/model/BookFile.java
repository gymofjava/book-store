package ir.javagym.bookstore.entity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class BookFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_file_id_sequence")
    @SequenceGenerator(name = "book_file_id_sequence", sequenceName = "book_file_id", allocationSize = 1)
    private Long id;
    private String fileUrl;
    private String fileFormat;
}
