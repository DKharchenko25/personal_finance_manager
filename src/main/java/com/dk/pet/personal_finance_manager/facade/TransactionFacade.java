package com.dk.pet.personal_finance_manager.facade;

import com.dk.pet.personal_finance_manager.dto.transaction.CreateTransactionRequest;
import com.dk.pet.personal_finance_manager.dto.transaction.TransactionResponse;
import com.dk.pet.personal_finance_manager.entity.Transaction;
import com.dk.pet.personal_finance_manager.mapper.TransactionMapper;
import com.dk.pet.personal_finance_manager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionFacade {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionResponse getTransactionByIdAndUserId(UUID id, UUID userId) {
        Transaction transaction = transactionService.getTransactionByIdAndUserId(id, userId);
        return transactionMapper.entityToResponse(transaction);
    }

    public Page<TransactionResponse> getAllTransactionsByUserId(UUID userId, UUID categoryId,
                                                                LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return transactionService.getAllTransactionsByUserId(userId, categoryId, startDate, endDate, pageable)
                .map(transactionMapper::entityToResponse);
    }

    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        Transaction transaction = transactionMapper.requestToEntity(request);
        Transaction createdTransaction = transactionService.createTransaction(transaction,
                request.getUserId(), request.getCategoryId());
        return transactionMapper.entityToResponse(createdTransaction);
    }

    public void deleteTransactionById(UUID id) {
        transactionService.deleteTransactionById(id);
    }
}
