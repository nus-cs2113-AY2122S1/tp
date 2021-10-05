package seedu.duke.task;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Message;

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
    public void deleteTask(int index) throws DukeException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    public void markTaskAsDone(int index) throws DukeException {
        try {
            taskList.get(index).setDone();
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    public TaskList findTasks(String keyword) {
        TaskList matchingTaskList = new TaskList();
        for (Task task: taskList) {
            if (task.getInformation().contains(keyword)) {
                matchingTaskList.addTask(task);
            }
        }
        return matchingTaskList;
    }
}
