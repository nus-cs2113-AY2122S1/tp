package seedu.duke.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.memberRoster;

public class ListCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() throws DukeException {
        System.setOut(new PrintStream(outContent));
        setUp();
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
        eventCatalog.clear();
        memberRoster.clear();
    }

    @Test
    public void listCommand_overallScheduleTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command1 = Parser.parseCommand("list -e");
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
                + "list -e: to see overall events"
                + System.lineSeparator()
                + "list -t [EVENT_NUM]: to see tasks in an Event"
                + System.lineSeparator()
                + "list -m e/[Event Index] t/[Task Index] : to see members in a Task"
                + System.lineSeparator()
                + "list -m: to see overall member roster"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void listCommand_taskListTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command1 = Parser.parseCommand("list -t 1");
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
    public void listCommand_memberListTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command1 = Parser.parseCommand("list -m e/1 t/1");
        command1.execute();
        String expectedOutput = "Event: Peppa Pig's Concert"
                + System.lineSeparator()
                + "Task: Collect Tickets"
                + System.lineSeparator()
                + "======================="
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void listCommand_memberRosterListTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command1 = Parser.parseCommand("list -m");
        command1.execute();
        String expectedOutput = "List of members in MemberRoster"
                + System.lineSeparator()
                + "1. BOB"
                + System.lineSeparator()
                + "2. Ant Verma"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
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
        Member bob = new Member("BOB");
        Member ant = new Member("Ant Verma");
        memberRoster.add(bob);
        memberRoster.add(ant);
        eventCatalog.sortCatalog();
    }
}
