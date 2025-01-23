package com.dk.pet.personal_finance_manager.mapper;

import com.dk.pet.personal_finance_manager.dto.category.CategoryResponse;
import com.dk.pet.personal_finance_manager.dto.category.CreateCategoryRequest;
import com.dk.pet.personal_finance_manager.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category requestToEntity(CreateCategoryRequest request);

    CategoryResponse entityToResponse(Category category);
}
