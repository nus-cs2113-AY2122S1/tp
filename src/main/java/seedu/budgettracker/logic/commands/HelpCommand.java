package seedu.budgettracker.logic.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String LS = System.lineSeparator();
    private static final String DIVIDER = "========================================================";

    public void printHelp() {
        System.out.println("1. " + AddCommand.COMMAND_WORD);
        System.out.println(AddExpenditureCommand.MESSAGE_USAGE);
        System.out.println(AddBudgetCommand.MESSAGE_USAGE);
        System.out.println("Adds a loan record.");
        System.out.println("Parameters: l/DEBTOR_NAME a/AMOUNT [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
        System.out.println("2. " + EditCommand.COMMAND_WORD);
        System.out.println(EditExpenditureCommand.MESSAGE_USAGE);
        System.out.println("3. edit m/MONTH i/INDEX [e/NEW_EXPENDITURE_NAME] [a/NEW_AMOUNT] [d/NEW_DATE]");
        System.out.println(DIVIDER);
        System.out.println("4. edit m/MONTH i/INDEX [l/DEBTOR_NAME] [a/AMOUNT] [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
        System.out.println("5. find [m/MONTH] b/DESCRIPTION");
        System.out.println(DIVIDER);
        System.out.println("6. " + ListRecordsCommand.COMMAND_WORD);
        System.out.println(ListRecordsCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
        System.out.println("7. " + DeleteCommand.COMMAND_WORD);
        System.out.println(DeleteBudgetCommand.MESSAGE_USAGE);
        System.out.println(DeleteAllExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteSingleExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteMultipleExpenditureCommand.MESSAGE_USAGE);
        System.out.println(DeleteAllLoanCommand.MESSAGE_USAGE);
        System.out.println(DeleteSingleLoanCommand.MESSAGE_USAGE);
        System.out.println(DeleteMultipleLoanCommand.MESSAGE_USAGE);
        System.out.println(DIVIDER);
        System.out.println("8. help");
        System.out.println("Get all commands' information.");
        System.out.println("Parameters: help");
        System.out.println(DIVIDER);
        System.out.println("9. bye");
        System.out.println("Exit the app.");
        System.out.println("Parameters: bye");
        System.out.println(DIVIDER);
    }

    public void execute() {
        printHelp();
    }
}
