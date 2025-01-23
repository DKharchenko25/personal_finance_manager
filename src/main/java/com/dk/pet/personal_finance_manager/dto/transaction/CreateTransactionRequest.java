package com.dk.pet.personal_finance_manager.dto.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class CreateTransactionRequest {
    @NotNull(message = "{validation.userId.notNull}")
    private UUID userId;
    @NotNull(message = "{validation.categoryId.notNull}")
    private UUID categoryId;
    @NotNull(message = "{validation.transaction.date.notNull}")
    private LocalDateTime date;
    @NotNull(message = "{validation.amount.notNull}")
    @Positive(message = "{validation.amount.positive}")
    private BigDecimal amount;
    private String description;
}
