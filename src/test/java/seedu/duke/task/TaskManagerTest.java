package seedu.duke.task;

import java.text.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class TaskManagerTest {

    TaskManager taskManager = new TaskManager();
    ArrayList<Task> tasklist = new ArrayList<Task>();
    private static final String VALID_DATE1 = "14-02-1998 02:00:00";
    private static final String VALID_DATE2 = "14-02-1998 03:30:00";

    @Test
    @DisplayName("Used to check the printing of the tasklist format")
    void testListTasklistFormat() throws ParseException {

        Date startDate = UtilityParser.getStringAsDate(VALID_DATE1);
        Date endDate = UtilityParser.getStringAsDate(VALID_DATE2);

        Task newToDo = new Todo("read book", PriorityEnum.LOW, startDate, RecurrenceEnum.DAILY);
        Task newDeadline = new Deadline("return book", startDate, PriorityEnum.MEDIUM, RecurrenceEnum.WEEKLY);
        Task newEvent = new Event("project meeting", startDate, endDate, PriorityEnum.MEDIUM, RecurrenceEnum.MONTHLY);

        tasklist.add(newToDo);
        tasklist.add(newDeadline);
        tasklist.add(newEvent);

        taskManager.setTasklist(tasklist);

        try {
            System.out.println(taskManager.listTasklist());
        } catch (EmptyTasklistException e) {
            System.out.println(e);
        }
    }


}