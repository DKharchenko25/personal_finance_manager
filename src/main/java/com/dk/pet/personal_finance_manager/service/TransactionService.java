package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.dto.transaction.CreateTransactionRequest;
import com.dk.pet.personal_finance_manager.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionService {

    Transaction getTransactionById(UUID id);

    Page<Transaction> getAllTransactionsByUserId(UUID userId,
                                                 UUID categoryId,
                                                 LocalDateTime startDate,
                                                 LocalDateTime endDate,
                                                 Pageable pageable);

    Transaction createTransaction(CreateTransactionRequest request);

    void deleteTransactionById(UUID id);
}
