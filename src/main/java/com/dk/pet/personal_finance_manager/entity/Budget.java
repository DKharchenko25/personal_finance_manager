package com.dk.pet.personal_finance_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "budgets")
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @NotNull(message = "Amount is mandatory")
    private BigDecimal amount;

    @NotNull(message = "Start date is mandatory")
    @Check(constraints = "start_date < end_date")
    private String startDate;

    @NotNull(message = "End date is mandatory")
    private String endDate;

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
