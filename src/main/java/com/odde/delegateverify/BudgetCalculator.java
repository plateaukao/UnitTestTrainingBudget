package com.odde.delegateverify;

import java.time.LocalDate;
import java.time.YearMonth;

public class BudgetCalculator {

    Budget budget;
    public class Budget {

        public int getBudget(int year, int month) {

            return 0;
        }
    }

    public BudgetCalculator(Budget budget) {
        this.budget = budget;
    }

    public int query(LocalDate startDate, LocalDate endDate) {

        int total = 0;
        // same full month
        LocalDate pivotStartDate = startDate;
        LocalDate pivotDate = getPivotDate(startDate);
        while (!pivotDate.isAfter(endDate)) {
            int monthBudget = budget.getBudget(pivotDate.getYear(), pivotDate.getMonthValue());
            int days = pivotDate.getDayOfMonth() - pivotStartDate.getDayOfMonth() + 1;
            total +=  monthBudget * days / getMonthLength(pivotDate);

            if(pivotDate.equals(endDate)) return total;
            // move forward
            pivotDate = pivotDate.plusMonths(1);
            pivotStartDate = LocalDate.of(pivotDate.getYear(), pivotDate.getMonth(), 1);
        }

        // add last part if necessary
        if (endDate.getMonthValue() == pivotDate.getMonthValue()) {
            int monthBudget = budget.getBudget(endDate.getYear(), endDate.getMonthValue());
            int days = endDate.getDayOfMonth() - startDate.getDayOfMonth() + 1;
            total +=  monthBudget * days / getMonthLength(pivotDate);
        }

        return total;
    }

    private LocalDate getPivotDate(LocalDate localDate) {
        int days = getMonthLength(localDate);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), days);
    }

    private int getMonthLength(LocalDate localDate) {
        YearMonth yearMonthObject = YearMonth.of(localDate.getYear(), localDate.getMonth().getValue());
        return yearMonthObject.lengthOfMonth();
    }

}
