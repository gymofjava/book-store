package ir.javagym.bookstore.controller;

import io.swagger.annotations.ApiOperation;
import ir.javagym.bookstore.aspect.pagination.Pagination;
import ir.javagym.bookstore.entity.dto.BookDTO;
import ir.javagym.bookstore.entity.dto.BookSearchParams;
import ir.javagym.bookstore.entity.model.Book;
import ir.javagym.bookstore.entity.dto.Response;
import ir.javagym.bookstore.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import static ir.javagym.bookstore.utility.CommonUtility.getPagination;

@RestController
@RequestMapping("api/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    Response<Long> addBook(@RequestBody @Valid Book book) {
        return bookService.addBook(book);
    }

    @PutMapping
    Response<Long> updateBook(@RequestBody @Valid Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("{id}")
    Response<?> deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

    @GetMapping("{id}")
    Response<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Pagination
    @GetMapping
    @ApiOperation(value = "getAllBooks (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    Response<Page<Book>> getAllBooks(HttpServletRequest request) {
        Pageable pagination = getPagination(request);
        return bookService.getAllBooks(pagination);
    }

    // search by : bookTitle, bookIsbnList, authorName (and todo search by bookReferenceUrl, bookReleaseDate, bookLang, bookPrice, bookFileFormat, categoryName, tagName, publisherName)
    @Pagination
    @ApiOperation(value = "searchBook (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    @PostMapping("search")
    Response<Page<BookDTO>> searchBook(@RequestBody @Valid BookSearchParams bookSearchParams, HttpServletRequest request) {
        Pageable pagination = getPagination(request);
        return bookService.searchBook(bookSearchParams, pagination);
    }
}
