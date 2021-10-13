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
}
