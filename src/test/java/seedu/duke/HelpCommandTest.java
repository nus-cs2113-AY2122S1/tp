package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.model.budget.BudgetManager;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.model.financemanager.NormalFinanceManager;
import seedu.duke.model.financemanager.RecurringFinanceManager;
import seedu.duke.storage.BudgetDataManager;
import seedu.duke.storage.DataManagerActions;
import seedu.duke.storage.NormalListDataManager;
import seedu.duke.storage.RecurringListDataManager;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    public static final String INDENT = "    ";
    public static final String SOLID_LINE = "_______________________________________________________________________\n";

    @Test
    void helpCommandTest_validInput_success() {
        NormalFinanceManager normalFinanceManager = new NormalFinanceManager();
        RecurringFinanceManager recurringFinanceManager = new RecurringFinanceManager();
        BudgetManager budgetManager = new BudgetManager();
        NormalListDataManager normalListDataManager = new NormalListDataManager();
        DataManagerActions dataManagerActions = new DataManagerActions();
        RecurringListDataManager recurringListDataManager = new RecurringListDataManager();
        BudgetDataManager budgetDataManager = new BudgetDataManager();
        Ui ui = new Ui();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(normalFinanceManager, recurringFinanceManager, budgetManager, normalListDataManager,
                dataManagerActions, recurringListDataManager, budgetDataManager, ui);

        String expectedOutput = "Available tags: n/NAME d/DATE a/AMOUNT c/CATEGORY_NUMBER i/INTERVAL e/END_DATE\n"
                + "Order of tags does not matter.\n"
                + "Square brackets \"[ ]\" identifies an optional argument.\n"
                + "List of commands available.\n"
                + SOLID_LINE
                + "KEYING IN ENTRIES. Type \"cat\" to view category number.\n"
                + "- add n/NAME a/AMOUNT [d/DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Add expense. Example: add n/chicken rice a/3.50 d/2021-09-30 c/1\n"
                + "- add income n/NAME a/AMOUNT [d/DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Add income. Example: add income n/payday a/400 d/2021-10-10 c/1\n"
                + "- delete [n/NAME] [a/AMOUNT] [d/DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Delete entries using keyword search. Needs at least 1 tag. Example: delete n/chicken\n"
                + "- edit [n/NAME] [a/AMOUNT] [d/DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Edit entries using keyword search. Needs at least 1 tag. Example: edit n/chicken\n"
                + SOLID_LINE
                + "RECURRING EXPENSES AND INCOME. Type \"cat\" to view category number.\n"
                + "Similar to keying in entries, but includes interval(mandatory for adding), endDate(optional)\n"
                + "Commands requires a \"R\", \n"
                + "e.g. addR, addR income, deleteR, editR\n\n"
                + "- addR n/NAME a/AMOUNT i/INTERVAL [d/DATE] [e/END_DATE] [c/CATEGORY_NUMBER] \n"
                + INDENT + "Add recurring expenses. Example: add n/spotify subscription a/10 i/MONTH d/2021-09-30 c/1\n"
                + "- addR income n/NAME a/AMOUNT i/INTERVAL [d/DATE] [e/END_DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Add recurring income. Example: add n/payday a/400 i/year d/2021-10-10 c/1\n"
                + "- deleteR [n/NAME] [a/AMOUNT] [i/INTERVAL] [d/DATE] [e/END_DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Delete recurring entries using keyword search. Needs at least 1 tag. "
                + "Example: deleteR n/spotify\n"
                + "- editR [n/NAME] [a/AMOUNT] [i/INTERVAL] [d/DATE] [e/END_DATE] [c/CATEGORY_NUMBER]\n"
                + INDENT + "Edit recurring entries using keyword search. Needs at least 1 tag. "
                + "Example: editR n/spotify\n"
                + SOLID_LINE
                + "DELETE ALL ENTRIES\n"
                + "- deleteAll [normal] [recurring]\n"
                + INDENT + "Only deletes all entries of the corresponding type.\n"
                + INDENT + "If no modifiers are specified, it defaults to deleting all entries regardless of type.\n"
                + SOLID_LINE
                + "VIEWING ENTRIES\n"
                + "- Format: view [income] [expense] [by SORTTYPE] [month MONTH] [year YEAR] [from STARTDATE [ENDDATE]]"
                + "[up/ascending]\n"
                + INDENT + "View entries sorted by name, date, amount or category, in ascending or descending order. "
                + "Example: view by amount ascending\n"
                + INDENT + "View a certain month or year or specific date range. "
                + "Example: view month 5 year 2021 from 2021-05-10\n"
                + SOLID_LINE
                + "BUDGETING. Type \"cat\" to view category number.\n"
                + "- set c/CATEGORY_NUMBER a/AMOUNT\n"
                + INDENT + "Set spending limit for individual category. Example: set c/0 100\n"
                + "- budget\n"
                + INDENT + "View current month's expenditure and budget\n"
                + SOLID_LINE
                + "UTILITIES\n"
                + "- cat\n"
                + INDENT + "View categories and category number\n"
                + "- exit\n"
                + INDENT + "Exits the app\n"
                + SOLID_LINE
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
