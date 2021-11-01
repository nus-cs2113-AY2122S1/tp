package seedu.utility;

import seedu.commands.general.CurrencyType;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.exceptions.BlankCurrencyTypeException;
import seedu.exceptions.ExpenseOverflowException;
import seedu.exceptions.IncomeOverflowException;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidCurrencyTypeException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidSettingsDataException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Saves entries (expenses and incomes) Stonks XD is tracking into a csv file.
 * Also saves Stonks XD settings into another csv file. Settings include: currency state, amount for each budget 
 * category and the reminder threshold value.
 */
public class DataManager {

    private static final String NEWLINE = System.lineSeparator();
    private static final String ENTRIES_FILE_NAME = "./StonksXD_Entries.csv";
    private static final String ENTRIES_CSV_HEADER = "entry_type,entry_description,amount,category,date";
    private static final String SETTINGS_FILE_NAME = "./StonksXD_Settings.csv";
    private static final String SETTINGS_CSV_HEADER = "currency,food,transport,medical,bills,entertainment," 
            + "misc,overall";
    private static final String BACKUP_ENTRIES_FILE_NAME = "./StonksXD_EntriesBackup.csv";
    private static final String BACKUP_SETTINGS_FILE_NAME = "./StonksXD_SettingsBackup.csv";
    private final Parser parser;
    private final FinancialTracker financialTracker;
    private final Ui ui;
    private final BudgetManager budgetManager;

    public DataManager(Parser parser, FinancialTracker financialTracker, Ui ui, BudgetManager budgetManager) {
        this.parser = parser;
        this.financialTracker = financialTracker;
        this.ui = ui;
        this.budgetManager = budgetManager;
    }

    /**
     * Saves all entries and settings. 
     * This method will be used more frequently as we typically want to save both entries and settings together.
     */
    public void saveAll() {
        saveEntries(ENTRIES_FILE_NAME);
        saveEntries(BACKUP_ENTRIES_FILE_NAME);
        saveSettings(SETTINGS_FILE_NAME);
        saveSettings(BACKUP_SETTINGS_FILE_NAME);
    }

    /**
     * Loads all entries and settings.
     * This method will be used more frequently as we typically want to load both entries and settings together.
     */
    public void loadAll() {
        loadEntries();
        loadSettings();
    }

    /**
     * Saves all entries StonksXD is currently tracking into a csv file.
     * This allows users to not lose all their entries when program closes.
     */
    private void saveEntries(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter buffer = new BufferedWriter(writer);
            String data;

            // Categories header for the CSV file
            buffer.write(ENTRIES_CSV_HEADER);
            buffer.write(NEWLINE);
            ArrayList<Expense> expenses = financialTracker.getExpenses();
            for (Expense expense : expenses) {
                data = parser.convertExpenseToData(expense);
                buffer.write(data);
                buffer.write(NEWLINE);
            }
            ArrayList<Income> incomes = financialTracker.getIncomes();
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

    /**
     * Loads all entries from StonksXD_Data.csv into StonksXD.
     * This allows users to not lose all their entries when the previous instance of StonksXD closed.
     */
    private void loadEntries() {
        boolean hasCorruptedLines = false;
        FileInputStream fis;
        try {
            fis = new FileInputStream(ENTRIES_FILE_NAME);
        } catch (FileNotFoundException e) {
            ui.printLoadingError(Messages.UNABLE_TO_FIND_DATA_FILE);
            return;
        }
        Scanner sc = new Scanner(fis);
        checkForEntriesFileHeader(sc);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            if (data.isBlank()) {
                continue;
            }
            try {
                Expense expense = parser.convertDataToExpense(data);
                financialTracker.addExpense(expense);
            } catch (InputException | InvalidExpenseDataFormatException 
                    | DateTimeParseException | ExpenseOverflowException e) {
                try {
                    Income income = parser.convertDataToIncome(data);
                    financialTracker.addIncome(income);
                } catch (InputException | InvalidIncomeDataFormatException 
                        | DateTimeParseException | IncomeOverflowException ie) {
                    hasCorruptedLines = true;
                }
            }
        }
        if (hasCorruptedLines) {
            ui.printLoadingError(Messages.HAS_CORRUPTED_DATA_ENTRIES);
        }
    }

    private void checkForEntriesFileHeader(Scanner sc) {
        String data;
        try {
            data = sc.nextLine();
        } catch (NoSuchElementException e) {
            ui.printLoadingError(Messages.INCOMPLETE_ENTRIES_CSV_HEADER_MESSAGE);
            return;
        }
        
        if (!data.equals(ENTRIES_CSV_HEADER)) {
            ui.printLoadingError(Messages.INCOMPLETE_ENTRIES_CSV_HEADER_MESSAGE);
        }
    }

    private void checkForSettingsFileHeader(Scanner sc) {
        String data;
        try {
            data = sc.nextLine();
        } catch (NoSuchElementException e) {
            ui.printLoadingError(Messages.INCOMPLETE_SETTINGS_CSV_HEADER_MESSAGE);
            return;
        }

        if (!data.equals(SETTINGS_CSV_HEADER)) {
            ui.printLoadingError(Messages.INCOMPLETE_SETTINGS_CSV_HEADER_MESSAGE);
        }
    }

    /**
     * Saves all settings into a csv file.
     * This allows users to not lose all their settings when program closes.
     */
    private void saveSettings(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter buffer = new BufferedWriter(writer);
            String data;
            
            buffer.write(SETTINGS_CSV_HEADER);
            buffer.write(NEWLINE);
            data = parser.convertSettingsToData(financialTracker, budgetManager);
            buffer.write(data);
            buffer.write(NEWLINE);
            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_SETTINGS);
        }
    }
    
    /**
     * Loads all settings from StonksXD_Settings.csv into StonksXD.
     * This allows users to not lose all their settings when the previous instance of 
     * StonksXD closed.
     */
    private void loadSettings() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(SETTINGS_FILE_NAME);
        } catch (FileNotFoundException e) {
            ui.printLoadingError(Messages.UNABLE_TO_FIND_SETTINGS_FILE);
            return;
        }

        Scanner sc = new Scanner(fis);
        try {
            checkForSettingsFileHeader(sc);
            String data = sc.nextLine();
            ArrayList<Double> budgetSettings = parser.convertDataToBudgetSettings(data);
            int budgetIndex = 0;
            for (ExpenseCategory category : ExpenseCategory.values()) {
                // Not expected to have a budget related to NULL
                if (category == ExpenseCategory.NULL) {
                    break;
                }
                budgetManager.setBudget(budgetSettings.get(budgetIndex), category);
                budgetIndex += 1;
            }
            CurrencyType currency = parser.convertDataToCurrencySetting(data);
            financialTracker.setCurrency(currency);
        } catch (NullPointerException | NumberFormatException | InvalidSettingsDataException 
                | InvalidCurrencyTypeException | BlankCurrencyTypeException | NoSuchElementException e) {
            ui.printLoadingError(Messages.HAS_CORRUPTED_SETTINGS);
        }
    }
}
