package seedu.duke.task.reminder;

import java.text.ParseException;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReminderManagerTest {

    private static final String VALID_DATE1 = "14-02-1998 02:00:00";
    private static final String VALID_DATE2 = "14-02-1998 03:30:00";

    Date startDate = UtilityParser.getStringAsDate(VALID_DATE1);
    Date endDate = UtilityParser.getStringAsDate(VALID_DATE2);

    ReminderManager reminderManager = new ReminderManager();

    private String expectedOut;

    ReminderManagerTest() throws ParseException, ParseDateFailedException {
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
        TaskManager.addTask(todoWithReminder);
        Task todoWithoutReminder = new Todo("lecture without reminder", endDate);
        TaskManager.addTask(todoWithoutReminder);
        Task deadlineWithReminder = new Deadline("exercise 1", startDate);
        TaskManager.addTask(deadlineWithReminder);
        Task deadlineWithoutReminder = new Deadline("exercise 1", endDate);
        TaskManager.addTask(deadlineWithoutReminder);
        Task eventTest = new Event("meeting", startDate, endDate);
        TaskManager.addTask(eventTest);

        expectedOut = "Reminder! 10 min before the following task:\n" + "\t"
                + todoWithReminder.getTaskEntryDescription()
                + "Reminder! 10 min before the following task:\n" + "\t"
                + deadlineWithReminder.getTaskEntryDescription()
                + "Reminder! 10 min before the following task:\n" + "\t"
                + eventTest.getTaskEntryDescription();

        assertEquals(expectedOut, ReminderManager.sendReminder());
    }
}