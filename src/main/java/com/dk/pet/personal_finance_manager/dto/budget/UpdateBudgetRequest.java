package com.dk.pet.personal_finance_manager.dto.budget;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBudgetRequest {
    private UUID categoryId;
    private BigDecimal amount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
