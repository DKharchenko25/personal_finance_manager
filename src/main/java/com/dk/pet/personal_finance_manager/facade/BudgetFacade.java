package com.dk.pet.personal_finance_manager.facade;

import com.dk.pet.personal_finance_manager.dto.budget.BudgetResponse;
import com.dk.pet.personal_finance_manager.dto.budget.CreateBudgetRequest;
import com.dk.pet.personal_finance_manager.dto.budget.UpdateBudgetRequest;
import com.dk.pet.personal_finance_manager.entity.Budget;
import com.dk.pet.personal_finance_manager.mapper.BudgetMapper;
import com.dk.pet.personal_finance_manager.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BudgetFacade {
    private final BudgetService budgetService;
    private final BudgetMapper budgetMapper;

    public Page<BudgetResponse> getAllBudgetsByUserId(UUID userId, UUID categoryId,
                                                      LocalDateTime startDate, LocalDateTime endDate,
                                                      Pageable pageable) {
        return budgetService.getAllBudgetsByUserId(userId, categoryId, startDate, endDate, pageable)
                .map(budgetMapper::entityToResponse);
    }

    public BudgetResponse getBudgetByIdAndUserId(UUID id, UUID userId) {
        Budget budget = budgetService.getBudgetByIdAndUserId(id, userId);
        return budgetMapper.entityToResponse(budget);
    }

    public BudgetResponse createBudget(CreateBudgetRequest request) {
        Budget budget = budgetMapper.requestToEntity(request);
        Budget createdBudget = budgetService.createBudget(budget, request.getUserId(), request.getCategoryId());
        return budgetMapper.entityToResponse(createdBudget);
    }

    public BudgetResponse updateBudgetById(UUID id, UpdateBudgetRequest request) {
        Budget updatedBudget = budgetService.updateBudgetById(id, request);
        return budgetMapper.entityToResponse(updatedBudget);
    }

    public void deleteBudgetById(UUID id) {
        budgetService.deleteBudgetById(id);
    }
}
