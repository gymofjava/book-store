package ir.javagym.bookstore.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class UserAccount extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_id_sequence")
    @SequenceGenerator(name = "user_account_id_sequence", sequenceName = "user_account_id", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Role role;
}
