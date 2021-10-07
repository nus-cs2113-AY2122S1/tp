package seedu.duke.commands;

public class DeleteExpenditureCommand extends Command {

    public static final String COMMAND_WORD = "DeleteExpenditure";
    public final int index;

    public DeleteExpenditureCommand(String indexString) {
        this.index = Integer.parseInt(indexString) - 1;
    }

    @Override
    public void execute() {
    }
}
