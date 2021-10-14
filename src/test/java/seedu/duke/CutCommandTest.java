package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit testing for CutCommand.
 */
class CutCommandTest {

    ClientList clientList = new ClientList();
    Ui ui = new Ui();

    Client testClient = new Client(new String[]{"botuan", "1234", "hgus", "hotel-one", "aus-a"});


    /**
     * Asserts that if cut index is valid, a client in the list would be deleted.
     */
    @Test
    void cutCommand_validData_correctlyDeleted() {
        Command add = new AddCommand(testClient);
        add.execute(clientList, ui);
        Command cut = new CutCommand(0);
        cut.execute(clientList, ui);

        int length = clientList.getClientCount();
        assertEquals(0, length);
    }

    /**
     * Asserts that if cut index is invalid, a client in the list would not be deleted.
     */
    @Test
    void cutCommand_invalidData_correctlyDeleted() {
        Command add = new AddCommand(testClient);
        add.execute(clientList, ui);
        Command cut = new CutCommand(-1);
        cut.execute(clientList, ui);

        int length = clientList.getClientCount();
        assertEquals(1, length);
    }
}
