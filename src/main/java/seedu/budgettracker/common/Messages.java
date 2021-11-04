package seedu.budgettracker.common;

public class Messages {

    public static final String MESSAGE_INVALID_EXPENDITURE_AMOUNT = "Amount entered must be greater than 0.";
    public static final String MESSAGE_INVALID_BUDGET_AMOUNT = "Amount entered must be greater than or equals to 0.";
    public static final String MESSAGE_INVALID_DATE = "Date entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_YEAR = "The date entered belongs to a year outside of your"
            + "current storage file. \n"
            + "Please use the \"year\" command to switch to the correct storage file before trying again.";
    public static final String MESSAGE_INVALID_INDEX_OF_EXPENDITURE = "Index of expenditure entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_MONTH = "Month entered should be in the range of 1 to 12";
    public static final String MESSAGE_INVALID_MONTH_OF_BUDGET = "Month of budget entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_ADD_COMMAND = "Parameters of add command entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_LIST_COMMAND = "Parameters of list command entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_DELETE_COMMAND = "Parameters of delete command entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_EDIT_COMMAND = "Command must be in the form of edit m/ OR edit e/";
    public static final String MESSAGE_INVALID_STAT_COMMAND = "Sorry, stat command must be "
            + "stat -b OR stat -e OR stat -l";
    public static final String MESSAGE_INVALID_COMMAND = "Sorry, I did not understand your command.";
    public static final String MESSAGE_INVALID_CATEGORY = "Category entered is not recognized.";
    public static final String MESSAGE_EXIT = "Bye, see you again soon!";
    public static final String MESSAGE_FILE_NOT_EXIST = "The file does not exist!";
    public static final String MESSAGE_INVALID_INPUT = "Error! Your inputs are missing or incorrect!";
    public static final String MESSAGE_INVALID_IO = "IOException: ";
    public static final String MESSAGE_WARNING_FILE_DIRECTORY = "Ensure "
            + "that you have files " + "in correct directory and named correctly!";
    public static final String MESSAGE_WARNING_INCORRECT_YEAR_FORMAT = "The year entered is not of YYYY format!";

}
