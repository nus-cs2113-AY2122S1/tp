package seedu.duke.task.reminder;

import seedu.duke.task.TaskManager;

import java.time.LocalDateTime;

public class ReminderManager {
    private TaskManager taskManager;

    public ReminderManager() {
        this.taskManager = new TaskManager();
    }

    public void setUpReminderManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void sendReminder() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < taskManager.getTasklist().size(); i++) {
            taskManager.getTasklist().get(i).displayReminder(now);
        }
    }
}
