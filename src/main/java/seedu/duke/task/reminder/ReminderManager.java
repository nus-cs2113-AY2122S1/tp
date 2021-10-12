package seedu.duke.task.reminder;

import seedu.duke.task.TaskManager;

import java.time.LocalDateTime;

public class ReminderManager {
    private static TaskManager taskManager;

    public ReminderManager() {
        this.taskManager = new TaskManager();
    }

    public void updateReminderManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /*
    public void printReminder() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < taskManager.getTasklist().size(); i++) {
            if (taskManager.getTasklist().get(i).needReminder()) {
                taskManager.getTasklist().get(i).displayReminder(now);
            }
        }
    }
    */

    public static String sendReminder() {
        LocalDateTime now = LocalDateTime.now();
        String message = "";
        for (int i = 0; i < taskManager.getTasklist().size(); i++) {
            if (taskManager.getTasklist().get(i).needReminder()) {
                message += (taskManager.getTasklist().get(i).getReminder(now));
            }
        }
        return message;
    }

}
