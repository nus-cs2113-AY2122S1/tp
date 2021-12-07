package seedu.duke.schedule.task;

import java.util.ArrayList;

//@@author swatimahadevan

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Allows for adding of task to list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Allows for deleting of task from list of tasks.
     *
     * @param index Index of task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Allows for editing of task from list of tasks.
     *
     * @param index Index of task to be edited.
     * @param description Description/name of task to be edited.
     * @param date Date of task to be edited.
     */
    public void editTask(int index, String description, String date) {
        Task task = new Todo(description, date);
        tasks.set(index - 1, task);
    }

}