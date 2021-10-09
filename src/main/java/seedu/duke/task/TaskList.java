package seedu.duke.task;

import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    // TODO: Implement serialization/deserialization
    // TODO: Reorder methods

    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void clearTaskList() {
        taskList.clear();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).setDone();
    }

    public TaskList filterTasksByKeyword(String keyword) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

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
}
