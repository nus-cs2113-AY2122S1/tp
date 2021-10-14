package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearCommandTest {

    @Test
    void clearCommand_populatedClientList_correctlyCleared() throws TourPlannerException {
        ClientList testClientList = new ClientList();
        Ui testUi = new Ui();
        Client testClient1 = new Client(new String[]{"botuan", "1234", "SQ123", "Intercontinental", "AUS_A"});
        Client testClient2 = new Client(new String[]{"amy", "6234", "CQ456 ", "Holiday Inn", "KOR_K"});
        Command addClient1 = new AddCommand(testClient1);
        Command addClient2 = new AddCommand(testClient2);
        addClient1.execute(testClientList, testUi);
        addClient2.execute(testClientList, testUi);

        Command clear = new ClearCommand();
        clear.execute(testClientList, testUi);
        assertEquals(testClientList.getClientCount(), 0);
    }
}