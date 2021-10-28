package service;

import entity.Expense;
import entity.ExpenseList;
import entity.Income;
import entity.IncomeList;
import storage.DataManager;
import terminal.Ui;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IncomeManager implements LoadableManager{

    private static IncomeManager incomeMgr;
    private String fileLabel;

    private IncomeManager() {
        fileLabel = "income";
    }

    public static IncomeManager getIncomeManager() {
        if (incomeMgr == null) {
            incomeMgr = new IncomeManager();
        }
        return incomeMgr;
    }

    public void addIncome(String incomeName, double incomeValue) {
        Format f = new SimpleDateFormat("dd/MM/yy");
        String today = f.format(new Date());
        Income newIncome = new Income(incomeName, incomeValue, today);
        IncomeList.addIncome(newIncome);
    }

    public void deleteIncome(String incomeName) {
        IncomeList.deleteIncome(incomeName);
    }

    // index starts from 0
    public void deleteIncome(int incomeNumber) {
        IncomeList.deleteIncome(incomeNumber - 1);
    }

    public void listIncomes() {
        Ui ui = Ui.getUi();
        // the formatting needs to be aligned
        String incomeListHeader = "Id. | Name             | Value  | Date";

        ui.printMessage(incomeListHeader);
        ArrayList<Income> incomes = IncomeList.getIncomes();
        for (int i = 0; i < incomes.size(); i++) {
            ui.printMessage((i + 1) + ". \t| " + incomes.get(i));
        }
    }

    @Override
    public void parse(String[] fileString) {
        for(String line : fileString) {
            String[] splitLine = line.split(";");

            String name = splitLine[0];
            Double value = Double.parseDouble(splitLine[1]);
            String date = splitLine[2];

            Income income = new Income(name, value, date);
            IncomeList.addIncome(income);
        }
    }

    @Override
    public String toFileString() {
        String fileString = "";
        ArrayList<Income> incomes = IncomeList.getIncomes();

        for (int i = 0; i < incomes.size(); i++) {
            Income income = incomes.get(i);
            String name = income.getDescription();
            String value = ((Double)income.getValue()).toString();
            String date = income.getDate();

            fileString += String.format("%s;%s;%s\n", name, value, date);
        }

        return fileString;
    }

    @Override
    public String getFileLabel() {
        return fileLabel;
    }

    public static void updateIncome(String incomeName, Double incomeValue) {
        IncomeList.updateIncome(incomeName, incomeValue);
    }
}
