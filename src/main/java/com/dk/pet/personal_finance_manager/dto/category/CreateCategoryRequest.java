package com.dk.pet.personal_finance_manager.dto.category;

import com.dk.pet.personal_finance_manager.entity.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {
    @NotBlank(message = "{validation.category.name.notBlank}")
    private String name;
    @NotBlank(message = "{validation.category.type.notNull}")
    private Type type;
    @NotNull(message = "{validation.userId.notNull}")
    private UUID userId;
}
