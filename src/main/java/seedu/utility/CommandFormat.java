package seedu.utility;

import java.util.Arrays;
import java.util.List;

public class CommandFormat {
    public static final String HELP_FORMAT = "List Out All Commands: help";
    public static final String ADD_EXPENSE_FORMAT = "Adding Expense: add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY";
    public static final String DEL_EXPENSE_FORMAT = "Deleting Expense: del_ex i/INDEX";
    public static final String LIST_EXPENSE_FORMAT = "Listing Expense: list_ex";
    public static final String TOTAL_EXPENSE_FORMAT = "Show Total Expense: total_ex";
    public static final String ADD_INCOME_FORMAT = "Adding Income: add_in d/DESCRIPTION a/AMOUNT c/CATEGORY";
    public static final String DEL_INCOME_FORMAT = "Deleting Income: del_in i/INDEX";
    public static final String LIST_INCOME_FORMAT = "Listing Income: list_in";
    public static final String TOTAL_INCOME_FORMAT = "Show Total Income: total_in";
    public static final String EXPENSE_BETWEEN_FORMAT = "Show Total Expense between 2 dates"
            + ": btw_ex s/START_DATE e/END_DATE";
    public static final String INCOME_BETWEEN_FORMAT = "Show Total Income between 2 dates"
            + ": btw_in s/START_DATE e/END_DATE";
    public static final String END_FORMAT = "To Terminate The Program: end";
    public static final String FIND_FORMAT = "To Find Using Date: find YYYY-MM-DD\n"
            + "To Find Based On Keyword: find KEYWORD";
    public static final String BALANCE_FORMAT = "To Display Total Balance: balance";

    public static final List<String> commands = Arrays.asList(HELP_FORMAT, ADD_EXPENSE_FORMAT, DEL_EXPENSE_FORMAT,
            LIST_EXPENSE_FORMAT, TOTAL_EXPENSE_FORMAT, EXPENSE_BETWEEN_FORMAT, ADD_INCOME_FORMAT, DEL_INCOME_FORMAT,
            LIST_INCOME_FORMAT, TOTAL_INCOME_FORMAT, FIND_FORMAT, BALANCE_FORMAT, INCOME_BETWEEN_FORMAT, END_FORMAT);

}
