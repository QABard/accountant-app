package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    HashMap<Integer, ArrayList<Month>> yearlyReportMap = new HashMap<>();

    public void readAllYearlyReports(Integer yearNumber, String fileName) {
        FileReader fileReader = new FileReader();
        ArrayList<String> linesYear = fileReader.readFileContents(fileName);
        ArrayList<Month> months = new ArrayList<>();

        for (String line : linesYear) {
            String[] columns = line.split(",");
            if (columns.length < 3 || !isNumeric(columns[0]) || !isNumeric(columns[1])) {
                continue;
            }
            int monthNumber = Integer.parseInt(columns[0]);
            int amount = Integer.parseInt(columns[1]);
            boolean isExpense = Boolean.parseBoolean(columns[2]);

            Month month = new Month(monthNumber, amount,isExpense);
            months.add(month);
        }
        yearlyReportMap.put(yearNumber, months);
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void printYearlyReport (Integer yearNumber) {
        if (yearlyReportMap.isEmpty()) {
            System.out.println("Необходимо считать данные из файла с годовым отчетом");
            return;
        }
        System.out.println("Отчет за " + yearNumber + " год");
        eachMonthProfit(getMonthList(yearNumber));
        averageSales(getMonthList(yearNumber));
        averageExpenses(getMonthList(yearNumber));
    }

    public ArrayList<Month> getMonthList (Integer yearNumber) {
        return yearlyReportMap.get(yearNumber);
    }

    public static void eachMonthProfit (ArrayList<Month> monthsList) {

        ArrayList<Integer> salesList = new ArrayList<>();
        ArrayList<Integer> expensesList = new ArrayList<>();

        for (int i = 0; i < monthsList.size(); i++) {
        Month month = monthsList.get(i);
        int yearReportMonthNumber = i;
        int monthSales = 0;
        int monthExpense = 0;
        if (month.getExpense()) {
            monthExpense = month.getAmount();
        } else {
            monthSales = month.getAmount();
        }
        if (yearReportMonthNumber == 0 || yearReportMonthNumber == 1) {
            if (monthExpense > 0) {
                expensesList.add(monthExpense);
            } else {
                salesList.add(monthSales);
            }
        } else if (yearReportMonthNumber == 2 || yearReportMonthNumber == 3) {
            if (monthExpense > 0) {
                expensesList.add(monthExpense);
            } else {
                salesList.add(monthSales);
            }
        } else {
            if (monthExpense > 0) {
                expensesList.add(monthExpense);
            } else {
                salesList.add(monthSales);
            }
        }
    }
        int januaryProfit = salesList.get(0) - expensesList.get(0);
        int februaryProfit = salesList.get(1) - expensesList.get(1);
        int marchProfit = salesList.get(2) - expensesList.get(2);

        System.out.println("Прибыль в январе составила: " + januaryProfit);
        System.out.println("Прибыль в феврале составила: " + februaryProfit);
        System.out.println("Прибыль в марте составила: " + marchProfit);
    }

    public static void averageSales (ArrayList<Month> monthsList) {
        int sumSales = 0;
        for (int i = 0; i < monthsList.size(); i++) {
            Month month = monthsList.get(i);
            if (!month.getExpense()) {
                sumSales = sumSales + month.getAmount();
            }
        }
        int averageSales = sumSales / 3;
        System.out.println("Средний доход за год составил " + averageSales);
    }

    public static void averageExpenses (ArrayList<Month> monthsList) {
        int sumExpenses = 0;
        for (int i = 0; i < monthsList.size(); i++) {
            Month month = monthsList.get(i);
            if (month.getExpense()) {
                sumExpenses = sumExpenses + month.getAmount();
            }
        }
        int averageExpenses = sumExpenses / 3;
        System.out.println("Средний расход за год составил " + averageExpenses);
    }

    public static ArrayList<Integer> monthsSalesGethererYear (ArrayList<Month> monthsList) {
        ArrayList<Integer> monthsSalesFromYear = new ArrayList<>();

        for (int i = 0; i < monthsList.size(); i++) {
            Month month = monthsList.get(i);
            int monthSales = 0;
            if (!month.getExpense()) {
                monthSales = month.getAmount();
                monthsSalesFromYear.add(monthSales);
            }
        }
        return monthsSalesFromYear;
    }

    public static ArrayList<Integer> monthsExpensesGethererYear (ArrayList<Month> monthsList) {
        ArrayList<Integer> monthsExpensesFromYear = new ArrayList<>();

        for (int i = 0; i < monthsList.size(); i++) {
            Month month = monthsList.get(i);
            int monthExpense = 0;
            if (month.getExpense()) {
                monthExpense = month.getAmount();
                monthsExpensesFromYear.add(monthExpense);
            }
        }
        return monthsExpensesFromYear;
    }
}