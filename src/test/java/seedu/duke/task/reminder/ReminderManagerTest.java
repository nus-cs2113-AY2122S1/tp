package seedu.duke.task.reminder;

import org.junit.jupiter.api.Test;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.time.LocalDateTime;
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

    @Test
    void sendReminder() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 10);
        startDate = now.getTime();
        endDate = Calendar.getInstance().getTime();

        Task todoWithReminder = new TodoWithReminder("lecture with reminder", startDate);
        Task todoWithoutReminder = new TodoWithReminder("lecture without reminder", endDate);
        Task deadlineTest = new DeadlineWithReminder("exercise 1", startDate);
        Task eventTest = new EventWithReminder("meeting", startDate, endDate);

        tasklist.add(todoWithReminder);
        tasklist.add(todoWithoutReminder);
        tasklist.add(deadlineTest);
        tasklist.add(eventTest);

        taskManager.setTasklist(tasklist);
        reminderManager.setUpReminderManager(taskManager);
    }
}