package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.clients.AddClientCommand;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.TourList;
import seedu.duke.data.FlightList;
import seedu.duke.data.ClientPackageList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddClientCommandTest {
    @Test
    void addClientCommand_validData_correctlyConstructed() {
        Client testClient = new Client(new String[]{"john", "91234567", "johndoe@gmail.com"});
        Command addClient = new AddClientCommand(testClient);

        Client retrieveClient = ((AddClientCommand) addClient).getClient();
        assertEquals("john", retrieveClient.getName());
        assertEquals("91234567", retrieveClient.getContactNum());
        assertEquals("johndoe@gmail.com", retrieveClient.getEmail());
    }

    @Test
    void addClientCommand_emptyClientList_populatedClientList() throws TourPlannerException {
        ClientList testClientList = new ClientList();
        FlightList dummyFlightList = new FlightList();
        TourList dummyTourList = new TourList();
        ClientPackageList dummyPackageList = new ClientPackageList();
        Ui testUi = new Ui();
        Client testClient = new Client(new String[]{"john", "91234567", "johndoe@gmail.com"});
        Command addClient = new AddClientCommand(testClient);
        addClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        addClient.execute();

        Client retrieveClient = testClientList.getClient(0);
        assertEquals("john", retrieveClient.getName());
        assertEquals("91234567", retrieveClient.getContactNum());
        assertEquals("johndoe@gmail.com", retrieveClient.getEmail());
    }
}