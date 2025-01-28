package com.dk.pet.personal_finance_manager.service;

import com.dk.pet.personal_finance_manager.entity.Transaction;
import com.dk.pet.personal_finance_manager.exception.ResourceNotFoundException;
import com.dk.pet.personal_finance_manager.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction with id %s not found", id)));
    }

    @Override
    public Transaction getTransactionByIdAndUserId(UUID id, UUID userId) {
        return transactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Transaction with id %s not found for user %s", id, userId)
                ));
    }

    @Override
    public Page<Transaction> getAllTransactionsByUserId(UUID userId, UUID categoryId,
                                                        LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return transactionRepository.findAllByUserId(userId, categoryId, startDate, endDate, pageable);
    }

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction, UUID userId, UUID categoryId) {
        transaction.setUser(userService.getUserById(userId));
        transaction.setCategory(categoryService.getCategoryById(categoryId));
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(UUID id) {
        Transaction transaction = getTransactionById(id);
        transactionRepository.deleteById(transaction.getId());
    }
}
