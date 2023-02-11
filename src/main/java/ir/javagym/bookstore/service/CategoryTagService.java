package ir.javagym.bookstore.service;

import ir.javagym.bookstore.aspect.exception.BadRequestException;
import ir.javagym.bookstore.aspect.exception.CrudException;
import ir.javagym.bookstore.aspect.exception.DataNotFoundException;
import ir.javagym.bookstore.dao.CategoryTagRepository;
import ir.javagym.bookstore.entity.model.CategoryTag;
import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryTagService {
    private CategoryTagRepository categoryTagRepository;

    public CategoryTagService(CategoryTagRepository categoryTagRepository) {
        this.categoryTagRepository = categoryTagRepository;
    }

    public Response<Long> addCategoryOrTag(CategoryTag categoryTag) {
        if (categoryTag.getId() != null)
            throw new BadRequestException("Don't send id");
        categoryTag.setDates();
        CategoryTag savedCategoryTag = categoryTagRepository.save(categoryTag);
        if (savedCategoryTag.getId() == null)
            throw new CrudException("Saving CategoryOrTag has a problem");
        return Response.<Long>builder().message("CategoryOrTag added").body(savedCategoryTag.getId()).build();
    }

    public Response<Long> updateCategoryOrTag(CategoryTag categoryTag) {
        if (categoryTag.getId() == null)
            throw new BadRequestException("You must Send id attribute for updating CategoryOrTag");
        Optional<CategoryTag> optionalCategoryOrTag = categoryTagRepository.findById(categoryTag.getId());
        CategoryTag foundBookCommon = optionalCategoryOrTag.orElseThrow(() -> new DataNotFoundException("This CategoryOrTag not exist"));

        categoryTag.setCreateDate(foundBookCommon.getCreateDate());
        categoryTag.setUpdateDate(LocalDateTime.now());

        CategoryTag updatedCategoryTag = categoryTagRepository.save(categoryTag);
        return Response.<Long>builder().message("CategoryOrTag updated").body(updatedCategoryTag.getId()).build();
    }

    public Response<?> deleteCategoryOrTagById(Long id) {
        categoryTagRepository.deleteById(id);
        return Response.builder().message("CategoryOrTag deleted").build();
    }

    public Response<CategoryTag> getCategoryOrTagById(Long id) {
        Optional<CategoryTag> result = categoryTagRepository.findById(id);
        CategoryTag categoryTag = result.orElseThrow(() -> new DataNotFoundException("This CategoryOrTag not exist"));
        return Response.<CategoryTag>builder().message("CategoryOrTag founded").body(categoryTag).build();
    }

    public Response<Page<CategoryTag>> getAllCategoryOrTag(Pageable pagination, String filterByType) {
        Page<CategoryTag> categoryTagPage;
        if (filterByType == null)
            categoryTagPage = categoryTagRepository.findAll(pagination);
        else
            categoryTagPage = categoryTagRepository.findAllByType(pagination, filterByType);
        if (categoryTagPage.isEmpty())
            throw new DataNotFoundException("Page of CategoryOrTag not found");
        return Response.<Page<CategoryTag>>builder().message("Page of CategoryOrTag founded").body(categoryTagPage).build();
    }
}
