package service;

import entity.Income;
import entity.IncomeList;
import terminal.Ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IncomeManager {

    public static void addIncome(String incomeName, double incomeValue) {
        Format f = new SimpleDateFormat("dd/MM/yy");
        String today = f.format(new Date());
        Income newIncome = new Income(incomeName, incomeValue, today);
        IncomeList.addIncome(newIncome);
    }

    public static void deleteIncome(String incomeName) {
        IncomeList.deleteIncome(incomeName);
    }

    // index starts from 0
    public static void deleteIncome(int incomeNumber) {
        IncomeList.deleteIncome(incomeNumber - 1);
    }

    public static void listIncomes() {
        Ui ui = Ui.getUi();
        // the formatting needs to be aligned
        String incomeListHeader = "Id. | Name             | Value  | Date";

        ui.printMessage(incomeListHeader);
        ArrayList<Income> incomes = IncomeList.getIncomes();
        for (int i = 0; i < incomes.size(); i++) {
            ui.printMessage((i + 1) + ". \t| " + incomes.get(i));
        }
    }

    public static void updateIncome(String incomeName, Double incomeValue) {
        IncomeList.updateIncome(incomeName, incomeValue);
    }
}
