package ir.javagym.bookstore.controller;

import io.swagger.annotations.ApiOperation;
import ir.javagym.bookstore.aspect.pagination.Pagination;
import ir.javagym.bookstore.entity.model.Author;
import ir.javagym.bookstore.entity.dto.Response;
import ir.javagym.bookstore.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import static ir.javagym.bookstore.utility.CommonUtility.getPagination;

@RestController
@RequestMapping("api/author")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    Response<Long> addAuthor(@RequestBody @Valid Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping
    Response<Long> updateAuthor(@RequestBody @Valid Author author) {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("{id}")
    Response<?> deleteAuthorById(@PathVariable Long id) {
        return authorService.deleteAuthorById(id);
    }

    @GetMapping("{id}")
    Response<Author> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @Pagination
    @GetMapping
    @ApiOperation(value = "getAllAuthors (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    Response<Page<Author>> getAllAuthors(HttpServletRequest request) {
        Pageable pagination = getPagination(request);
        return authorService.getAllAuthors(pagination);
    }
}
