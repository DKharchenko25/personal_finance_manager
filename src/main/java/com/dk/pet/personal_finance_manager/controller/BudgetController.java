package com.dk.pet.personal_finance_manager.controller;

import com.dk.pet.personal_finance_manager.dto.budget.BudgetResponse;
import com.dk.pet.personal_finance_manager.dto.budget.CreateBudgetRequest;
import com.dk.pet.personal_finance_manager.dto.budget.UpdateBudgetRequest;
import com.dk.pet.personal_finance_manager.facade.BudgetFacade;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/budgets")
@Validated
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetFacade budgetFacade;

    @GetMapping
    public ResponseEntity<Page<BudgetResponse>> getAllBudgets(@RequestParam(required = false) UUID categoryId,
                                                              @RequestParam(required = false) LocalDateTime startDate,
                                                              @RequestParam(required = false) LocalDateTime endDate,
                                                              Pageable pageable) {
        UUID userId = UUID.randomUUID(); // TODO: get userId from security context
        Page<BudgetResponse> budgets = budgetFacade.getAllBudgetsByUserId(userId, categoryId, startDate, endDate, pageable);
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> getBudgetById(@PathVariable UUID budgetId) {
        UUID userId = UUID.randomUUID(); // TODO: get userId from security context
        BudgetResponse budget = budgetFacade.getBudgetByIdAndUserId(budgetId, userId);
        return ResponseEntity.ok(budget);
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody @Valid CreateBudgetRequest request) {
        BudgetResponse response = budgetFacade.createBudget(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> updateBudget(@PathVariable UUID budgetId,
                                                       @RequestBody @Valid UpdateBudgetRequest request) {
        BudgetResponse response = budgetFacade.updateBudgetById(budgetId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudgetById(@PathVariable UUID budgetId) {
        budgetFacade.deleteBudgetById(budgetId);
        return ResponseEntity.noContent().build();
    }

}
