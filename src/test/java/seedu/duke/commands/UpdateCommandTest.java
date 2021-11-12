package seedu.duke.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;

public class UpdateCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static ByteArrayInputStream in;

    public void setInput(String input) {
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @BeforeEach
    public void setUpStream() throws DukeException {
        System.setOut(new PrintStream(outContent));
        setUp();
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
        eventCatalog.clear();
    }

    @Test
    public void updateCommand_updateNameOfEvent_success() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        final InputStream sysInBackup = System.in;
        setInput("n/Charlie Puth Concert");

        Command command1 = Parser.parseCommand("update 2");
        command1.execute();
        assertEquals("Charlie Puth Concert", eventCatalog.get(1).getTitle());
        System.setIn(sysInBackup);
    }

    @Test
    public void updateCommand_updateDateOfEvent_success() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        final InputStream sysInBackup = System.in;
        setInput("d/31-12-2022 1800");

        Command command1 = Parser.parseCommand("update 2");
        command1.execute();
        assertEquals("31 Dec 2022 - 18:00", eventCatalog.get(1).getStringDateTime());
        System.setIn(sysInBackup);
    }

    @Test
    public void updateCommand_updateVenueOfEvent_success() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        final InputStream sysInBackup = System.in;
        setInput("v/mfr");

        Command command1 = Parser.parseCommand("update 2");
        command1.execute();
        assertEquals("mfr", eventCatalog.get(1).getVenue());
        System.setIn(sysInBackup);
    }

    @Test
    public void updateCommand_updateDescriptionOfEvent_success() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        final InputStream sysInBackup = System.in;
        setInput("p/Cool coool cool, awesome, top 16 fruit tree");

        Command command1 = Parser.parseCommand("update 2");
        command1.execute();
        assertEquals("Cool coool cool, awesome, top 16 fruit tree", eventCatalog.get(1).getDescription());
        System.setIn(sysInBackup);
    }

    @Test
    public void updateCommand_updateBudgetOfEvent_success() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        final InputStream sysInBackup = System.in;
        setInput("b/10000.53");

        Command command1 = Parser.parseCommand("update 2");
        command1.execute();
        String actualBudget = Double.toString(eventCatalog.get(1).getBudget());

        assertEquals("10000.53", actualBudget);
        System.setIn(sysInBackup);
    }

    void setUp() throws DukeException {
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);
        event1.markAsDone();
        eventCatalog.add(event1);
        LocalDateTime taskDeadline1 = Parser.convertDateTime("19-02-2022 1930");
        Task task1 = new Task("Buy Boost", "Need the boost for the concert", taskDeadline1);
        LocalDateTime taskDeadline2 = Parser.convertDateTime("18-02-2022 1930");
        Task task2 = new Task("Collect Tickets", "Collection point: Scape", taskDeadline2);
        LocalDateTime event2DateTime = Parser.convertDateTime("20-02-2022 2030");
        Event event2 = new Event("Funfair",
                "For charity", event2DateTime,
                "Parade square", 2000.10);
        eventCatalog.add(event2);
        eventCatalog.get(0).addToTaskList(task1);
        eventCatalog.get(0).addToTaskList(task2);
        eventCatalog.sortCatalog();
    }
}
