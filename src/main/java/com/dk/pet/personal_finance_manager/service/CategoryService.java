package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.entity.Category;
import com.dk.pet.personal_finance_manager.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    Category getCategoryById(UUID id);

    Page<Category> getAllCategoriesByType(Type type, Pageable pageable);
}
