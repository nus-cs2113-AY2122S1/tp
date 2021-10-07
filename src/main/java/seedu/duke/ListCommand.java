package seedu.duke;

public class ListCommand extends Command {
    public void execute(ClientList clientList, Ui ui) {
        System.out.println("Here is a list of all your clients:");
        for (int i = 1; i <= clientList.getClientCount(); i++) {
            Client currClient = clientList.getClient(i - 1);
            System.out.println(i + ". " + currClient);
        }
    }

    public boolean isExit() {
        return false;
    }
}

