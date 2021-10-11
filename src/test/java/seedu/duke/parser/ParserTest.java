package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddLessonCommand;
import seedu.duke.command.AddTaskCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.exception.DukeException;

import seedu.duke.command.Command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseAddTask() {
        String userResponse = "add task CS2113T tP -d MON -i before tutorial";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddTask_wrongFlag_exceptionThrown() {
        String userResponse = "add task CS2113T tP -i MON -d before tutorial";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void parseAddTask_invalidDayOfTheWeek_exceptionThrown() {
        String userResponse = "add task CS2113T tP -d LOL -i before tutorial";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void testParseAddLesson() {
        String userResponse = "add lesson CS2113T Lecture -d MON -s 2pm -e 4pm";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddLessonCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddLesson_wrongFlag_exceptionThrown() {
        String userResponse = "add lesson CS2113T Lecture -d MON -e 2pm -s 4pm";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void parseAddLesson_invalidDayOfTheWeek_exceptionThrown() {
        String userResponse = "add lesson CS2113T Lecture -d LOL -s 2pm -e 4pm";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void testMarkTaskAsDone() {
        String userResponse = "done 1";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof DoneCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseMarkTaskDone_indexNotANumber_exceptionThrown() {
        String userResponse = "done m";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }
}
