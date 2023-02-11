package ir.javagym.bookstore.dao;

import ir.javagym.bookstore.entity.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book join book.authors author join book.isbnList isbns where " +
            "lower(book.title) like lower(concat('%', :title, '%')) or :isbn in isbns or " +
            "lower(author.fullName) like lower(concat('%', :authorName, '%')) ")
    Page<Book> findAllByTitleOrIsbnListOrAuthorName(Pageable pageable, String title, String isbn, String authorName);
}
