package seedu.duke.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static seedu.duke.Duke.eventCatalog;

public class ListCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        setUp();
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
        eventCatalog.clear();
    }

    @Test
    public void listCommand_overallScheduleTest() throws DukeException {
        Command command1 = Parser.parseCommand("list");
        command1.execute();
        String expectedOutput = "OVERALL SCHEDULE"
                + System.lineSeparator()
                + "======================="
                + System.lineSeparator()
                + "1. [E][X] Peppa Pig's Concert (at: 19 Feb 2022 - 20:00)"
                + System.lineSeparator()
                + "2. [E][ ] Funfair (at: 20 Feb 2022 - 20:30)"
                + System.lineSeparator()
                + System.lineSeparator()
                + "FURTHER COMMANDS"
                + System.lineSeparator()
                + "-----------------------"
                + System.lineSeparator()
                + "To list Task: list [Event Index] -t"
                + System.lineSeparator()
                + "To list Members of a Task: list [Event Index] t/[Task Index]"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void listCommand_taskListTest() throws DukeException {
        Command command1 = Parser.parseCommand("list 1 -t");
        command1.execute();
        String expectedOutput = "Event: Peppa Pig's Concert"
                + System.lineSeparator()
                + "======================="
                + System.lineSeparator()
                + "1. [T][ ] Collect Tickets (by: 18 Feb 2022 - 19:30)"
                + System.lineSeparator()
                + "2. [T][ ] Buy Boost (by: 19 Feb 2022 - 19:30)"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void listCommand_memberListTest() throws DukeException {
        Command command1 = Parser.parseCommand("list 1 t/1");
        command1.execute();
        String expectedOutput = "Event: Peppa Pig's Concert"
                + System.lineSeparator()
                + "Task: Collect Tickets"
                + System.lineSeparator()
                + "======================="
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    void setUp() {
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
