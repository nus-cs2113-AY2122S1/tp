package seedu.duke.task.taskmanager;

//@@author SeanRobertDH
/**
 * Observer interface used by {@link seedu.duke.storage.DataManager}
 * to observe {@link seedu.duke.task.taskmanager.TaskManager}.
 * Uses Observer design pattern.
 */
public interface Observer {
    void update();
}
