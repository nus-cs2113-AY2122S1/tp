package seedu.budgettracker.logic.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String LS = System.lineSeparator();
    private static final String DIVIDER = "========================================================";

    public void printHelp() {
        printAddCommandHelp();
        printEditCommandHelp();
        System.out.println("3. find [m/MONTH] b/DESCRIPTION");
        System.out.println(DIVIDER);
        System.out.println("4. list m/all [c/CATEGORY]");
        System.out.println(DIVIDER);
        System.out.println("5. list m/MONTH [c/CATEGORY]");
        System.out.println(DIVIDER);
        printDeleteCommandHelp();
        printStatCommand();
        System.out.println("9. help");
        System.out.println("Get all commands' information.");
        System.out.println("Parameters: help");
        System.out.println(DIVIDER);
        printExitCommandHelp();
    }


    private void printAddCommandHelp() {
        System.out.println("1. " + AddCommand.COMMAND_WORD);
        System.out.println(AddExpenditureCommand.MESSAGE_USAGE);
        System.out.println(AddBudgetCommand.MESSAGE_USAGE);
        System.out.println("Adds a loan record.");
        System.out.println("Parameters: l/DEBTOR_NAME a/AMOUNT [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
    }

    private void printEditCommandHelp() {
        System.out.println("2. " + EditCommand.COMMAND_WORD);
        System.out.println(EditExpenditureCommand.MESSAGE_USAGE);
        System.out.println("Edits a budget record:");
        System.out.println("edit -b m/MONTH a/NEW_AMOUNT");
        System.out.println();
        System.out.println("Edits a loan record: ");
        System.out.println(" edit m/MONTH i/INDEX [l/DEBTOR_NAME] [a/AMOUNT] [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
    }

    //@@author edisonzhong17
    private void printDeleteCommandHelp() {
        System.out.println("6. " + DeleteCommand.COMMAND_WORD);
        System.out.println(DeleteBudgetCommand.MESSAGE_USAGE);
        System.out.println(DeleteAllExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteSingleExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteMultipleExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteAllLoanCommand.MESSAGE_USAGE);
        System.out.println(DeleteSingleLoanCommand.MESSAGE_USAGE);
        System.out.println(DeleteMultipleLoanCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
    }

    private void printStatCommand() {
        System.out.println("7. " + StatCommand.COMMAND_WORD);
    }

    private void printExitCommandHelp() {
        System.out.println("10. bye");
        System.out.println("Exit the app.");
        System.out.println("Parameters: bye");
        System.out.println(DIVIDER);
    }

    public void execute() {
        printHelp();
    }
}
