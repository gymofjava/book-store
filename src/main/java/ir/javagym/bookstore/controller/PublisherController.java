package ir.javagym.bookstore.controller;

import io.swagger.annotations.ApiOperation;
import ir.javagym.bookstore.aspect.pagination.Pagination;
import ir.javagym.bookstore.entity.model.Publisher;
import ir.javagym.bookstore.entity.dto.Response;
import ir.javagym.bookstore.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static ir.javagym.bookstore.utility.CommonUtility.getPagination;

@RestController
@RequestMapping("api/publisher")
public class PublisherController {
    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    Response<Long> addPublisher(@RequestBody @Valid Publisher publisher) {
        return publisherService.addPublisher(publisher);
    }

    @PutMapping
    Response<Long> updatePublisher(@RequestBody @Valid Publisher publisher) {
        return publisherService.updatePublisher(publisher);
    }

    @DeleteMapping("{id}")
    Response<?> deletePublisherById(@PathVariable Long id) {
        return publisherService.deletePublisherById(id);
    }

    @GetMapping("{id}")
    Response<Publisher> getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @Pagination
    @GetMapping
    @ApiOperation(value = "getAllPublishers (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    Response<Page<Publisher>> getAllPublishers(HttpServletRequest request) {
        Pageable pagination = getPagination(request);
        return publisherService.getAllPublishers(pagination);
    }
}
