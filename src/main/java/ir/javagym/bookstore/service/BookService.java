package ir.javagym.bookstore.service;

import ir.javagym.bookstore.dao.BookRepository;
import ir.javagym.bookstore.aspect.exception.BadRequestException;
import ir.javagym.bookstore.aspect.exception.CrudException;
import ir.javagym.bookstore.aspect.exception.DataNotFoundException;
import ir.javagym.bookstore.entity.dto.BookDTO;
import ir.javagym.bookstore.entity.dto.BookSearchParams;
import ir.javagym.bookstore.entity.model.Book;
import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Response<Long> addBook(Book book) {
        if (book.getId() != null)
            throw new BadRequestException("Don't send id");
        book.setDates();
        Book savedBook = bookRepository.save(book);
        if (savedBook.getId() == null)
            throw new CrudException("Saving Book has a problem");
        return Response.<Long>builder().message("Book added").body(savedBook.getId()).build();
    }

    public Response<Long> updateBook(Book book) {
        if (book.getId() == null)
            throw new BadRequestException("You must Send id attribute for updating Book");
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        Book foundBook = optionalBook.orElseThrow(() -> new DataNotFoundException("This Book not exist"));

        book.setCreateDate(foundBook.getCreateDate());
        book.setUpdateDate(LocalDateTime.now());

        Book updatedBook = bookRepository.save(book);
        return Response.<Long>builder().message("Book updated").body(updatedBook.getId()).build();
    }

    public Response<?> deleteBookById(Long id) {
        bookRepository.deleteById(id);
        return Response.builder().message("Book deleted").build();
    }

    public Response<Book> getBookById(Long id) {
        Optional<Book> result = bookRepository.findById(id);
        Book book = result.orElseThrow(() -> new DataNotFoundException("This Book not exist"));
        return Response.<Book>builder().message("Book founded").body(book).build();
    }

    public Response<Page<Book>> getAllBooks(Pageable pagination) {
        Page<Book> bookPage = bookRepository.findAll(pagination);
        if (bookPage.isEmpty())
            throw new DataNotFoundException("Page of Books not found");
        return Response.<Page<Book>>builder().message("Page of Books founded").body(bookPage).build();
    }

    public Response<Page<BookDTO>> searchBook(BookSearchParams bookSearchParams, Pageable pagination) {
        if (bookSearchParams.getSortBy() != null)
            pagination = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(), Sort.Direction.DESC, bookSearchParams.getSortBy());
        else
            pagination = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(), Sort.Direction.DESC, "title");

        Page<Book> bookPage = bookRepository.findAllByTitleOrIsbnListOrAuthorName(pagination,
                bookSearchParams.getTitle(), bookSearchParams.getIsbn(), bookSearchParams.getAuthorName());
        if (bookPage.isEmpty())
            throw new DataNotFoundException("Page of Books not found");
        return Response.<Page<BookDTO>>builder().message("Page of Books founded")
                .body(BookDTO.convertFrom(bookPage)).build();
    }
}
