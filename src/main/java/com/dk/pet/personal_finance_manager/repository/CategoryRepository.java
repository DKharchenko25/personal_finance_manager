package com.dk.pet.personal_finance_manager.repository;

import com.dk.pet.personal_finance_manager.entity.Category;
import com.dk.pet.personal_finance_manager.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Page<Category> findAllByUserIdAndType(UUID userId, Type type, Pageable pageable);
}
