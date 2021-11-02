package seedu.duke.task.reminder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReminderManagerTest {

    private String expectedOut;

    ReminderManagerTest(){
    }

    @Test
    void sendReminder() {
        TaskManager taskManager = new TaskManager();
        Calendar start = Calendar.getInstance();
        start.add(Calendar.MINUTE, 10);
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, 20);
        LocalDateTime startDate = start.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = end.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Task todoWithReminder = new Todo("lecture with reminder", startDate);
        taskManager.addTask(todoWithReminder);
        Task todoWithoutReminder = new Todo("lecture without reminder", endDate);
        taskManager.addTask(todoWithoutReminder);
        Task deadlineWithReminder = new Deadline("exercise 1", startDate);
        taskManager.addTask(deadlineWithReminder);
        Task deadlineWithoutReminder = new Deadline("exercise 1", endDate);
        taskManager.addTask(deadlineWithoutReminder);
        Task eventTest = new Event("meeting", startDate, endDate);
        taskManager.addTask(eventTest);

        expectedOut = "Reminder! 10 min before the following tas:\n" + "\t"
                + todoWithReminder.getTaskEntryDescription() + "\n"
                + "Reminder! 10 min before the following task:\n" + "\t"
                + deadlineWithReminder.getTaskEntryDescription() + "\n"
                + "Reminder! 10 min before the following task:\n" + "\t"
                + eventTest.getTaskEntryDescription() + "\n";

        assertEquals(expectedOut, ReminderManager.sendReminder(taskManager));
    }
}