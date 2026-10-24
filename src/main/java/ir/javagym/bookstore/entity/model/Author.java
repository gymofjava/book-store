package ir.javagym.bookstore.entity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_sequence")
    @SequenceGenerator(name = "author_id_sequence", sequenceName = "author_id", allocationSize = 1)
    private Long id;
    private String fullName;
    @ElementCollection
    @CollectionTable(name = "author_alias", joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "alias")
    private List<String> aliases;
}
