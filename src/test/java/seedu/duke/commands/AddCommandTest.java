package seedu.duke.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.memberRoster;

public class AddCommandTest {

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
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
        eventCatalog.clear();
        memberRoster.clear();
    }

    @Test
    void addEventCommandTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        final InputStream sysInBackup = System.in;
        setInput("Asia World Tour");

        Command command = Parser.parseCommand("add -e n/Peppa Pig's Concert d/19-02-2022 2000 "
                + "v/Indoor Stadium b/1000.9");
        command.execute();
        assertEquals("Peppa Pig's Concert", eventCatalog.get(0).getTitle());
        assertEquals("19 Feb 2022 - 20:00", eventCatalog.get(0).getStringDateTime());
        assertEquals("Indoor Stadium", eventCatalog.get(0).getVenue());
        assertEquals(1000.90, eventCatalog.get(0).getBudget());
        System.setIn(sysInBackup);
    }

    @Test
    void addMemberCommandTest() throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        Command command = Parser.parseCommand("add -m Alwin Ang, Alvin Lim");
        command.execute();
        assertEquals("ALVIN LIM", memberRoster.get(0).getName());
        assertEquals("ALWIN ANG", memberRoster.get(1).getName());
    }

}
