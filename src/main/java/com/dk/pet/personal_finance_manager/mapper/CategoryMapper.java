package com.dk.pet.personal_finance_manager.mapper;

import com.dk.pet.personal_finance_manager.dto.category.CategoryResponse;
import com.dk.pet.personal_finance_manager.dto.category.CreateCategoryRequest;
import com.dk.pet.personal_finance_manager.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Category requestToEntity(CreateCategoryRequest request);

    @Mapping(target = "userId", source = "user.id")
    CategoryResponse entityToResponse(Category category);
}
