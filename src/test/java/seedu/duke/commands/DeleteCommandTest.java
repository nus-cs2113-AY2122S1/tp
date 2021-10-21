package seedu.duke.commands;


import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.items.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;


class DeleteCommandTest {

    @Test
    void deleteOneEvent_listOfTwoEvents_oneEventRemaining() {
        setUp();
        Command command1 = Parser.parseCommand("delete -e 2");
        command1.execute();
        assertEquals(1, eventCatalog.size());

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y".getBytes());
        System.setIn(in);

        Command command2 = Parser.parseCommand("delete all");
        command2.execute();
        System.setIn(sysInBackup);
    }


    void setUp() {
        // Setting up
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);
        event1.markAsDone();
        LocalDateTime event2DateTime = Parser.convertDateTime("20-02-2022 2030");
        Event event2 = new Event("Funfair",
                "For charity", event2DateTime,
                "Parade square", 2000.10);
        eventCatalog.add(event1);
        eventCatalog.add(event2);
    }
}