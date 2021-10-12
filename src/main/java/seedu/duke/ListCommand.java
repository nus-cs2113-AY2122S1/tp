package seedu.duke;

public class ListCommand extends Command {
    public void execute(ClientList clientList, Ui ui) {
        int count = clientList.getClientCount();
        if (count == 0) {
            System.out.println("I'm sorry, There seems to be no clients.");
            return;
        }
        System.out.println("Here is a list of all your clients:");
        for (int i = 1; i <= count; i++) {
            Client currClient = clientList.getClient(i - 1);
            System.out.println(i + ". " + currClient);
        }
    }

    public boolean isExit() {
        return false;
    }
}


