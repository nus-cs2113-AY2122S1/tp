package seedu.budgettracker.common;

public class Messages {

    public static final String MESSAGE_INVALID_AMOUNT = "Amount entered must be greater than 0.";
    public static final String MESSAGE_AMOUNT_EXCEEDED = "Amount more than 1 billion is currently not supported";
    public static final String MESSAGE_INVALID_DATE = "Date entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_YEAR = "The date entered belongs to a year outside of your "
            + "current storage file. \n"
            + "Please use the \"year\" command to switch to the correct storage file before trying again.";
    public static final String MESSAGE_INVALID_INDEX_OF_EXPENDITURE = "Index of expenditure entered is invalid.\n"
            + "Please enter the index within the list range and without spaces.";
    public static final String MESSAGE_INVALID_INDEX_OF_LOAN = "Index of loan entered is invalid.\n"
            + "please enter the index within the list range and without spaces.";
    public static final String MESSAGE_INVALID_MONTH = "Month entered should be in the range of 1 to 12";
    public static final String MESSAGE_INVALID_MONTH_OF_BUDGET = "Month of budget entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_ADD_COMMAND = "Parameters of add command entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_LIST_COMMAND = "Parameters of list command entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_DELETE_COMMAND = "Parameters of delete command entered is invalid\n%1$s";
    public static final String MESSAGE_INVALID_EDIT_COMMAND = "Command must be in the form of edit m/ OR edit e/";
    public static final String MESSAGE_INVALID_STAT_COMMAND = "Sorry, stat command must be "
            + "stat -b OR stat -e OR stat -l";
    public static final String MESSAGE_INVALID_COMMAND = "Sorry, I did not understand your command.";
    public static final String MESSAGE_EXIT = "Bye, see you again soon!";
    public static final String MESSAGE_FILE_NOT_EXIST = "The file does not exist!";
    public static final String MESSAGE_INVALID_INPUT = "Your inputs are missing or incorrect! Check help for more info";
    public static final String MESSAGE_INVALID_IO = "IOException: ";
    public static final String MESSAGE_WARNING_FILE_DIRECTORY = "Ensure "
            + "that you have files " + "in correct directory and named correctly!";
    public static final String MESSAGE_WARNING_INCORRECT_YEAR_FORMAT = "The year entered is not of YYYY format!";
    public static final String MESSAGE_AMOUNT_ZERO_OR_NEGATIVE = "Your amount has to be above $0!";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "You have too many or incorrect arguments!";
    public static final String MESSAGE_INCORRECT_OR_MISSING_INPUTS = "Your inputs are missing or incorrect!";
    public static final String MESSAGE_MISSING_TYPE_INPUTS = "You have missing or invalid type inputs."
            + "Please indicate '-e', '-b' or '-l'!\n%1$s";
    public static final String MESSAGE_INVALID_INDEX = "Please ensure your index is a valid integer!";
    public static final String MESSAGE_INVALID_INDEXES = "Please ensure your indexes are valid integers!";
    public static final String MESSAGE_INCORRECT_TYPE = "Type specified is incorrect!";
    public static final String MESSAGE_INCORRECT_CATEGORY = "Specified category name does not exist!";
    public static final String MESSAGE_INVALID_NAME = "Your name is empty!";
    public static final String MESSAGE_DATE_INVALID_FORMAT = "Your date is in the wrong format!"
            + ""
            + "Correct format: YYYY-MM-DD";
    public static final String MESSAGE_INVALID_NUMBER_AMOUNT = "Please enter a valid number into amount!";
    public static final String MESSAGE_INVALID_DESCRIPTION = "Your description is empty!";
}
