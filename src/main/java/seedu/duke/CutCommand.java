package seedu.duke;

public class CutCommand extends Command {
    private final int clientIndex;

    public CutCommand(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void execute(ClientList clients, Ui ui) {
        try {
            ui.showCut(clients.getClient(clientIndex));
            clients.cut(clientIndex, ui);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("INVALID: Index out of bounds");
        }
    }
}
