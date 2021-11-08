package seedu.tp.task.taskmanager;

//@@author SeanRobertDH
/**
 * Observer interface used by {@link seedu.tp.storage.DataManager}
 * to observe {@link seedu.tp.task.taskmanager.TaskManager}.
 * Uses Observer design pattern.
 */
public interface Observer {
    void update();
}
