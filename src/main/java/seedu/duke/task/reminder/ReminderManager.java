package seedu.duke.task.reminder;

import seedu.duke.task.TaskManager;

import java.time.LocalDateTime;

public class ReminderManager {
    private static TaskManager taskManager;

    public ReminderManager() {
        this.taskManager = new TaskManager();
    }

    public void setUpReminderManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void sendReminder() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < taskManager.getTasklist().size(); i++) {
            if (taskManager.getTasklist().get(i).needReminder()) {
                taskManager.getTasklist().get(i).displayReminder(now);
            }
        }
    }

/* for Junit Test
    public static String testReminder() {
        LocalDateTime now = LocalDateTime.now();
        String testOut = "";
        for (int i = 0; i < taskManager.getTasklist().size(); i++) {
            if (taskManager.getTasklist().get(i).needReminder()) {
                testOut += (taskManager.getTasklist().get(i).reminderForTest(now));
            }
        }
        return testOut;
    }
 */

}
