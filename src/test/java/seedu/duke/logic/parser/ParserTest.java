package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.commands.task.DoneTaskCommand;
import seedu.duke.DukeException;

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
        String userResponse = "add lesson CS2113T Lecture -d FRI -s 16:00 -e 18:00";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddLessonCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddLesson_wrongFlag_exceptionThrown() {
        String userResponse = "add lesson CS2113T Lecture -d MON -e 18:00 -s 16:00";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void parseAddLesson_invalidDayOfTheWeek_exceptionThrown() {
        String userResponse = "add lesson CS2113T Lecture -d LOL -s 16:00 -e 18:00";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void testMarkTaskAsDone() {
        String userResponse = "done task 1";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof DoneTaskCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseMarkTaskDone_indexNotANumber_exceptionThrown() {
        String userResponse = "done task m";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseListModule_invalidArgument_exceptionThrown() {
        String userResponse = "list module lol";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }
}
