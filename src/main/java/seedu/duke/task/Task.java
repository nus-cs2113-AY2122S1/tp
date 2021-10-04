package seedu.duke.task;

public class Task {
    private String title;
    private String dayOfTheWeek;
    private String information;
    private boolean isDone;

    /**
     * Constructor for the Task class without the parameter "information" given.
     *
     * @param title title of the task
     * @param dayOfTheWeek day of the week the task is to be done
     */
    public Task(String title, String dayOfTheWeek) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = "";
        this.isDone = false;
    }

    /**
     * Constructor for the Task with the parameter "information" given.
     *
     * @param title title of the task
     * @param dayOfTheWeek day of the week the task is to be done
     * @param information extra information on the task
     */
    public Task(String title, String dayOfTheWeek, String information) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
        this.isDone = false;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     * Returns the symbol representing the done status of the task.
     *
     * @return the symbol representing whether the task is done
     */
    public String getDoneSymbol() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        if (information.equals("")) {
            return ("[T]" + getDoneSymbol() + " "
                    + "Title: " + title + " ");
        } else {
            return "[T]" + getDoneSymbol() + " " + "Title: " + title + " " + "(Info: " + information + ")";
        }
    }
}
