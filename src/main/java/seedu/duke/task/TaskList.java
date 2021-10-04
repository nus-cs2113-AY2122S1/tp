package seedu.duke.task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Replaces the current task list with a given one.
     *
     * @param newTaskList the new task list given
     */
    public void loadTaskList(List<Task> newTaskList) {
        taskList = newTaskList;
    }

    public void addTask(Task newTask) {
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
    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
