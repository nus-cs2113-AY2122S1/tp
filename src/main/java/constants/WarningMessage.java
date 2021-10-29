package constants;

public final class WarningMessage {
    private WarningMessage() {
    }

    public static final String expenseNearingBudgetWarning = "Your latest expense is almost exceeding the budget "
            + "you have set.\n Spend carefully or you will soon burst your budget!";

    public static final String budgetNowZeroWarning = "Your latest expense means your budget is now zero! "
            + "\nDon't spend anymore unless you absolutely have to.";

    public static final String expenseExceedBudgetWarning = "Your latest expense has made you burst your budget! "
            + "\nPlease spend more consciously now.";
}
