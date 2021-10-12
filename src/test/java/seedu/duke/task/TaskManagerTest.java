package seedu.duke.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class TaskManagerTest {

    TaskManager taskManager = new TaskManager();
    ArrayList<Task> tasklist = new ArrayList<Task>();
    Date startDate = new Date();
    Date endDate = new Date();

    @Test
    @DisplayName("Used to check the printing of the tasklist format")
    void testListTasklistFormat() {

        startDate = Calendar.getInstance().getTime();
        endDate = Calendar.getInstance().getTime();

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