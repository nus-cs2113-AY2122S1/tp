package service;

import entity.Income;
import entity.IncomeList;
import terminal.Ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IncomeManager implements LoadableManager{

    private static IncomeManager incomeMgr;

    private IncomeManager() {
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
    public void parse(String fileString) {

    }

    @Override
    public void toFileString() {

    }
}
