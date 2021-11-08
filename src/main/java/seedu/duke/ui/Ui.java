package seedu.duke.ui;

import seedu.duke.model.budget.Budget;
import seedu.duke.utility.MintException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.model.entries.Entry;
import seedu.duke.model.entries.Type;
import seedu.duke.model.entries.RecurringEntry;
import seedu.duke.model.entries.Interval;
import seedu.duke.model.entries.ExpenseCategory;
import seedu.duke.logic.parser.ValidityChecker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ui {
    protected static final String INDENT = "    ";
    public static final String SOLID_LINE = "_______________________________________________________________________\n";
    public static final String SUCCESSFUL_EDIT_MESSAGE = "Got it! I will update the fields accordingly!";
    public static final int MIN_NAME_INDENTATION = 4;
    public static final int MIN_AMOUNT_INDENTATION = 5;
    public static final int MIN_SPENDING_INDENTATION = 6;
    public static final int MIN_LIMIT_INDENTATION = 7;
    public static final int MIN_CAT_INDENTATION = 8;
    public static final int INDEX_CANCEL = -1;
    public static final String CANCEL_MESSAGE = " To cancel, type \"cancel\"";
    public static final String MISSING_FILE_MESSAGE = "Missing data detected! Creating the necessary files...";
    public static final String MISSING_FIELDS_MESSAGE = "There seems to be some extra/missing fields! "
            + "Invalid line deleted. We have reloaded the list!";
    public static final String GREETINGS = "Hello! I'm Mint" + System.lineSeparator() + "What can I do for you?";
    public static final String SHUTDOWN = "Goodbye! Hope to see you again soon!";

    public static final String FIRST_TIME_USER_MESSAGE = "You must be a first time user!\n"
            + "Welcome! To get you started, type \"help\" to get a list of commands you can use.\n"
            + "Alternatively, Type \"add n/NAME a/AMOUNT\" to add your very first item.\n"
            + "To see what you have added, type \"view\"!";
    public static final String RETRY_FILE_CREATION_MESSAGE = "Seems like a directory had a text file's extension... "
            + "Deleting that and trying again... ";
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printError(MintException e) {
        System.out.println(e.getMessage());
    }

    //@@author irvinseet
    public boolean hasUnsafeCharacters(String text) {
        Pattern pattern = Pattern.compile("[$&+,:;=\\\\?@#|'<>^*()%!]");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public String readUserInput() {
        if (in.hasNextLine()) {
            return in.nextLine().trim();
        }
        return null;
    }

    public void printGreetings() {
        System.out.println(GREETINGS);
    }

    public void shutdown() {
        System.out.println(SHUTDOWN);
    }

    //@@author irvinseet
    public void help() {
        System.out.println("Available tags: n/NAME d/DATE a/AMOUNT c/CATEGORY_NUMBER i/INTERVAL e/END_DATE\n"
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
        );
    }

    //@@author yanjia1777
    public void viewGivenList(ArrayList<Entry> list) {
        int maxNameLength = MIN_NAME_INDENTATION;
        int maxAmountLength = MIN_AMOUNT_INDENTATION;
        int maxCatLength = MIN_CAT_INDENTATION;
        System.out.println("Here is the list of items containing the keyword.");
        for (Entry entry : list) {
            if (entry.getName().length() > maxNameLength) {
                maxNameLength = entry.getName().length();
            }
            if (String.format("%,.2f", entry.getAmount()).length() > maxAmountLength) {
                maxAmountLength = String.format("%,.2f", entry.getAmount()).length();
            }
            if (entry.getCategory().name().length() > maxCatLength) {
                maxCatLength = entry.getCategory().name().length();
            }
        }
        System.out.println(" Index |   Type  | " + getMiddleIndented("Category", maxCatLength) + " |    Date    | "
                + getMiddleIndented("Name", maxNameLength) + " | "
                + getMiddleIndented("Amount", maxAmountLength + 1) + " | Every |   Until");
        int indexCount = 1;
        for (Entry entry : list) {
            printViewIndividualEntry(entry, maxCatLength, maxNameLength, maxAmountLength, indexCount);
            indexCount++;
        }
    }

    //@@author pos0414

    /**
     * Print out a given list and let the user choose the entry to process by entering the index of that entry.
     * @param filteredList List of entries the user can choose from
     * @param isDelete True if it is a delete process, false if it is an edit process
     * @return index of the entry to process. -1 if user cancels the process.
     */
    public int chooseItemToDeleteOrEdit(ArrayList<Entry> filteredList, boolean isDelete) {
        if (isDelete) {
            System.out.println("Enter the index of the item you want to delete." + CANCEL_MESSAGE);
        } else {
            System.out.println("Enter the index of the item you want to edit." + CANCEL_MESSAGE);
        }
        int index = 0;
        boolean proceedToDelete = false;

        String userInput = readUserInput();
        while (userInput != null && !proceedToDelete) {
            if (userInput.equals("cancel")) {
                return INDEX_CANCEL;
            }
            try {
                index = ValidityChecker.checkInvalidIndex(userInput, filteredList.size());
                proceedToDelete = true;
            } catch (MintException e) {
                System.out.println(e.getMessage() + CANCEL_MESSAGE);
            }
            if (!proceedToDelete) {
                userInput = readUserInput();
            }
        }
        return index - 1;
    }

    /**
     * Get confirmation from the user if the given entry is what the user wants to process.
     * @param entry Entry to be checked
     * @param isDelete True if it is a delete process, false if it is an edit process
     * @return True if the user confirms it is the entry to be processed, False if the user denies (cancel the process)
     */
    public boolean isConfirmedToDeleteOrEdit(Entry entry, boolean isDelete) {
        if (isDelete) {
            System.out.println("Is this what you want to delete?");
        } else {
            System.out.println("Is this what you want to edit?");
        }
        System.out.println(INDENT + entry);
        System.out.println("Type \"y\" if yes. Type \"n\" if not.");
        return isConfirmed();
    }

    //@@author yanjia1777
    public boolean isConfirmDeleteAll() {
        System.out.println("Are you sure you want to delete all entries?");
        System.out.println("Type \"y\" if yes. Type \"n\" if not.");
        return isConfirmed();
    }

    //@@author pos0414

    /**
     * Return True if user says "y", false if user says "n".
     * @return True if user says "y", false if user says "n"
     */
    public boolean isConfirmed() {
        String userInput = readUserInput();
        while (userInput != null) {
            switch (userInput.trim()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Sorry I don't understand what that means. "
                        + "Type \"y\" if yes. Type \"n\" if not.");
            }
            userInput = readUserInput();
        }
        return false;
    }

    //@@author yanjia1777
    public void deleteAllConfirmation() {
        System.out.println("All entries successfully deleted.");
    }

    public void deleteAborted() {
        System.out.println("Delete aborted.");
    }

    public void printNoMatchingEntryMessage() {
        System.out.println(MintException.ERROR_EXPENSE_NOT_IN_LIST);
    }

    public void printCancelMessage() {
        System.out.println("Ok. I have cancelled the process.");
    }

    public static void printOutcomeOfEditAttempt() {
        System.out.println(SUCCESSFUL_EDIT_MESSAGE);
    }

    //@@author irvinseet
    public void printCategoryList() {
        System.out.println("Here are the categories and its tag number\n"
                + "Expenses           | Income\n"
                + "c/0 FOOD           | c/0 ALLOWANCE\n"
                + "c/1 ENTERTAINMENT  | c/1 WAGES\n"
                + "c/2 TRANSPORTATION | c/2 SALARY\n"
                + "c/3 HOUSEHOLD      | c/3 INTERESTED\n"
                + "c/4 APPAREL        | c/4 INVESTMENT\n"
                + "c/5 BEAUTY         | c/5 COMMISSION\n"
                + "c/6 GIFT           | c/6 GIFT\n"
                + "c/7 OTHERS         | c/7 OTHERS\n");
    }

    public static void printMissingFileMessage() {
        System.out.println(MISSING_FILE_MESSAGE);
    }

    public static void printFirstTimeUserMessage() {
        System.out.println(FIRST_TIME_USER_MESSAGE);
    }

    public static void printRetryFileCreationMessage() {
        System.out.println(RETRY_FILE_CREATION_MESSAGE);
    }

    public static void printFieldsErrorMessage() {
        System.out.println(MISSING_FIELDS_MESSAGE);
    }

    //    public static void setLimitMessage(String catNumString, String amount) {
    //        int catNumInt = Integer.parseInt(catNumString);
    //        System.out.println("Set Limit of " + CategoryList.getCatName(catNumInt) + " to $" + amount);
    //    }

    //@@author Yitching
    public static StringBuilder constructErrorMessage(ArrayList<String> missingDelimiters) throws MintException {
        int index = 1;
        StringBuilder missingFieldsErrorMessage = new StringBuilder();
        missingFieldsErrorMessage.append(Parser.STRING_INCLUDE);
        for (String delimiter : missingDelimiters) {
            switch (delimiter.trim()) {
            case "n/":
                missingFieldsErrorMessage.append(index).append(Parser.SEPARATOR).append(Parser.STRING_DESCRIPTION);
                index++;
                break;
            case "d/":
                missingFieldsErrorMessage.append(index).append(Parser.SEPARATOR).append(Parser.STRING_DATE);
                index++;
                break;
            case "a/":
                missingFieldsErrorMessage.append(index).append(Parser.SEPARATOR).append(Parser.STRING_AMOUNT);
                index++;
                break;
            case "c/":
                missingFieldsErrorMessage.append(index).append(Parser.SEPARATOR).append(Parser.STRING_CATNUM);
                index++;
                break;
            case "i/":
                missingFieldsErrorMessage.append(index).append(Parser.SEPARATOR).append(Parser.STRING_INTERVAL);
                index++;
                break;
            case "e/":
                missingFieldsErrorMessage.append(index).append(Parser.SEPARATOR).append(Parser.STRING_END_DATE);
                index++;
                break;
            default:
                throw new MintException(MintException.ERROR_INVALID_COMMAND);
            }
        }
        return missingFieldsErrorMessage;
    }

    //@@author yanjia1777
    public int[] printView(ArrayList<Entry> outputArray, LocalDate fromDate, LocalDate endDate, double total) {
        int maxNameLength = MIN_NAME_INDENTATION;
        int maxAmountLength = MIN_AMOUNT_INDENTATION;
        int maxCatLength = MIN_CAT_INDENTATION;
        System.out.println("Here is the list of your entries:");
        if (fromDate != null) {
            System.out.println("Since " + fromDate + " to " + endDate + ":");
        }
        for (Entry entry : outputArray) {
            if (entry.getName().length() > maxNameLength) {
                maxNameLength = entry.getName().length();
            }
            if (String.format("%,.2f", entry.getAmount()).length() > maxAmountLength) {
                maxAmountLength = String.format("%,.2f", entry.getAmount()).length();
            }
            if (entry.getCategory().name().length() > maxCatLength) {
                maxCatLength = entry.getCategory().name().length();
            }
        }
        System.out.println("  Type  | " + getMiddleIndented("Category", maxCatLength) + " |    Date    | "
                + getMiddleIndented("Name", maxNameLength) + " | "
                + getMiddleIndented("Amount", maxAmountLength + 1) + " | Every |   Until");
        for (Entry entry : outputArray) {
            printViewIndividualEntry(entry, maxCatLength, maxNameLength, maxAmountLength, 0);
        }
        System.out.print(getIndent(maxNameLength + maxCatLength, 0, "")
                + "                Net Total: |");
        if (total < 0) {
            total = Math.abs(total);
            System.out.print("-$" + String.format("%,.2f", total));
        } else {
            System.out.print(" $" + String.format("%,.2f", total));
        }
        System.out.println();
        return new int[]{maxCatLength, maxNameLength, maxAmountLength};
    }

    private static void printViewIndividualEntry(Entry entry, int maxCatLength, int maxNameLength,
            int maxAmountLength, int indexInt) {
        String index = indexInt == 0 ? "" : "   " + indexInt + "   | ";
        String type = entry.getType() == Type.Expense ? entry.getType().toString() : entry.getType() + " ";
        String category = getMiddleIndented(entry.getCategory().name(), maxCatLength);
        String date = entry.getDate().toString();
        String name = getMiddleIndented(entry.getName(), maxNameLength);
        String amount = getRightIndented(String.format("%,.2f", entry.getAmount()), maxAmountLength);
        String negativeSign = entry.getType() == Type.Expense ? "-$" : " $";
        if (entry instanceof RecurringEntry) {
            String interval = entry.getInterval() == Interval.MONTH ? entry.getInterval().toString()
                    : entry.getInterval() + " ";
            String until = entry.getEndDate().toString();
            until = until.equals("2200-12-31") ? "Forever :D" : until;
            System.out.println(index + type + " | " + category + " | " + date + " | " + name + " |" + negativeSign
                    + amount + " | " + interval + " | " + until);
        } else {
            System.out.println(index + type + " | " + category + " | " + date + " | " + name + " |" + negativeSign
                    + amount + " |       |");
        }
    }

    public void printViewRecurring(ArrayList<Entry> entryList, int maxCatIndent, int maxNameIndent,
            int maxAmountIndent, boolean isViewAll, boolean isViewExpenseOrIncome) {
        if (!isViewAll) {
            System.out.println("Here is the list of recurring entries added to the above list:");
        } else if (isViewExpenseOrIncome) {
            System.out.print("Here is the list of applicable recurring entries, ");
            System.out.println("where some were added to the above list:");
        } else {
            System.out.println("Here is the list of all recurring entries, where some were added to the above list:");
        }
        for (Entry entry : entryList) {
            printViewIndividualEntry(entry, maxCatIndent, maxNameIndent, maxAmountIndent, 0);
        }
    }

    public static StringBuilder getIndent(int leftIndent, int rightIndent, String item) {
        StringBuilder itemWithIndent = new StringBuilder();
        while (leftIndent != 0) {
            itemWithIndent.append(" ");
            leftIndent--;
        }

        itemWithIndent.append(item);

        while (rightIndent != 0) {
            itemWithIndent.append(" ");
            rightIndent--;
        }
        return itemWithIndent;
    }

    public static String getRightIndented(String amount, int indent) {
        double length = amount.length();
        int rightIndent = (int) (indent - length);
        if (rightIndent < 0) {
            rightIndent = 0;
        }
        return getIndent(0, rightIndent, amount).toString();
    }

    public static String getMiddleIndented(String name, int indent) {
        double length = name.length();
        int leftIndent = (int) Math.floor((indent - length) / 2);
        int rightIndent = (int) Math.ceil((indent - length) / 2);
        if (leftIndent < 0) {
            leftIndent = 0;
        }
        if (rightIndent < 0) {
            rightIndent = 0;
        }
        return Ui.getIndent(leftIndent, rightIndent, name).toString();
    }

    public static String getLeftIndented(String amount, int indent) {
        double length = amount.length();
        int leftIndent = (int) (indent - length);
        if (leftIndent < 0) {
            leftIndent = 0;
        }
        return getIndent(leftIndent, 0, amount).toString();
    }

    public void printEntryAdded(Entry entry) {
        System.out.println("I've added: " + entry);
    }

    public void printInvalidCommand(String message) {
        System.out.println(message);
    }

    public void printEntryDeleted(Entry entry) {
        System.out.println("I have deleted: " + entry);
    }

    //@@author irvinseet
    public void printSetBudget(ExpenseCategory category, double amount) {
        System.out.printf("Budget for %s set to $%.2f\n", category, amount);
    }

    //@@author yanjia1777
    public void printBudgetBreakdown(ArrayList<Budget> budgetList, ArrayList<Entry> entries,
            ArrayList<Entry> recurringEntries) {
        int maxSpendingLength = MIN_SPENDING_INDENTATION;
        int maxLimitLength = MIN_LIMIT_INDENTATION;
        int maxCatLength = MIN_CAT_INDENTATION;
        for (Budget budget : budgetList) {
            if (String.format("$%,.2f",
                    budget.getMonthlySpending(entries, recurringEntries)).length() > maxSpendingLength) {
                maxSpendingLength = String.format("$%,.2f",
                        budget.getMonthlySpending(entries, recurringEntries)).length();
            }
            if (String.format("$%,.2f", budget.getLimit()).length() > maxLimitLength) {
                maxLimitLength = String.format("$%,.2f", budget.getLimit()).length();
            }
            if (budget.getCategory().name().length() > maxCatLength) {
                maxCatLength = budget.getCategory().name().length();
            }
        }
        System.out.println("Here is the budget for " + LocalDate.now().getMonth() + " " + LocalDate.now().getYear());
        System.out.println(getMiddleIndented("Category", maxCatLength) + " | "
                + getLeftIndented("Amount", maxSpendingLength) + " | "
                + getRightIndented("Budget", maxLimitLength) + " | Percentage");
        for (Budget budget : budgetList) {
            printBudgetIndividualEntry(budget, entries, recurringEntries, maxCatLength, maxSpendingLength,
                    maxLimitLength);
        }

    }

    public void printBudgetIndividualEntry(Budget budget, ArrayList<Entry> entries,
            ArrayList<Entry> recurringEntries, int maxCatLength, int maxSpendingLength,
            int maxLimitLength) {
        String category = getMiddleIndented(budget.getCategory().name(), maxCatLength);
        String spending = getLeftIndented(String.format("$%,.2f",
                budget.getMonthlySpending(entries, recurringEntries)),
                maxSpendingLength);
        String limit = budget.getLimit() == 0 ? getRightIndented("Not set", maxLimitLength) :
                getRightIndented(String.format("$%,.2f", budget.getLimit()), maxLimitLength);
        String percentage = "";
        if (budget.getLimit() != 0 && budget.getMonthlySpending(entries, recurringEntries) != 0) {
            percentage = String.format("%,.2f",
                    budget.getMonthlySpending(entries, recurringEntries) / budget.getLimit() * 100) + "%";
        }
        System.out.println(category + " | " + spending + " / " + limit + " | " + percentage);
    }

    //@@author irvinseet
    public void printBudgetWarningMessage(ExpenseCategory category, double spending, double limit) {
        System.out.printf("Slow down, you've set aside $%.2f for %s, "
                + "but you already spent $%.2f.\n", limit, category, spending);
    }

    //@@author irvinseet
    public void printUnsafeCharacters() {
        System.out.println("Please do not use special characters. "
                + "Only '.', '/', '-' are allowed. '/' is strictly use for tags.");
    }
}



