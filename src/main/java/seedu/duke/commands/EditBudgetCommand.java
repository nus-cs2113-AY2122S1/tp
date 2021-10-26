package seedu.duke.commands;

public class EditBudgetCommand extends EditCommand {
    public int month;
    public double amount;

    public EditBudgetCommand(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public void execute(boolean isLoadingStorage) {
        recordList.editBudget(this.month, this.amount);
    }
}
