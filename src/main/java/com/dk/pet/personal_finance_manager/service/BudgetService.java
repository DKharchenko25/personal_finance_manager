package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.dto.budget.CreateBudgetRequest;
import com.dk.pet.personal_finance_manager.dto.budget.UpdateBudgetRequest;
import com.dk.pet.personal_finance_manager.entity.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BudgetService {

    Page<Budget> getAllBudgetsByUserId(UUID userId, UUID categoryId, LocalDateTime startDate,
                                       LocalDateTime endDate, Pageable pageable);

    Budget getBudgetById(UUID id);

    Budget createBudget(CreateBudgetRequest request);

    Budget updateBudgetById(UUID id, UpdateBudgetRequest request);

    void deleteBudgetById(UUID id);
}
