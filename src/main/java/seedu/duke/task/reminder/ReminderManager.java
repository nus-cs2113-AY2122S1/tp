package seedu.duke.task.reminder;

import seedu.duke.task.TaskManager;

import java.time.LocalDateTime;

public class ReminderManager {

    public ReminderManager() {

    }

    public static String sendReminder() {
        LocalDateTime now = LocalDateTime.now();
        String message = "";
        for (int i = 0; i < TaskManager.getTaskList().size(); i++) {
            if (TaskManager.getTask(i).needReminder()) {
                message += TaskManager.getTask(i).getReminder(now);
            }
        }
        return message;
    }

}
