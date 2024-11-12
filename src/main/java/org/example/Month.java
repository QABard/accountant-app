package org.example;

public class Month {
    int monthNumber;
    int amount;
    boolean isExpense;

    Month(int monthNumber, int amount, boolean isExpense) {
        this.monthNumber = monthNumber;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getExpense() {
        return isExpense;
    }
}