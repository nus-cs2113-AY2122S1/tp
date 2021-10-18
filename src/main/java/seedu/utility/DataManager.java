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
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves entries that StonksXD is currently tracking into a csv file so that entries can be saved and be easily
 * manipulated by user.
 * Also loads all saved entries when a new instance of StonksXD starts.
 */
public class DataManager {
    
    private static final String FILE_NAME = "./StonksXD_Data.csv";

    /**
     * Saves all entries StonksXD is currently tracking into a csv file StonksXD_Data.csv.
     * This allows users to not lose all their entries when program closes.
     * 
     * @param parser Does the conversion from entries to data for saving.
     * @param financialTracker Provides the ArrayList of expenses and the ArrayList of incomes.
     * @param ui Prints saving error.
     */
    public void save(Parser parser, FinancialTracker financialTracker, Ui ui) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            String data;
            String newline = "\n";

            // Categories header for the CSV file
            buffer.write("entry_type, entry_description, amount, category, date");
            buffer.write(newline);
            ArrayList<Expense> expenses = financialTracker.getExpenses();
            for (Expense expense : expenses) {
                data = parser.convertExpenseToData(expense);
                buffer.write(data);
                buffer.write(newline);
            }
            ArrayList<Income> incomes = financialTracker.getIncomes();
            for (Income income : incomes) {
                data = parser.convertIncomeToData(income);
                buffer.write(data);
                buffer.write(newline);
            }

            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_ENTRIES);
        }
    }

    /**
     * Loads all entries from StonksXD_Data.csv into StonksXD.
     * This allows users to not lose all their entries when the previous instance of StonksXD closed.
     *
     * @param parser Does the conversion from data in csv file to income and expense entries.
     * @param financialTracker Stores all expenses and incomes after being converted from data format.
     * @param ui Prints loading errors.
     */
    public void load(Parser parser, FinancialTracker financialTracker, Ui ui) {
        boolean hasCorruptedLines = false;
        FileInputStream fis;
        try {
            fis = new FileInputStream(FILE_NAME);
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
            } catch (InvalidExpenseAmountException | InvalidExpenseDataFormatException | DateTimeException ee) {
                try {
                    Income income = parser.convertDataToIncome(data);
                    financialTracker.addIncome(income);
                } catch (InvalidIncomeAmountException | InvalidIncomeDataFormatException | DateTimeException ie) {
                    hasCorruptedLines = true;
                }
            }
        }
        
        if (hasCorruptedLines) {
            ui.printError(Messages.HAS_CORRUPTED_DATA_ENTRIES);
        } 
    }
}
