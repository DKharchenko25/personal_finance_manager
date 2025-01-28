package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.entity.Category;
import com.dk.pet.personal_finance_manager.entity.Type;
import com.dk.pet.personal_finance_manager.exception.ResourceNotFoundException;
import com.dk.pet.personal_finance_manager.mapper.CategoryMapper;
import com.dk.pet.personal_finance_manager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %s not found", id)));
    }

    @Override
    public Page<Category> getAllCategoriesByUserIdAndType(UUID userId, Type type, Pageable pageable) {
        return categoryRepository.findAllByUserIdAndType(userId, type, pageable);
    }

    @Override
    public Category createCategory(Category category, UUID userId) {
        category.setUser(userService.getUserById(userId));
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        Category category = getCategoryById(id);
        categoryRepository.deleteById(category.getId());
    }
}
