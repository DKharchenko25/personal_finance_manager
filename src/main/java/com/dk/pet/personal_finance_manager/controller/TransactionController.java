package com.dk.pet.personal_finance_manager.controller;

import com.dk.pet.personal_finance_manager.dto.transaction.CreateTransactionRequest;
import com.dk.pet.personal_finance_manager.dto.transaction.TransactionResponse;
import com.dk.pet.personal_finance_manager.facade.TransactionFacade;
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

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionFacade transactionFacade;

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> getAllTransactionsByUserId(@RequestParam(required = false) UUID categoryId,
                                                                                @RequestParam(required = false) LocalDateTime startDate,
                                                                                @RequestParam(required = false) LocalDateTime endDate,
                                                                                Pageable pageable) {
        UUID userId = UUID.randomUUID(); // TODO: get userId from security context
        Page<TransactionResponse> transactions = transactionFacade.getAllTransactionsByUserId(userId, categoryId, startDate, endDate, pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable UUID transactionId) {
        UUID userId = UUID.randomUUID(); // TODO: get userId from security context
        TransactionResponse transaction = transactionFacade.getTransactionByIdAndUserId(transactionId, userId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid CreateTransactionRequest request) {
        TransactionResponse response = transactionFacade.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable UUID transactionId) {
        transactionFacade.deleteTransactionById(transactionId);
        return ResponseEntity.noContent().build();
    }
}
