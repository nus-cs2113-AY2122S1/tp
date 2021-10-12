package seedu.duke.task;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public int getNumberOfPendingTasks() {
        int count = 0;
        for (Task task : taskList) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public void deleteAllTasks() {
        taskList.clear();
    }

    public void markTaskAsDone(int taskIndex) {
        taskList.get(taskIndex).setDone();
    }

    /**
     * Filters the list of tasks based on the specified keyword.
     *
     * @param keyword the specified keyword
     * @return the filtered task list
     */
    public TaskList filterTasksByKeyword(String keyword) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Filters the list of tasks based on the specified period.
     *
     * @param period the specified period
     * @return the filtered task list
     */
    public TaskList filterTasksByPeriod(String period) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getDayOfTheWeek().toLowerCase().contains(period))
                .collect(Collectors.toList()));
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
     * Deserializes the storage file and returns the correct task list.
     *
     * @param data a list of strings representing the serialized data
     * @return deserialized task list
     * @throws DukeException if the data is invalid format
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            s.append(Ui.PADDING).append(i + 1).append(". ").append(task).append(System.lineSeparator());
        }
        return s.toString();
    }
}
