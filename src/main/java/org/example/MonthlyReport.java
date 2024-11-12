package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    HashMap<Integer, ArrayList<Item>> monthlyReportMap = new HashMap<>();

    public void readAllMonthlyReports(Integer monthNumber, String fileName) {
    FileReader fileReader = new FileReader();
    ArrayList<String> linesMonth = fileReader.readFileContents(fileName);
    ArrayList<Item> items = new ArrayList<>();

        for (String line : linesMonth) {
            String[] columns = line.split(",");
            if (columns.length < 4 || !isNumeric(columns[2]) || !isNumeric(columns[3])) {
                continue;
            }
            String itemName = columns[0];
            boolean isExpense = Boolean.parseBoolean(columns[1]);
            int quantity = Integer.parseInt(columns[2]);
            int unitPrice = Integer.parseInt(columns[3]);
            Item item = new Item(itemName, isExpense, quantity, unitPrice);
            items.add(item);
        }
        monthlyReportMap.put(monthNumber, items);
}

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void printMonthlyReports(Integer monthNumber) {
        if (monthlyReportMap.isEmpty()) {
            System.out.println("Необходимо считать данные из файла с месячным отчетом");
            return;
        }
            System.out.println(monthsNames(monthNumber));
            printMaxProfit(getItemList(monthNumber));
            printMaxExpense(getItemList(monthNumber));
    }

    public String monthsNames (Integer monthNumber) {
        if (monthNumber == 1) {
            return "Январь";
        } else if (monthNumber == 2) {
            return "Февраль";
        } else {
            return "Март";
        }
    }

    public ArrayList<Item> getItemList (Integer monthNumber) {
        return monthlyReportMap.get(monthNumber);
    }

    public void printMaxProfit (ArrayList<Item> itemList) {
        int maxProfit = 0;
        String maxProfitItem = "";
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (!item.getExpense()) {
                int sum = item.getQuantity() * item.getUnitPrice();
                if (sum > maxProfit) {
                    maxProfit = sum;
                    maxProfitItem = item.getItemName();
                }
            }
        }
        System.out.println("Самый прибыльный товар: " + maxProfitItem + " " + maxProfit);
    }

    public void printMaxExpense (ArrayList<Item> itemList) {
        int maxExpense = 0;
        String maxExpenseItem = "";
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.getExpense()) {
                int sum = item.getQuantity() * item.getUnitPrice();
                if (sum > maxExpense) {
                    maxExpense = sum;
                    maxExpenseItem = item.getItemName();
                }
            }
        }
        System.out.println("Самая большая трата: " + maxExpenseItem + " " + maxExpense);
    }
}