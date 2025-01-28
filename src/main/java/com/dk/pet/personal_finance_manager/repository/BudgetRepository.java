package com.dk.pet.personal_finance_manager.repository;

import com.dk.pet.personal_finance_manager.entity.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId " +
            "AND (:categoryId IS NULL OR b.category.id = :categoryId) " +
            "AND (:startDate IS NULL OR b.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR b.endDate <= :endDate)")
    Page<Budget> findAllByUserId(UUID userId,
                                 UUID categoryId,
                                 LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 Pageable pageable);

    Optional<Budget> findByIdAndUserId(UUID id, UUID userId);
}
