package seedu.duke;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {

    private final List<Task> tasksList = new ArrayList<>();

    /** Constructor of the TaskList. */
    public TaskList() {}

    /**
     * Add a task to the list.
     *
     * @param toAdd The task to add.
     */
    public void add(Task toAdd) {
        tasksList.add(toAdd);
    }

    /**
     * Get a task.
     *
     * @param toGet The task index.
     * @return the task got.
     */
    public Task get(int toGet) {
        return tasksList.get(toGet);
    }

    /**
     * Get the size of the tasks list.
     *
     * @return the size of tasksList.
     */
    public int size() {
        return tasksList.size();
    }

    /**
     * Get all the tasks in the list.
     *
     * @return all tasks in list.
     */
    public List<Task> listView() {
        return Collections.unmodifiableList(tasksList);
    }

    /**
     * Remove a task in the list.
     *
     * @param toRemove The task to remove.
     */
    public void remove(Task toRemove) {
        tasksList.remove(toRemove);
    }

    public static class TaskNotFoundException extends Exception {}

}
