package seedu.utility;

import seedu.duke.Parser;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeDataFormatException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves entries that StonksXD is currently tracking into a csv file so that entries can be saved and be easily
 * manipulated by user.
 * Also loads all saved entries when a new instance of StonksXD starts.
 */
public class DataManager {
    private final String FILENAME = "./StonksXD_Data.csv";

    public void save(Parser parser, FinancialTracker financialTracker, Ui ui) {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            ArrayList<Expense> expenses = financialTracker.listExpenses();
            ArrayList<Income> incomes = financialTracker.listIncomes();
            String data;
            String NEWLINE = "\n";

            buffer.write("entry_type, entry_description, amount");
            buffer.write(NEWLINE);

            for (Expense expense : expenses) {
                data = parser.convertExpenseToData(expense);
                buffer.write(data);
                buffer.write(NEWLINE);
            }
            for (Income income : incomes) {
                data = parser.convertIncomeToData(income);
                buffer.write(data);
                buffer.write(NEWLINE);
            }

            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_ENTRIES);
        }
    }

    public void load(Parser parser, FinancialTracker financialTracker, Ui ui) {
        boolean hasCorruptedLines = false;
        FileInputStream fis;
        try {
            fis = new FileInputStream(FILENAME);
        } catch (FileNotFoundException e) {
            ui.printError(Messages.UNABLE_TO_FIND_DATA_FILE);
            return;
        }
        Scanner sc = new Scanner(fis);
        sc.nextLine();

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            try {
                Expense expense = parser.convertDataToExpense(data);
                financialTracker.addExpense(expense);
            } catch (InvalidExpenseAmountException | InvalidExpenseDataFormatException ee) {
                try {
                    Income income = parser.convertDataToIncome(data);
                    financialTracker.addIncome(income);
                } catch (InvalidIncomeAmountException | InvalidIncomeDataFormatException ie) {
                    hasCorruptedLines = true;
                }
            }
        }
        
        if (hasCorruptedLines) {
            ui.printError(Messages.HAS_CORRUPTED_DATA_ENTRIES);
        }
    }
}
