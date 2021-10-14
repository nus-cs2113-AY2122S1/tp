package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    @Test
    void addCommand_validData_correctlyConstructed() {
        Client testClient = new Client(new String[]{"botuan", "1234", "SQ123", "Intercontinental", "AUS_A"});
        Command addClient = new AddCommand(testClient);

        Client retrieveClient = ((AddCommand) addClient).getClient();
        assertEquals("botuan", retrieveClient.getName());
        assertEquals("1234", retrieveClient.getContactNum());
        assertEquals("SQ123", retrieveClient.getFlight());
        assertEquals("Intercontinental", retrieveClient.getAccomms());
        assertEquals("AUS_A", retrieveClient.getTour());
    }

    @Test
    void addCommand_emptyClientList_populatedClientList() {
        ClientList testClientList = new ClientList();
        Ui testUi = new Ui();
        Client testClient = new Client(new String[]{"botuan", "1234", "SQ123", "Intercontinental", "AUS_A"});
        Command addClient = new AddCommand(testClient);
        addClient.execute(testClientList, testUi);

        Client retrieveClient = testClientList.getClient(0);
        assertEquals("botuan", retrieveClient.getName());
        assertEquals("1234", retrieveClient.getContactNum());
        assertEquals("SQ123", retrieveClient.getFlight());
        assertEquals("Intercontinental", retrieveClient.getAccomms());
        assertEquals("AUS_A", retrieveClient.getTour());
    }
}
