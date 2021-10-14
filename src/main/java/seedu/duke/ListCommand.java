package seedu.duke;

/**
 * List all clients and their information in a viewable indexed manner.
 */
public class ListCommand extends Command {

    /**
     * Executes listing of all clients in the existing client list.
     * If the list is empty, breaks out from execute early with an error message.
     *
     * @param clientList existing list of clients
     * @param ui         user interface of TourPlanner
     */
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
}


