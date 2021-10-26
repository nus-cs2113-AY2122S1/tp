package seedu.duke.task.reminder;

import seedu.duke.task.TaskManager;

public class CustomiseReminder {
    public CustomiseReminder() {

    }

    public static void updateReminderTime(int index, long time) {
        TaskManager.getTask(index-1).updateReminderTime(time);
    }

    public static void updateReminderMessage(int index, String message) {
        TaskManager.getTask(index-1).updateReminderMessage(message);
    }
}
