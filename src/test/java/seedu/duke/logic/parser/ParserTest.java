package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.lesson.FindLessonCommand;
import seedu.duke.logic.commands.module.FindModuleCommand;
import seedu.duke.logic.commands.task.FindTaskCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.commands.task.DoneTaskCommand;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@@author Roycius
public class ParserTest {
    @Test
    public void parseAddTask_noPriority_valid() {
        String userResponse = "add task CS2113T tP -d MON -i before tutorial";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddTask_noInfo_valid() {
        String userResponse = "add task CS2113T tP -d MON -p HIGH";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddTask_hasAllParameters_valid() {
        String userResponse = "add task CS2113T tP -d MON -p HIGH -i before tutorial";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddTaskCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddTask_hasJumbledFlags_valid() {
        String userResponse = "add task CS2113T tP -p HIGH -d MON -i before tutorial";
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
    public void parseAddTask_duplicateFlag_exceptionThrown() {
        String userResponse = "add task CS2113T tP -d MON -i before tutorial -d MON";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void parseAddTask_invalidDayOfTheWeek_exceptionThrown() {
        String userResponse = "add task CS2113T tP -d LOL -i before tutorial";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void parseAddTask_noTitle_exceptionThrown() {
        String userResponse = "add task  -d MON -p HIGH";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void parseAddLesson_noLink_valid() {
        String userResponse = "add lesson CS2113T Lecture -d FRI -s 16:00 -e 18:00";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddLessonCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddLesson_hasAllParameters_valid() {
        String userResponse = "add lesson CS2113T Lecture -d FRI -s 16:00 -e 18:00 -l www.link.com";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddLessonCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddLesson_hasJumbledFlags_valid() {
        String userResponse = "add lesson CS2113T Lecture -e 18:00 -s 16:00 -l www.link.com -d FRI";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof AddLessonCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseAddLesson_invalidDayOfTheWeek_exceptionThrown() {
        String userResponse = "add lesson CS2113T Lecture -d LOL -s 16:00 -e 18:00 -l www.link.com";
        assertThrows(DukeException.class, () -> Parser.parse(userResponse));
    }

    @Test
    public void testMarkTaskAsDone_indexIsNumber_valid() {
        String userResponse = "done task 1";
        try {
            Command command = Parser.parse(userResponse);
            assertTrue(command instanceof DoneTaskCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseMarkTaskAsDone_indexNotANumber_exceptionThrown() {
        String userResponse = "done task m";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseCommand_extraTrailingWords_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("help invalid"));
        assertThrows(DukeException.class, () -> Parser.parse("exit invalid"));
    }

    @Test
    public void parseFindCommand_validArgument_valid() {
        try {
            Command lessonCommand = Parser.parse("find lesson test");
            assertTrue(lessonCommand instanceof FindLessonCommand);
            Command taskCommand = Parser.parse("find task test");
            assertTrue(taskCommand instanceof FindTaskCommand);
            Command moduleCommand = Parser.parse("find module test");
            assertTrue(moduleCommand instanceof FindModuleCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    //@@author ptejasv
    @Test
    public void parseListLessons_invalidArgument_exceptionThrown() {
        String userResponse = "list lesson invalid";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseListTasks_invalidArgument_exceptionThrown() {
        String userResponse = "list task invalid";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseListModules_invalidArgument_exceptionThrown() {
        String userResponse = "list module invalid";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseDeleteLesson_invalidFormat_exceptionThrown() {
        String userResponse = "delete lesson";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseDeleteLesson_indexIsNotNumber_exceptionThrown() {
        String userResponse = "delete lesson invalid";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseDeleteTask_invalidFormat_exceptionThrown() {
        String userResponse = "delete task";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }

    @Test
    public void parseDeleteTask_indexIsNotNumber_exceptionThrown() {
        String userResponse = "delete task invalid";
        assertThrows(DukeException.class, (() -> Parser.parse(userResponse)));
    }
}
