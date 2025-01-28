package com.dk.pet.personal_finance_manager.controller;

import com.dk.pet.personal_finance_manager.dto.category.CategoryResponse;
import com.dk.pet.personal_finance_manager.dto.category.CreateCategoryRequest;
import com.dk.pet.personal_finance_manager.entity.Type;
import com.dk.pet.personal_finance_manager.facade.CategoryFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@Validated
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryFacade categoryFacade;

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAllCategoriesByType(@RequestParam Type type, Pageable pageable) {
        UUID userId = UUID.randomUUID(); // TODO: get userId from security context
        Page<CategoryResponse> categories = categoryFacade.getAllCategoriesByUserIdAndType(userId, type, pageable);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        CategoryResponse response = categoryFacade.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable UUID categoryId) {
        categoryFacade.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
