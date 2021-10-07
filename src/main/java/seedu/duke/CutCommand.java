package seedu.duke;

public class CutCommand extends Command {
    private int clientIndex;

    public CutCommand(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void execute(ClientList clients, Ui ui) {
        clients.cut(clientIndex, ui);
    }
}
