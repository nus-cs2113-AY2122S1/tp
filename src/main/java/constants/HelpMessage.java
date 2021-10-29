package constants;

public final class HelpMessage {

    public static final String helpMsg = "Expenses Functionality:\n"
            + "expense add [NAME] -v [VALUE] -c [CATEGORY]\t Add a new expense\n"
            + "expense list [-c]\t List all expenses from a category\n"
            + "expense delete -n [NAME] -i [ID]\t Delete an existing expense\n"
            + "expense update [NAME] -v [NEW_VALUE] -c [NEW_CATEGORY]\t Update the value & category of an expense\n"
            + "\nIncome Functionality:\n"
            + "income add [NAME] -v [VALUE]\t Add a new income\n"
            + "income list\t List all incomes\n"
            + "income delete -n [NAME] -i [ID]\t Delete an existing income\n"
            + "income update [NAME] -v [VALUE]\t Update the value of an income\n"
            + "\nBudget Functionality:\n"
            + "budget add -v [VALUE]\t Add a new budget\n"
            + "budget list\t List the budget set for current month\n"
            + "budget delete\t Delete the budget for current month\n"
            + "\nOthers:\n"
            + "help\t Displays this help message\n"
            + "exit\t Exit the program";

    private HelpMessage() {
    }
}
