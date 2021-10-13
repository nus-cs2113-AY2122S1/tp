package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CutCommandTest {

    ClientList clientList = new ClientList();
    Ui ui = new Ui();

    Client testClient = new Client(new String[]{"botuan", "1234", "hgus", "hotel-one", "aus-a"});


    @Test
    void cutCommand_validData_correctlyDeleted() {
        Command add = new AddCommand(testClient);
        add.execute(clientList, ui);
        Command cut = new CutCommand(0);
        cut.execute(clientList, ui);

        int length = clientList.getClientCount();
        assertEquals(0, length);
    }

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
