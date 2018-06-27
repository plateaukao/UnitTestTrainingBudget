package com.odde.delegateverify;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetCalculatorTest {
    private BudgetCalculator.Budget budget = mock(BudgetCalculator.Budget.class);
    private BudgetCalculator calculator = new BudgetCalculator(budget);

    @Test
    public void one_month() {
        givenBudget();
        assertQueryEquals(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 31), 31);
    }

    private void givenBudget() {
        when(budget.getBudget(2018, 1)).thenReturn(31);
        when(budget.getBudget(2018, 2)).thenReturn(56);
        when(budget.getBudget(2018, 3)).thenReturn(93);
    }

    @Test
    public void multiple_months() {
        givenBudget();
        assertQueryEquals(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 3, 31), 180);
    }

    @Test
    public void within_feb() {
        givenBudget();
        assertQueryEquals(LocalDate.of(2018, 2, 15), LocalDate.of(2018, 2, 25), 22);
    }

    private void assertQueryEquals(LocalDate startDate, LocalDate endDate, int budgetValue) {
        assertThat(calculator.query(startDate, endDate)).isEqualTo(budgetValue);
    }
}