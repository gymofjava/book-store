package ir.javagym.bookstore.dao;

import ir.javagym.bookstore.entity.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
}
