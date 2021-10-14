package seedu.duke.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private static final String DIVIDER = "========================================================";

    public void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("1. add e/EXPENDITURE_NAME a/COST [d/DATE_OF_EXPENDITURE]");
        System.out.println(DIVIDER);
        System.out.println("2. add l/DEBTOR_NAME a/AMOUNT [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
        System.out.println("3. add b/ a/AMOUNT m/MONTH [y/YEAR]");
        System.out.println(DIVIDER);
        System.out.println("4. add b/ a/AMOUNT m/MONTH [y/YEAR]");
        System.out.println(DIVIDER);
        System.out.println("5. edit m/MONTH b/AMOUNT");
        System.out.println(DIVIDER);
        System.out.println("6. edit m/MONTH i/INDEX [e/NEW_EXPENDITURE_NAME] [a/NEW_AMOUNT] [d/NEW_DATE]");
        System.out.println(DIVIDER);
        System.out.println("7. edit m/MONTH i/INDEX [l/DEBTOR_NAME] [a/AMOUNT] [d/DATE_OF_LOAN]");
        System.out.println(DIVIDER);
        System.out.println("8. find [m/MONTH] b/DESCRIPTION");
        System.out.println(DIVIDER);
        System.out.println("9. list m/all");
        System.out.println(DIVIDER);
        System.out.println("10. list m/MONTH");
        System.out.println(DIVIDER);
        System.out.println("11. delete m/MONTH");
        System.out.println(DIVIDER);
        System.out.println("12. delete m/MONTH i/INDEX");
        System.out.println(DIVIDER);
        System.out.println("13. delete m/MONTH i/INDEX_FROM-INDEX_TO");
        System.out.println(DIVIDER);
        System.out.println("14. help");
        System.out.println(DIVIDER);
        System.out.println("15. bye");
        System.out.println(DIVIDER);
    }

    public void execute(boolean isLoadingStorage) {
        printHelp();
    }
}
