package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.ListFormatException;
import seedu.duke.exception.MissingFilterArgumentException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.parser.DateParser;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.HashMap;
import java.util.Map;

//@@author SeanRobertDH
class TaskManagerTest {

    Task newToDo;
    Task newDeadline;
    Task newEvent;
    private static final String VALID_DATE1 = "22-10-2021 02:00";
    private static final String VALID_DATE2 = "22-10-2021 05:00";

    private TaskManager taskManager;



    private TaskManagerTest() throws ParseDateFailedException {
        taskManager = new TaskManager();
        LocalDateTime startDate = DateParser.stringToDate(VALID_DATE1);
        LocalDateTime endDate = DateParser.stringToDate(VALID_DATE2);
        newToDo = new Todo("read book", PriorityEnum.LOW, startDate, RecurrenceEnum.DAILY);
        newDeadline = new Deadline("return book", startDate, PriorityEnum.MEDIUM, RecurrenceEnum.WEEKLY);
        newEvent = new Event("project meeting", startDate, endDate, PriorityEnum.HIGH, RecurrenceEnum.MONTHLY);
        taskManager.addTask(newToDo);
        taskManager.addTask(newEvent);
        taskManager.addTask(newDeadline);
    }

    @Test
    void testListTasklistFormat() {
        Map<String, String> listArguments = new HashMap<>();
        listArguments.put("random_flag", "random_criteria");
        try {
            System.out.println("Testing List Command");
            System.out.println(taskManager.listTasklist(listArguments));
        } catch (EmptyTasklistException ete) {
            System.out.println(ete);
        } catch (ListFormatException lfe) {
            System.out.println(lfe);
        } catch (MissingFilterArgumentException mfae) {
            System.out.println(mfae);
        }
    }

    @Test
    void testSortTasklistByPriority() {

        Map<String, String> sortArguments = new HashMap<>();
        sortArguments.put("by", "priority");

        Map<String, String> listArguments = new HashMap<>();

        try {
            System.out.println("Testing Sort by Priority Command");
            taskManager.sortTasklist(sortArguments);
            System.out.println(taskManager.listTasklist(listArguments));
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }

    }

    @Test
    void testSortTasklistByDescription() {

        Map<String, String> sortArguments = new HashMap<>();
        sortArguments.put("by", "description");

        Map<String, String> listArguments = new HashMap<>();

        try {
            System.out.println("Testing Sort by Description Command");
            taskManager.sortTasklist(sortArguments);
            System.out.println(taskManager.listTasklist(listArguments));
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }

    }

    @Test
    void testSortTasklistByTaskType() {

        Map<String, String> sortArguments = new HashMap<>();
        sortArguments.put("by", "type");

        Map<String, String> listArguments = new HashMap<>();

        try {
            System.out.println("Testing Sort by Task Type Command");
            taskManager.sortTasklist(sortArguments);
            System.out.println(taskManager.listTasklist(listArguments));
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }

    }

    @Test
    void sortTasklist_ThrowsException_IfMissingByFlag() {

        Map<String, String> sortArguments = new HashMap<>();
        sortArguments.put("random_flag", "random_criteria");

        try {
            taskManager.sortTasklist(sortArguments);
            fail(); // the test should not reach here
        } catch (Exception e) {
            System.out.println("SortFormatException caught");
            assertEquals("[!] Your sort command is wrong...\n"
                    + "Please follow the format: sort --by [criteria]", e.toString());
        }
    }

    @Test
    void sortTasklist_ThrowsException_IfSortCriteriaEmpty() {

        Map<String, String> commandArguments = new HashMap<>();
        commandArguments.put("by", "");

        try {
            taskManager.sortTasklist(commandArguments);
            fail(); // the test should not reach here
        } catch (Exception e) {
            System.out.println("EmptySortCriteriaException caught");
            assertEquals("[!] Please specify a sort criteria...\n", e.toString());
        }
    }
}