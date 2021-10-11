package seedu.duke.task;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> taskList;

    /**
     * Creates a new TaskList when no saved data file is found
     * (e.g. when starting the program for the first time)
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a new TaskList when loading from an existing list.
     *
     * @param taskList the existing task list
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param newTask the task to add
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Deletes all tasks from the task list.
     */
    public void clearTaskList() {
        taskList.clear();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Gets the size of the task list.
     *
     * @return the number of tasks in the task list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index the index of the task to delete
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

    /**
     * Gets information of a task from the task list.
     *
     * @param index the index of the task
     * @return the task represented by the specified index
     */
    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException exception) {
            throw new DukeException(Message.ERROR_NUMBER_FORMAT);
        }
    }

    /**
     * Gets the number of pending tasks.
     *
     * @return the number of tasks not marked as done
     */
    public int getNumberOfPendingTasks() {
        int count = 0;
        for (Task task : taskList) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of the task to mark as done.
     * @throws DukeException when the index given is out-of-bounds
     */
    public void markTaskAsDone(int index) throws DukeException {
        try {
            Task task = taskList.get(index);
            task.setDone();
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Gets tasks containing the specified keyword.
     *
     * @param keyword the keyword to find in tasks in the list
     * @return the filtered task list containing only tasks with the specified keyword
     */
    public TaskList filterTasksByKeyword(String keyword) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Gets tasks with the specified period.
     *
     * @param period the day of the week for the task/lesson
     * @return the filtered task list containing only tasks with the specified period
     */
    public TaskList filterTasksByPeriod(String period) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getDayOfTheWeek().toLowerCase().contains(period))
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            s.append(Ui.PADDING).append(i + 1).append(". ").append(task).append(System.lineSeparator());
        }
        return s.toString();
    }

    /**
     * Serializes the task list into its file data storage format.
     *
     * @return the serialized task list
     */
    public String serialize() {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.serialize()).append(System.lineSeparator());
        }
        return data.toString();
    }

    /**
     * Filters out strings representing task data from a list of strings and deserializes
     * them into a list of task objects.
     *
     * @param data the list of strings
     * @return the list of task objects
     * @throws DukeException when there is task data that is not deserializable
     */
    public static List<Task> deserialize(List<String> data) throws DukeException {
        List<Task> taskList = new ArrayList<>();
        for (String entry : data) {
            if (entry.charAt(0) == 'T') {
                taskList.add(Task.deserialize(entry));
            }
        }
        return taskList;
    }
}
