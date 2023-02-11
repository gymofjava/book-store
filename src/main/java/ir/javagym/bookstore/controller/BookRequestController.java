package ir.javagym.bookstore.controller;

import io.swagger.annotations.ApiOperation;
import ir.javagym.bookstore.aspect.pagination.Pagination;
import ir.javagym.bookstore.entity.model.BookRequest;
import ir.javagym.bookstore.entity.dto.Response;
import ir.javagym.bookstore.service.BookRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static ir.javagym.bookstore.utility.CommonUtility.getPagination;

@RestController
@RequestMapping("api/bookRequest")
public class BookRequestController {
    private BookRequestService bookRequestService;

    public BookRequestController(BookRequestService bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @PostMapping
    Response<Long> addBookRequest(@RequestBody @Valid BookRequest bookRequest) {
        return bookRequestService.addBookRequest(bookRequest);
    }

    @DeleteMapping("{id}")
    Response<?> deleteBookRequestById(@PathVariable Long id) {
        return bookRequestService.deleteBookRequestById(id);
    }

    @GetMapping("{id}")
    Response<BookRequest> getBookRequestById(@PathVariable Long id) {
        return bookRequestService.getBookRequestById(id);
    }

    @Pagination
    @GetMapping
    @ApiOperation(value = "getAllBookRequests (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    Response<Page<BookRequest>> getAllBookRequests(HttpServletRequest request) {
        Pageable pagination = getPagination(request);
        return bookRequestService.getAllBookRequests(pagination);
    }
}
