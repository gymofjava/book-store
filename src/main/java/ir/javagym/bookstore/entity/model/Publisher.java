package ir.javagym.bookstore.entity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Publisher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
    @SequenceGenerator(name = "book_id_sequence", sequenceName = "book_id", allocationSize = 1)
    private Long id;
    private String name;
    private String websiteUrl;
}
