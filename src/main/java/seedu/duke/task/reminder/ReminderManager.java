package seedu.duke.task.reminder;

import seedu.duke.task.taskmanager.TaskManager;

import java.time.LocalDateTime;

public class ReminderManager {

    public ReminderManager() {

    }

    public static String sendReminder(TaskManager taskManager) {
        LocalDateTime now = LocalDateTime.now();
        String message = "";
        for (int i = 0; i < taskManager.getTaskListSize(); i++) {
            if (taskManager.getTask(i).needReminder()) {
                message += (taskManager.getTask(i).getReminder(now));
            }
        }
        return message;
    }

}
