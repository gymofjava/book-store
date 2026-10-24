package ir.javagym.bookstore.entity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class CategoryTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_common_id_sequence")
    @SequenceGenerator(name = "book_common_id_sequence", sequenceName = "book_common_id", allocationSize = 1)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
}
