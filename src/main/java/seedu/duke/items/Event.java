package seedu.duke.items;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Item {

    private final ArrayList<Task> taskList = new ArrayList<>();
    private String venue;
    private double budget;

    public static final String EVENT_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Event(String title, String description, LocalDateTime dateTime, String venue, double budget) {
        super("event", title, description, dateTime);
        this.venue = venue;
        this.budget = budget;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getVenue() {
        return venue;
    }

    public double getBudget() {
        return budget;
    }

    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    public Task getFromTaskList(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    // @@author alwinangys
    public String getTaskListAsString() {
        StringBuilder tasks = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            tasks.append(counter).append(". ");
            tasks.append(task.getTitle());
            if (i != taskList.size() - 1) {
                tasks.append("\n");
            }
            counter++;
        }

        return tasks.toString();
    }
    // @@author alwinangys

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.getTitle(), this.getStringDateTime());
    }
}
