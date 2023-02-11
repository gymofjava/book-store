package ir.javagym.bookstore.entity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
public class BookRequest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_request_id_sequence")
    @SequenceGenerator(name = "book_request_id_sequence", sequenceName = "book_request_id", allocationSize = 1)
    private Long id;

    // todo userId or email or username
    @NotBlank
    private String userId;
    private String titleOrLink;
}
