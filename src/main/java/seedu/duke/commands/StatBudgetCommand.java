package seedu.duke.commands;

public class StatBudgetCommand extends StatCommand {
    private int month;

    public StatBudgetCommand(int month) {
        this.month = month;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        System.out.println(this.month);
    }
}
