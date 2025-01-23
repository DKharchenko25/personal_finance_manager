package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.dto.budget.CreateBudgetRequest;
import com.dk.pet.personal_finance_manager.dto.budget.UpdateBudgetRequest;
import com.dk.pet.personal_finance_manager.entity.Budget;
import com.dk.pet.personal_finance_manager.exception.ResourceNotFoundException;
import com.dk.pet.personal_finance_manager.mapper.BudgetMapper;
import com.dk.pet.personal_finance_manager.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryServiceImpl categoryService;
    private final UserServiceImpl userService;
    private final BudgetMapper budgetMapper;

    @Override
    public Page<Budget> getAllBudgetsByUserId(UUID userId, UUID categoryId, LocalDateTime startDate,
                                              LocalDateTime endDate, Pageable pageable) {
        return budgetRepository.findAllByUserId(userId, categoryId, startDate, endDate, pageable);
    }

    @Override
    public Budget getBudgetById(UUID id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Budget with id %s not found", id)));
    }

    @Override
    @Transactional
    public Budget createBudget(CreateBudgetRequest request) {
        Budget budget = budgetMapper.requestToEntity(request);
        budget.setUser(userService.getUserById(request.getUserId()));
        budget.setCategory(categoryService.getCategoryById(request.getCategoryId()));
        return budgetRepository.save(budget);
    }

    @Override
    @Transactional
    public Budget updateBudgetById(UUID id, UpdateBudgetRequest request) {
        Budget budget = getBudgetById(id);
        Optional.ofNullable(request.getCategoryId())
                .ifPresent(categoryId -> budget.setCategory(categoryService.getCategoryById(categoryId)));
        Optional.ofNullable(request.getAmount()).ifPresent(budget::setAmount);
        Optional.ofNullable(request.getStartDate()).ifPresent(budget::setStartDate);
        Optional.ofNullable(request.getEndDate()).ifPresent(budget::setEndDate);
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudgetById(UUID id) {
        Budget budget = getBudgetById(id);
        budgetRepository.deleteById(budget.getId());
    }
}
