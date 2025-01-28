package com.dk.pet.personal_finance_manager.mapper;

import com.dk.pet.personal_finance_manager.dto.budget.BudgetResponse;
import com.dk.pet.personal_finance_manager.dto.budget.CreateBudgetRequest;
import com.dk.pet.personal_finance_manager.entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Budget requestToEntity(CreateBudgetRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    BudgetResponse entityToResponse(Budget budget);
}
