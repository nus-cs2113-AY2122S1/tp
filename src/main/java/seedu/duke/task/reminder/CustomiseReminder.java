package seedu.duke.task.reminder;

import seedu.duke.task.taskmanager.TaskManager;

public class CustomiseReminder {
    public CustomiseReminder() {

    }

    public static void updateReminderTime(TaskManager taskManager, int index, long time) {
        taskManager.getTask(index-1).updateReminderTime(time);
    }

    public static void updateReminderMessage(TaskManager taskManager, int index, String message) {
        taskManager.getTask(index-1).updateReminderMessage(message);
    }
}
