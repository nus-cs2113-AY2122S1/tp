package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    ClientList clientList = new ClientList();
    Ui ui = new Ui();

    Client testClient = new Client(new String[]{"botuan", "1234", "hgus", "hotel-one", "aus-a"});


    @Test
    void ListCommand_validData_correctlyListed() {
        Command add = new AddCommand(testClient);
        add.execute(clientList, ui);
        Command list = new ListCommand();

        String expectedOutput = "Here is a list of all your clients:\n" +
        "1. Client's name: botuan\n" +
        "Client's contactNum: 1234\n" +
        "Client's flight number: hgus\n" +
        "Client's accomms: hotel-one\n" +
        "Client's tour: aus-a\n" +
        "\n";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(stream);
        PrintStream old = System.out;
        System.setOut(ps);
        list.execute(clientList, ui);
        System.out.flush();
        System.setOut(old);
        assertEquals(expectedOutput, stream.toString());



    }
}