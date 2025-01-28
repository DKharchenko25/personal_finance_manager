package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionService {

    Transaction getTransactionById(UUID id);

    Transaction getTransactionByIdAndUserId(UUID id, UUID userId);

    Page<Transaction> getAllTransactionsByUserId(UUID userId,
                                                 UUID categoryId,
                                                 LocalDateTime startDate,
                                                 LocalDateTime endDate,
                                                 Pageable pageable);

    Transaction createTransaction(Transaction transaction, UUID userId, UUID categoryId);

    void deleteTransactionById(UUID id);
}
