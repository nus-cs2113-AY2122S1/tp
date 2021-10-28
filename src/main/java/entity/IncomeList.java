package entity;

import java.util.ArrayList;

public class IncomeList {
    private static ArrayList<Income> incomes = new ArrayList<>();

    public static void addIncome(Income newIncome) {
        incomes.add(newIncome);
    }

    public static void deleteIncome(String incomeName) {
        for (int i = 0; i < incomes.size(); i++) {
            if (incomes.get(i).getDescription().contains(incomeName)) {
                incomes.remove(i);
            }
        }
    }

    public static void deleteIncome(int incomeIndex) {
        incomes.remove(incomeIndex);
    }

    public static ArrayList<Income> getIncomes() {
        return incomes;
    }

    public static void updateIncome(String incomeName, Double incomeValue) {
        for (int i = 0; i < incomes.size(); i++) {
            if (incomes.get(i).getDescription().contains(incomeName)) {
                incomes.get(i).updateValue(incomeValue);
                break;
            }
        }
    }
}
