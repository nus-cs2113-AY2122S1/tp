package seedu.duke.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;

public class NextCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void nextCommandResult_nextEarliestEvent() throws DukeException {
        setUp();
        Command command1 = Parser.parseCommand("next event");
        Ui.printEvent(eventCatalog.get(0));
        String expectedOutput = "Title: Peppa Pig's Concert"
                + System.lineSeparator()
                + "Date: 19 Feb 2022 - 20:00"
                + System.lineSeparator()
                + "Description: Asia world tour"
                + System.lineSeparator()
                + "Venue: Indoor Stadium"
                + System.lineSeparator()
                + "Budget: $1000.9"
                + System.lineSeparator()
                + "Tasks: "
                + System.lineSeparator()
                + "1. [T][ ] Collect Tickets (by: 18 Feb 2022 - 19:30)"
                + System.lineSeparator()
                + "2. [T][ ] Buy Boost (by: 19 Feb 2022 - 19:30)"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        assertEquals("Hope you have prepared everything!", command1.execute().feedbackToUser);
        eventCatalog.clear();
    }

    @Test
    public void nextCommandResult_nextEarliestTask_taskExists() throws DukeException {
        setUp();
        Command command1 = Parser.parseCommand("next task 1");
        Ui.printTask(eventCatalog.get(0).getFromTaskList(0));
        String expectedOutput = "Title: Collect Tickets"
                + System.lineSeparator()
                + "Deadline: 18 Feb 2022 - 19:30"
                + System.lineSeparator()
                + "Description: Collection point: Scape"
                + System.lineSeparator()
                + "Members: "
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        assertEquals("Hope you have prepared everything!", command1.execute().feedbackToUser);
        eventCatalog.clear();
    }

    @Test
    public void nextCommandResult_nextEarliestTask_noTaskExists() throws DukeException {
        setUp();
        Command command1 = Parser.parseCommand("next task 2");
        assertEquals("This Event has no tasks!", command1.execute().feedbackToUser);
        eventCatalog.clear();
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
