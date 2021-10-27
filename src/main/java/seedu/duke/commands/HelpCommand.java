package seedu.duke.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String LS = System.lineSeparator();
    private static final String DIVIDER = "========================================================";

    public void printHelp() {
        System.out.println("1. " + AddCommand.COMMAND_WORD);
        System.out.println(AddExpenditureCommand.MESSAGE_USAGE + LS);
        System.out.println(AddBudgetCommand.MESSAGE_USAGE + LS);
        System.out.println("Adds a loan record.");
        System.out.println("Parameters: l/DEBTOR_NAME a/AMOUNT [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
        System.out.println("2. edit m/MONTH b/AMOUNT");
        System.out.println(DIVIDER);
        System.out.println("3. edit m/MONTH i/INDEX [e/NEW_EXPENDITURE_NAME] [a/NEW_AMOUNT] [d/NEW_DATE]");
        System.out.println(DIVIDER);
        System.out.println("4. edit m/MONTH i/INDEX [l/DEBTOR_NAME] [a/AMOUNT] [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
        System.out.println("5. find [m/MONTH] b/DESCRIPTION");
        System.out.println(DIVIDER);
        System.out.println("6. list m/all [c/CATEGORY]");
        System.out.println(DIVIDER);
        System.out.println("7. list m/MONTH [c/CATEGORY]");
        System.out.println(DIVIDER);
        System.out.println("8. delete m/MONTH");
        System.out.println(DIVIDER);
        System.out.println("9. delete m/MONTH i/INDEX");
        System.out.println(DIVIDER);
        System.out.println("10. delete m/MONTH i/INDEX_FROM-INDEX_TO");
        System.out.println(DIVIDER);
        System.out.println("11. help");
        System.out.println(DIVIDER);
        System.out.println("12. bye");
        System.out.println(DIVIDER);
    }

    public void execute(boolean isLoadingStorage) {
        printHelp();
    }
}
