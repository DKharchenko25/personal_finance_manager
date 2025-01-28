package com.dk.pet.personal_finance_manager.facade;

import com.dk.pet.personal_finance_manager.dto.category.CategoryResponse;
import com.dk.pet.personal_finance_manager.dto.category.CreateCategoryRequest;
import com.dk.pet.personal_finance_manager.entity.Category;
import com.dk.pet.personal_finance_manager.entity.Type;
import com.dk.pet.personal_finance_manager.mapper.CategoryMapper;
import com.dk.pet.personal_finance_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryFacade {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public Page<CategoryResponse> getAllCategoriesByUserIdAndType(UUID userId, Type type, Pageable pageable) {
        return categoryService.getAllCategoriesByUserIdAndType(userId, type, pageable)
                .map(categoryMapper::entityToResponse);
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = categoryMapper.requestToEntity(request);
        Category createdCategory = categoryService.createCategory(category, request.getUserId());
        return categoryMapper.entityToResponse(createdCategory);
    }

    public void deleteCategoryById(UUID id) {
        categoryService.deleteCategoryById(id);
    }
}
