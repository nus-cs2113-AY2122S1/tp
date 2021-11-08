package constants;

public final class HelpMessage {

    public static final String helpMsg = "Expense Functionality:\n"
            + "expense add [NAME] -v [VALUE] -c [CATEGORY]\t Add a new expense\n"
            + "expense list [-c]\t List all expenses from a category\n"
            + "expense delete -n [NAME] -i [ID]\t Delete an existing expense\n"
            + "expense update [NAME] -v [VALUE] -c [CATEGORY]\t Update the value & category of an expense\n"
            + "expense warning -v [VALUE]\t Set a warning limit for expense with respect to budget\n "
            + "\nIncome Functionality:\n"
            + "income add [NAME] -v [VALUE]\t Add a new income\n"
            + "income list\t List all incomes\n"
            + "income delete -n [NAME] -i [ID]\t Delete an existing income\n"
            + "income update [NAME] -v [VALUE]\t Update the value of an income\n"
            + "\nBudget Functionality:\n"
            + "budget add -v [VALUE]\t Add a new budget\n"
            + "budget list\t List the budget set for current month\n"
            + "budget delete\t Delete the budget for current month\n"
            + "\nInvest Functionality:\n"
            + "invest add savings [NAME] -v [VALUE] \t Add savings account\n"
            + "invest add stocks [NAME] -n [NUM] -p [PRICE] \t Add a stock\n"
            + "invest delete savings -i [ID] \t Delete savings account\n"
            + "invest delete stocks -i [ID] \t Delete a stock\n"
            + "invest list savings \t List all savings accounts\n"
            + "invest list stocks \t List all stocks\n"
            + "\nOthers:\n"
            + "help\t Displays this help message\n"
            + "exit\t Exit the program";


    private HelpMessage() {
    }
}
