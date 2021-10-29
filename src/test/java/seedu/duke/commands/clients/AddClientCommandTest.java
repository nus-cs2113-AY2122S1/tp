package seedu.duke.commands.clients;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddClientCommandTest {

    public static final String TEST_CLIENT_ID = "c001";
    public static final String TEST_CLIENT_NAME = "Bo Tuan";
    public static final String TEST_CLIENT_CONTACT = "91234567";
    public static final String TEST_CLIENT_EMAIL = "bobotea@gmail.com";

    ClientList testClientList = new ClientList();
    FlightList dummyFlightList = new FlightList();
    TourList dummyTourList = new TourList();
    ClientPackageList dummyPackageList = new ClientPackageList();
    Ui testUi = new Ui();

    @Test
    void addClientCommand_validData_correctlyConstructed() {
        Client testClient =
                new Client(new String[]{TEST_CLIENT_ID,
                        TEST_CLIENT_NAME,
                        TEST_CLIENT_CONTACT,
                        TEST_CLIENT_EMAIL});
        AddClientCommand addClient = new AddClientCommand(testClient);

        Client retrieveClient = addClient.getClient();
        assertEquals(TEST_CLIENT_ID, retrieveClient.getId());
        assertEquals(TEST_CLIENT_NAME, retrieveClient.getName());
        assertEquals(TEST_CLIENT_CONTACT, retrieveClient.getContactNum());
        assertEquals(TEST_CLIENT_EMAIL, retrieveClient.getEmail());
    }

    @Test
    void addClientCommand_emptyClientList_populatedClientList() throws TourPlannerException {
        Client testClient =
                new Client(new String[]{TEST_CLIENT_ID,
                        TEST_CLIENT_NAME,
                        TEST_CLIENT_CONTACT,
                        TEST_CLIENT_EMAIL});
        AddClientCommand addClient = new AddClientCommand(testClient);
        addClient.setData(testClientList, dummyFlightList, dummyTourList, dummyPackageList, testUi);
        addClient.execute();

        Client retrieveClient = testClientList.getClientById(TEST_CLIENT_ID);
        assertEquals(TEST_CLIENT_ID, retrieveClient.getId());
        assertEquals(TEST_CLIENT_NAME, retrieveClient.getName());
        assertEquals(TEST_CLIENT_CONTACT, retrieveClient.getContactNum());
        assertEquals(TEST_CLIENT_EMAIL, retrieveClient.getEmail());
    }
}
