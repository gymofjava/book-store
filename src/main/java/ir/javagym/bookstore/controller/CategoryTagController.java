package ir.javagym.bookstore.controller;

import io.swagger.annotations.ApiOperation;
import ir.javagym.bookstore.aspect.pagination.Pagination;
import ir.javagym.bookstore.entity.model.CategoryTag;
import ir.javagym.bookstore.entity.dto.Response;
import ir.javagym.bookstore.service.CategoryTagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static ir.javagym.bookstore.utility.CommonUtility.getPagination;

@RestController
@RequestMapping("api/categoryTag")
public class CategoryTagController {
    private CategoryTagService categoryTagService;

    public CategoryTagController(CategoryTagService categoryTagService) {
        this.categoryTagService = categoryTagService;
    }

    @PostMapping
    Response<Long> addCategoryOrTag(@RequestBody @Valid CategoryTag categoryTag) {
        return categoryTagService.addCategoryOrTag(categoryTag);
    }

    @PutMapping
    Response<Long> updateCategoryOrTag(@RequestBody @Valid CategoryTag categoryTag) {
        return categoryTagService.updateCategoryOrTag(categoryTag);
    }

    @DeleteMapping("{id}")
    Response<?> deleteCategoryOrTagById(@PathVariable Long id) {
        return categoryTagService.deleteCategoryOrTagById(id);
    }

    @GetMapping("{id}")
    Response<CategoryTag> getCategoryOrTagById(@PathVariable Long id) {
        return categoryTagService.getCategoryOrTagById(id);
    }

    @Pagination
    @GetMapping
    @ApiOperation(value = "getAllCategoryOrTag (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    Response<Page<CategoryTag>> getAllCategoryOrTag(HttpServletRequest request, @RequestParam(required = false) String filterByType) {
        Pageable pagination = getPagination(request);
        return categoryTagService.getAllCategoryOrTag(pagination, filterByType);
    }
}
