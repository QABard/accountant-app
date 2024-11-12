package org.example;

import java.util.ArrayList;

public class CheckBalance {
    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;

    public CheckBalance(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

   public void runCheckBalance (Integer monthNumber1, Integer monthNumber2, Integer monthNumber3, Integer yearNumber) {
        if (monthlyReport.monthlyReportMap.isEmpty()) {
           System.out.println("Необходимо считать месячные отчеты из файлов");
       } else if (yearlyReport.yearlyReportMap.isEmpty()) {
           System.out.println("Необходимо считать годовой отчет из файла");
       } else {
            compareReports(monthNumber1, monthNumber2, monthNumber3);
       }
   }


   public ArrayList<Integer> expensesMonthsGetherer (Integer monthNumber1, Integer monthNumber2, Integer monthNumber3) {
       ArrayList<Integer> expensesMonths = new ArrayList<>();
       expensesMonths.add(expensesMonth(monthNumber1));
       expensesMonths.add(expensesMonth(monthNumber2));
       expensesMonths.add(expensesMonth(monthNumber3));
       return expensesMonths;
   }

   public ArrayList<Integer> salesMonthsGetherer (Integer monthNumber1, Integer monthNumber2, Integer monthNumber3) {
       ArrayList<Integer> salesMonths = new ArrayList<>();
       salesMonths.add(salesMonth(monthNumber1));
       salesMonths.add(salesMonth(monthNumber2));
       salesMonths.add(salesMonth(monthNumber3));
       return salesMonths;
   }

   public Integer salesMonth (Integer monthNumber) {
       ArrayList<Item> itemList = monthlyReport.getItemList(monthNumber);
       int sumSales = 0;
       for (int i = 0; i < itemList.size(); i++) {
           Item item = itemList.get(i);
           if (!item.getExpense()) {
               int tempSales = item.getUnitPrice() * item.getQuantity();
               sumSales = sumSales + tempSales;
           }
       }
       return sumSales;
   }

   public Integer expensesMonth (Integer monthNumber) {
       ArrayList<Item> itemList = monthlyReport.getItemList(monthNumber);
       int sumExpenses = 0;
       for (int i = 0; i < itemList.size(); i++) {
           Item item = itemList.get(i);
           if (item.getExpense()) {
               int tempExpense = item.getUnitPrice() * item.getQuantity();
               sumExpenses = sumExpenses + tempExpense;
           }
       }
       return sumExpenses;
   }

   public void compareReports (Integer monthNumber1, Integer monthNumber2, Integer monthNumber3) {
        if (!expensesMonthsGetherer(monthNumber1, monthNumber2, monthNumber3).get(0).equals(yearlyReport.monthsExpensesGethererYear(yearlyReport.getMonthList(2021)).get(0))) {
            System.out.println("Данные расходов за январь не сходятся");
        } else if (!salesMonthsGetherer(monthNumber1, monthNumber2, monthNumber3).get(0).equals(yearlyReport.monthsSalesGethererYear(yearlyReport.getMonthList(2021)).get(0))) {
            System.out.println("Данные продаж за январь не сходятся");
        } else if (!expensesMonthsGetherer(monthNumber1, monthNumber2, monthNumber3).get(1).equals(yearlyReport.monthsExpensesGethererYear(yearlyReport.getMonthList(2021)).get(1))) {
            System.out.println("Данные расходов за февраль не сходятся");
        } else if (!salesMonthsGetherer(monthNumber1, monthNumber2, monthNumber3).get(1).equals(yearlyReport.monthsSalesGethererYear(yearlyReport.getMonthList(2021)).get(1))) {
            System.out.println("Данные продаж за февраль не сходятся");
        } else if (!expensesMonthsGetherer(monthNumber1, monthNumber2, monthNumber3).get(2).equals(yearlyReport.monthsExpensesGethererYear(yearlyReport.getMonthList(2021)).get(2))) {
            System.out.println("Данные расходов за март не сходятся");
        } else if (!salesMonthsGetherer(monthNumber1, monthNumber2, monthNumber3).get(2).equals(yearlyReport.monthsSalesGethererYear(yearlyReport.getMonthList(2021)).get(2))) {
            System.out.println("Данные продаж за март не сходятся");
        } else {
            System.out.println("Ошибок при сверке отчетов не обнаружено");
        }
   }
}