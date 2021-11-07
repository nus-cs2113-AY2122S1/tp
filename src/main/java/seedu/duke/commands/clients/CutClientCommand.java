package seedu.duke.commands.clients;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientPackage;

import java.util.ArrayList;

public class CutClientCommand extends Command {
    private final String clientId;
    private Client client;

    /**
     * Class constructor for CutClientCommand.
     *
     * @param clientId ID of to-be-deleted client in the client list
     */
    public CutClientCommand(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Executes deletion of the specific client and related client packages, according to the clientId.
     */
    @Override
    public void execute() {
        try {
            cutClient();
            cutClientPackage();
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("INVALID: Index out of bounds");
        } catch (TourPlannerException e2) {
            System.out.println(e2.getMessage());
        }
    }

    /**
     * Executes deletion of the specific client from the client list.
     *
     * @throws TourPlannerException if client cannot be found based on the client id
     */
    private void cutClient() throws TourPlannerException {
        this.client = clients.getClientById(clientId);
        final int newClientCount = clients.getClientCount() - 1;
        ui.showCut(client);
        ui.showLine();
        clients.cut(client);
        assert newClientCount == clients.getClientCount();
        assert newClientCount >= 0;
    }

    /**
     * Executes deletion of the client packages containing the specific client from the client package list.
     */
    private void cutClientPackage() {
        ArrayList<ClientPackage> clientPackagesWithClient = clientPackages.getClientPackageByClient(client);
        for (ClientPackage clientPackage: clientPackagesWithClient) {
            System.out.println();
            ui.showCut(clientPackage);
            clientPackages.cut(clientPackage);
        }
    }
}
