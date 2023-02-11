package ir.javagym.bookstore.entity.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected LocalDateTime createDate;
    protected LocalDateTime updateDate;
    protected String description;

    public void setDates() {
        this.createDate = LocalDateTime.now();
        this.updateDate = this.createDate;
    }
}
