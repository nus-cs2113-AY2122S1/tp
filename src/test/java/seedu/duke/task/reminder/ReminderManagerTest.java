package seedu.duke.task.reminder;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.Parser;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReminderManagerTest {

    TaskManager taskManager = new TaskManager();
    ArrayList<Task> tasklist = new ArrayList<Task>();
    Date startDate = new Date();
    Date endDate = new Date();

    ReminderManager reminderManager = new ReminderManager();

    private final String expectedOut = "Reminder! 10 min before the following task:\n"
            + "Reminder! 10 min before the following task:\n"
            + "Reminder! 10 min before the following task:\n";

    @Test
    void sendReminder() {
        Calendar taskTime = Calendar.getInstance();
        taskTime.add(Calendar.MINUTE, 10);
        startDate = taskTime.getTime();
        endDate = Calendar.getInstance().getTime();

        Task todoWithReminder = new Todo("lecture with reminder", startDate);
        Task todoWithoutReminder = new Todo("lecture without reminder", endDate);
        Task deadlineWithReminder = new Deadline("exercise 1", startDate);
        Task deadlineWithoutReminder = new Deadline("exercise 1", endDate);
        Task eventTest = new Event("meeting", startDate, endDate);

        tasklist.add(todoWithReminder);
        tasklist.add(todoWithoutReminder);
        tasklist.add(deadlineWithReminder);
        tasklist.add(deadlineWithoutReminder);
        tasklist.add(eventTest);

        taskManager.setTasklist(tasklist);
        reminderManager.updateReminderManager(taskManager);
        assertEquals(expectedOut, ReminderManager.sendReminder());
    }
}