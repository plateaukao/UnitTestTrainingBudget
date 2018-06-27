package com.odde.delegateverify;

import javafx.util.Pair;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

public class BudgetCalculator {

    Map<Pair<Integer, Integer>, Integer> bbudget;

    Budget budget;
    public class Budget {
        public int getBudget(int year, int month) {
            return 0;
        }
    }

    public BudgetCalculator(Map bbudget) {
        this.bbudget = bbudget;
    }

    public BudgetCalculator(Budget budget) {
        this.budget = budget;
    }

    public int query(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) throw new InvalidParameterException();

        int total = 0;
        // same full month
        LocalDate pivotStartDate = startDate;
        LocalDate pivotDate = (startDate.getMonthValue() == endDate.getMonthValue())? endDate : getPivotDate(startDate);

        while (!pivotDate.isAfter(endDate)) {
            int monthBudget = budget.getBudget(pivotDate.getYear(), pivotDate.getMonthValue());
            int days = pivotDate.getDayOfMonth() - pivotStartDate.getDayOfMonth() + 1;
            total +=  monthBudget * days / getMonthLength(pivotDate);

            // move forward
            pivotDate = pivotDate.plusMonths(1);
            pivotStartDate = LocalDate.of(pivotDate.getYear(), pivotDate.getMonth(), 1);
            if( pivotDate.getMonthValue() == endDate.getMonthValue()){
                pivotDate = endDate;
            } else {
                //move to next month;
                pivotDate = LocalDate.of(pivotDate.getYear(), pivotDate.getMonth(), getMonthLength(pivotDate));
            }
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

    private boolean isSameYearMonth(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.getYear() == localDate2.getYear()
                && localDate1.getMonthValue() == localDate2.getMonthValue();
    }
}
