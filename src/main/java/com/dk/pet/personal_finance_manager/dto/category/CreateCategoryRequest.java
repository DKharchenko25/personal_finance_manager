package com.dk.pet.personal_finance_manager.dto.category;

import com.dk.pet.personal_finance_manager.entity.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {
    @NotBlank(message = "{validation.category.name.notBlank}")
    private String name;
    @NotBlank(message = "{validation.category.type.notNull}")
    private Type type;
}
