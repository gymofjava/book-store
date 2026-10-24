package ir.javagym.bookstore.dao;

import ir.javagym.bookstore.entity.model.CategoryTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTagRepository extends JpaRepository<CategoryTag, Long> {
    Page<CategoryTag> findAllByType(Pageable pageable, String filterByType);
}
