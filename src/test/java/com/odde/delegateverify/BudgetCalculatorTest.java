package com.odde.delegateverify;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetCalculatorTest {
    private BudgetCalculator.Budget budget = mock(BudgetCalculator.Budget.class);
    private BudgetCalculator calculator = new BudgetCalculator(budget);

    @Before
    public void setup() {
        givenBudget();
    }

    private void givenBudget() {
        when(budget.getBudget(2018, 1)).thenReturn(31);
        when(budget.getBudget(2018, 2)).thenReturn(56);
        when(budget.getBudget(2018, 3)).thenReturn(93);
    }

    @Test
    public void one_month() {
        assertQueryEquals(31, of(2018, 1, 31), of(2018, 1, 1));
    }

    @Test
    public void multiple_months() {
        assertQueryEquals(180, of(2018, 3, 31), of(2018, 1, 1));
    }

    @Test
    public void within_one_month() {
        assertQueryEquals(22, of(2018, 2, 25), of(2018, 2, 15));
    }

    @Test
    public void across_months() {
        assertQueryEquals(73, of(2018, 3, 15), of(2018, 2, 15));
    }

    @Test
    public void no_budget_case() {
        assertQueryEquals(129, of(2018, 4, 20), of(2018, 2, 11));
    }

    @Test
    public void another_no_budget_case() {
        assertQueryEquals(0, of(2018, 5, 30), of(2018, 5, 1));
    }

    private void assertQueryEquals(int budgetValue, LocalDate endDate, LocalDate startDate) {
        assertThat(calculator.query(startDate, endDate)).isEqualTo(budgetValue);
    }
}