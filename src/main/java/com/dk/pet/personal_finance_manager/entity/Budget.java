package com.dk.pet.personal_finance_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "budgets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Budget {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull(message = "{validation.amount.notNull}")
    @Positive(message = "{validation.amount.positive}")
    private BigDecimal amount;

    @NotNull(message = "{validation.budget.startDate.notNull}")
    @Check(constraints = "start_date < end_date")
    private LocalDateTime startDate;

    @NotNull(message = "{validation.budget.endDate.notNull}")
    private LocalDateTime endDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(id, budget.id)
                && Objects.equals(user, budget.user)
                && Objects.equals(category, budget.category)
                && Objects.equals(amount, budget.amount)
                && Objects.equals(startDate, budget.startDate)
                && Objects.equals(endDate, budget.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, category, amount, startDate, endDate);
    }
}
