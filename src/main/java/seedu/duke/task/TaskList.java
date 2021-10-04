package seedu.duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    /**
     * Replaces the current task list with a given one.
     *
     * @param newTaskList the new task list given
     */
    public void loadTaskList(ArrayList<Task> newTaskList) {
        taskList = newTaskList;
    }

    public void addTaskToList(Task newTask) {
        taskList.add(newTask);
    }

    public void clearTaskList() {
        taskList.clear();
    }

    /**
     * Removes the Task of the given index from the task list.
     *
     * @param index the index of the task to be removed
     */
    public void deleteTaskFromList(int index) {
        taskList.remove(index);
    }
}
