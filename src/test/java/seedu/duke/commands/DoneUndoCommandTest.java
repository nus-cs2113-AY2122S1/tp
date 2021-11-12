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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.memberRoster;

public class DoneUndoCommandTest {

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
    void doneCommand_twoEventTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command = Parser.parseCommand("done -e 1 2");
        System.out.print(command.execute().feedbackToUser);
        String expectedOutput = "Nice! I have marked these items as done: \n"
                + "[E][X] Funfair (at: 20 Feb 2022 - 20:30)\n"
                + "--------LIST UPDATED-----------\n"
                + "These items are already done: \n"
                + "[E][X] Peppa Pig's Concert (at: 19 Feb 2022 - 20:00)\n"
                + "There's no need for me to re-mark them. ";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void doneCommand_twoTaskTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Parser.updateIndexOfLastSelectedEvent(0);
        Command command = Parser.parseCommand("done -t 1 2");
        System.out.print(command.execute().feedbackToUser);
        String expectedOutput = "Nice! I have marked these items as done: \n"
                + "[T][X] Collect Tickets (by: 19 Feb 2022 - 19:30)\n"
                + "--------LIST UPDATED-----------\n"
                + "These items are already done: \n"
                + "[T][X] Buy Boost (by: 18 Feb 2022 - 19:30)\n"
                + "There's no need for me to re-mark them. ";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void undoCommand_twoEventTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command = Parser.parseCommand("undo -e 1 2");
        System.out.print(command.execute().feedbackToUser);
        String expectedOutput = "Okay, I have unmarked these items: \n"
                + "[E][ ] Peppa Pig's Concert (at: 19 Feb 2022 - 20:00)\n"
                + "--------LIST UPDATED-----------\n"
                + "These items are not done yet: \n"
                + "[E][ ] Funfair (at: 20 Feb 2022 - 20:30)\n"
                + "They can't be unmarked. ";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void undoCommand_twoTaskTest() throws DukeException, NoCommandAttributesException, InvalidItemTypeException {
        Parser.updateIndexOfLastSelectedEvent(0);
        Command command = Parser.parseCommand("undo -t 1 2");
        System.out.print(command.execute().feedbackToUser);
        String expectedOutput = "Okay, I have unmarked these items: \n"
                + "[T][ ] Buy Boost (by: 18 Feb 2022 - 19:30)\n"
                + "--------LIST UPDATED-----------\n"
                + "These items are not done yet: \n"
                + "[T][ ] Collect Tickets (by: 19 Feb 2022 - 19:30)\n"
                + "They can't be unmarked. ";
        assertEquals(expectedOutput, outContent.toString());
    }

    void setUp() throws DukeException {
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);
        event1.markAsDone();
        eventCatalog.add(event1);
        LocalDateTime taskDeadline1 = Parser.convertDateTime("18-02-2022 1930");
        Task task1 = new Task("Buy Boost", "Need the boost for the concert", taskDeadline1);
        task1.markAsDone();
        LocalDateTime taskDeadline2 = Parser.convertDateTime("19-02-2022 1930");
        Task task2 = new Task("Collect Tickets", "Collection point: Scape", taskDeadline2);
        LocalDateTime event2DateTime = Parser.convertDateTime("20-02-2022 2030");
        Event event2 = new Event("Funfair",
                "For charity", event2DateTime,
                "Parade square", 2000.10);
        eventCatalog.add(event2);
        eventCatalog.get(0).addToTaskList(task1);
        eventCatalog.get(0).addToTaskList(task2);
    }

}
