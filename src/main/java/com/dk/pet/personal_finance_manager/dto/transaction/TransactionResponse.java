package com.dk.pet.personal_finance_manager.dto.transaction;

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
public class TransactionResponse {
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private LocalDateTime date;
    private BigDecimal amount;
    private String description;
}
