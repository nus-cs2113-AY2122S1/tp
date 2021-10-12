package seedu.duke.task.reminder;

import java.text.ParseException;
import org.junit.jupiter.api.Test;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReminderManagerTest {

    private static final String VALID_DATE1 = "14-02-1998 02:00:00";
    private static final String VALID_DATE2 = "14-02-1998 03:30:00";

    TaskManager taskManager = new TaskManager();
    ArrayList<Task> tasklist = new ArrayList<Task>();
    Date startDate = UtilityParser.getStringAsDate(VALID_DATE1);
    Date endDate = UtilityParser.getStringAsDate(VALID_DATE2);

    ReminderManager reminderManager = new ReminderManager();

    private String expectedOut;

    ReminderManagerTest() throws ParseException {
    }

    @Test
    void sendReminder() {
        Calendar start = Calendar.getInstance();
        start.add(Calendar.MINUTE, 10);
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, 20);
        startDate = start.getTime();
        endDate = end.getTime();

        Task todoWithReminder = new Todo("lecture with reminder", startDate);
        tasklist.add(todoWithReminder);
        Task todoWithoutReminder = new Todo("lecture without reminder", endDate);
        tasklist.add(todoWithoutReminder);
        Task deadlineWithReminder = new Deadline("exercise 1", startDate);
        tasklist.add(deadlineWithReminder);
        Task deadlineWithoutReminder = new Deadline("exercise 1", endDate);
        tasklist.add(deadlineWithoutReminder);
        Task eventTest = new Event("meeting", startDate, endDate);
        tasklist.add(eventTest);

        expectedOut = "Reminder! 10 min before the following task:\n" + "\t"
                + todoWithReminder.getTaskEntryDescription()
                + "Reminder! 10 min before the following task:\n" + "\t"
                + deadlineWithReminder.getTaskEntryDescription()
                + "Reminder! 10 min before the following task:\n" + "\t"
                + eventTest.getTaskEntryDescription();

        taskManager.setTasklist(tasklist);
        reminderManager.updateReminderManager(taskManager);
        assertEquals(expectedOut, ReminderManager.sendReminder());
    }
}