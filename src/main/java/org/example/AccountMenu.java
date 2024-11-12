package org.example;

import java.util.Scanner;

public class AccountMenu {
    public static void runAccountMenu() {
    Scanner scanner = new Scanner(System.in);
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();
    CheckBalance checkBalance = new CheckBalance(monthlyReport, yearlyReport);

     while (true) {
         printMenu();
         int command = scanner.nextInt();
         scanner.nextLine();
         String password;

         if (command == 1) {
             monthlyReport.readAllMonthlyReports(1,"m.202101.csv");
             monthlyReport.readAllMonthlyReports(2,"m.202102.csv");
             monthlyReport.readAllMonthlyReports(3,"m.202103.csv");
             if (!monthlyReport.monthlyReportMap.isEmpty()) {
                 System.out.println("Месячные отчеты считаны");
             }
         }
         else if (command == 2) {
             yearlyReport.readAllYearlyReports(2021, "y.2021.csv");
             if (!yearlyReport.yearlyReportMap.isEmpty()) {
                 System.out.println("Годовой отчет считан");
             }
         }
         else if (command == 3) {
             checkBalance.runCheckBalance(1,2,3,2021);
             System.out.println("Сверка отчетов");
         }
         else if (command == 4) {
             monthlyReport.printMonthlyReports(1);
             monthlyReport.printMonthlyReports(2);
             monthlyReport.printMonthlyReports(3);
         }
         else if (command == 5) {
             yearlyReport.printYearlyReport(2021);
         }
         else if (command == 6) {
             System.out.println("Для выхода из программы введите специальный код");
             password = scanner.nextLine().toLowerCase();
             if (!password.equals("qwerty")) {
                 System.out.println("Неправильный пароль");
             } else {
                 break;
             }
         }
         else {
             System.out.println("Извините, такой команды нет");
         }
     }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход из программы");
    }
}