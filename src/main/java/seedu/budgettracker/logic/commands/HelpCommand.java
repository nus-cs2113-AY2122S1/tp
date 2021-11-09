package seedu.budgettracker.logic.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String LS = System.lineSeparator();
    public static final int ADD_COMMAND_HELP_INDEX = 1;
    public static final int EDIT_COMMAND_HELP_INDEX = 2;
    public static final int FIND_COMMAND_HELP_INDEX = 3;
    public static final int LIST_COMMAND_HELP_INDEX = 4;
    public static final int DELETE_COMMAND_HELP_INDEX = 5;
    public static final int STAT_COMMAND_HELP_INDEX = 6;
    public static final int HELP_COMMAND_HELP_INDEX = 7;
    public static final int EXIT_COMMAND_HELP_INDEX = 8;
    private static final String DIVIDER = "========================================================";

    //@@author YEOWEIHNGWHYELAB
    public void printHelp() {
        printAddCommandHelp();
        printEditCommandHelp();
        printFindCommandHelp();
        printListCommandHelp();
        printDeleteCommandHelp();
        printStatCommandHelp();
        printHelpCommandHelp();
        printExitCommandHelp();
    }
    //@@

    private void printAddCommandHelp() {
        System.out.println(ADD_COMMAND_HELP_INDEX + ". " + AddCommand.COMMAND_WORD);
        System.out.println(AddExpenditureCommand.MESSAGE_USAGE);
        System.out.println(AddBudgetCommand.MESSAGE_USAGE);
        System.out.println(AddLoanCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
    }

    private void printEditCommandHelp() {
        System.out.println(EDIT_COMMAND_HELP_INDEX + ". " + EditCommand.COMMAND_WORD);
        System.out.println(EditBudgetCommand.MESSAGE_USAGE);
        System.out.println(EditExpenditureCommand.MESSAGE_USAGE);
        System.out.println(EditLoanCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
    }

    //@@author YEOWEIHNGWHYELAB
    private void printFindCommandHelp() {
        System.out.println(FIND_COMMAND_HELP_INDEX + "." + FindCommand.COMMAND_WORD);
        System.out.println("Finds all expenditure and loan records with the specified keyword");
        System.out.println("Parameters: [KEYWORD]");
        System.out.println(DIVIDER);
    }
    //@@

    private void printListCommandHelp() {
        System.out.println(LIST_COMMAND_HELP_INDEX + ". list");
        System.out.println("Lists all records" + LS
                + "Parameters:  m/all [c/CATEGORY]\n");
        System.out.println("Lists records in a specified month"
                + "list m/MONTH [c/CATEGORY]");
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    private void printDeleteCommandHelp() {
        System.out.println(DELETE_COMMAND_HELP_INDEX + ". " + DeleteCommand.COMMAND_WORD);
        System.out.println(DeleteBudgetCommand.MESSAGE_USAGE);
        System.out.println(DeleteAllExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteSingleExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteMultipleExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteAllLoanCommand.MESSAGE_USAGE);
        System.out.println(DeleteSingleLoanCommand.MESSAGE_USAGE);
        System.out.println(DeleteMultipleLoanCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
    }
    //@@author

    private void printStatCommandHelp() {
        System.out.println(STAT_COMMAND_HELP_INDEX + ". " + StatCommand.COMMAND_WORD);
        System.out.println(StatCategoryCommand.MESSAGE_USAGE + LS);
        System.out.println(StatYearCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
    }

    private void printHelpCommandHelp() {
        System.out.println(HELP_COMMAND_HELP_INDEX + "." + HelpCommand.COMMAND_WORD);
        System.out.println("Get all commands' information." + LS
                + "Parameters: help");
        System.out.println(DIVIDER);
    }

    private void printExitCommandHelp() {
        System.out.println(EXIT_COMMAND_HELP_INDEX + "." + ExitCommand.COMMAND_WORD);
        System.out.println("Exits the app." + LS
                + "Parameters: bye");
        System.out.println(DIVIDER);
    }

    public void execute() {
        printHelp();
    }
}
