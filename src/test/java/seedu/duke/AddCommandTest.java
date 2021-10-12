package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void addCommand_validData_correctlyConstructed() {
        Client testClient = new Client(new String[]{"botuan", "1234", "hgus", "hotel-one", "aus-a"});
        Command addClient = new AddCommand(testClient);

        Client retrieveClient = ((AddCommand) addClient).getClient();
        assertEquals("botuan", retrieveClient.getName());
        assertEquals("1234", retrieveClient.getContactNum());
        assertEquals("hgus", retrieveClient.getFlight());
        assertEquals("hotel-one", retrieveClient.getAccomms());
        assertEquals("aus-a", retrieveClient.getTour());
    }
}
