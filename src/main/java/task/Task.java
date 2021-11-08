package task;


import command.parser.DateParser;
import command.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Task {
    private static final Logger logger = command.Logger.myLogger();
    private static final String LATE_SYMBOL = "[LATE]";
    private static final String DONE_SYMBOL = "[X]";
    private static final String EMPTY_SYMBOL = "[ ]";
    private static final String EMPTY_SPACE = " ";
    protected String description;
    protected boolean isDone;
    protected boolean isLate;
    protected String date;
    protected LocalDateTime deadline;


    /**
     * temp message.
     *
     * @param description The description of the task given by the user
     */
    public Task(String description, String date) {
        setDescription(description);
        setDate(date);
        setDone(false);
        setLate(false);
    }

    /**
     * For deserialization from JSON file.
     */
    public Task() {
    }

    //Getters
    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Needed for serialization of data.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    private void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDate(String date) {
        this.date = date;
        setDeadline(DateParser.parseDate(date));
    }

    public void parseDeadline(String date) throws DateTimeException {
        try {
            logger.log(Level.INFO, "Successfully set Task deadline...");
            setDate(date);
            updateOverdue();
            Ui.printUpdateTaskDeadline(this);
        } catch (DateTimeException e) {
            Ui.wrongDateTimeFormat();
        }
    }

    public String createFormattedDeadline() throws NullPointerException {
        try {
            return DateParser.dateStringOutput(this.deadline);
        } catch (NullPointerException e) {
            Ui.invalidDate();
        }
        return "";
    }

    public void markDone() {
        logger.log(Level.INFO, "Successfully marked Task as done...");
        this.isDone = true;
        Ui.printMarkDoneMessage(this);
    }

    public void markNotDone() {
        logger.log(Level.INFO, "Successfully marked Task as not done...");
        this.isDone = false;
        Ui.printMarkNotDoneMessage(this);
    }

    public void setLate(boolean isLate) {
        this.isLate = isLate;
    }

    /**
     * Checks if the task is overdue by checking the deadline of the task
     * against the system clock. If it is overdue, update the task attribute.
     */
    public void updateOverdue() throws NullPointerException {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.isLate = !(this.isDone) && currentDateTime.isAfter(this.deadline);
        } catch (NullPointerException e) {
            Ui.printInvalidIndex();
        }
    }

    private String createLateIcon() {
        return this.isLate ? LATE_SYMBOL : "";
    }

    public String createStatusIcon() {
        return this.isDone ? DONE_SYMBOL : EMPTY_SYMBOL;
    }

    public String toString() {
        return createLateIcon() + createStatusIcon() + EMPTY_SPACE + getDescription()
                + " by: " + createFormattedDeadline();
    }
}
