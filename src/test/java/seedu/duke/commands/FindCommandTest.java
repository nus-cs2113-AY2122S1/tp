package seedu.duke.commands;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.Event;
import seedu.duke.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;

class FindCommandTest {

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
    void findResult_ThreeEventsWithTwoMatches_correctOutput() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        createEventsList();
        Command command = Parser.parseCommand("find -e Concert");
        CommandResult feedback = command.execute();
        assertTrue(feedback.feedbackToUser.contains("Temasek Hall Concert"));
        assertTrue(feedback.feedbackToUser.contains("NUS Concert"));
        assertFalse(feedback.feedbackToUser.contains("Orientation Week"));
        eventCatalog.clear();
    }

    @Test
    void findResult_noFlag_correctErrorMessage() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        createEventsList();
        Parser.parseCommand("find abc");
        String expectedOutput = "Please add -e to find event(s)!" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void findResult_noEventsFound_correctErrorMessage() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        createEventsList();
        Command command = Parser.parseCommand("find -e abc");
        CommandResult feedback = command.execute();
        String expectedOutput = "No matching events found!";
        assertEquals(expectedOutput, feedback.feedbackToUser);
    }

    private void createEventsList() throws DukeException {
        eventCatalog.clear();

        LocalDateTime event1DateTime = Parser.convertDateTime("03-12-2021 2300");
        Event event1 = new Event("Temasek Hall Concert", "Concert for Freshmen",
                event1DateTime, "Temasek Hall", 1000);

        LocalDateTime event2DateTime = Parser.convertDateTime("04-12-2021 1900");
        Event event2 = new Event("NUS Concert", "Concert for Profs",
                event2DateTime, "NUS MPSH", 2000);

        LocalDateTime event3DateTime = Parser.convertDateTime("01-01-2022 1200");
        Event event3 = new Event("Orientation Week", "Orientation for Freshmen",
                event3DateTime, "NUS", 3000);

        eventCatalog.add(event1);
        eventCatalog.add(event2);
        eventCatalog.add(event3);
    }

}