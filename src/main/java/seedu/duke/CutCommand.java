package seedu.duke;

public class CutCommand extends Command {
    private int taskNumber;

    public CutCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(ClientList clients, Ui ui) {
        clients.cut(taskNumber, ui);
    }
}
