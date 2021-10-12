package seedu.duke.task;

import java.time.LocalDateTime;

public abstract class Task {

    static final PriorityEnum DEFAULT_PRIORITY = PriorityEnum.MEDIUM;

    String description;
    PriorityEnum priority;

    protected Task(String description) {
        this.description = description;
        this.priority = DEFAULT_PRIORITY;
    }

    protected Task(String description, PriorityEnum priority) {
        this(description);
        this.priority = priority;
    }

    public String getTaskEntryDescription() {
        return this.description + " [" + this.priority + "]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public abstract void displayReminder(LocalDateTime now);
}
