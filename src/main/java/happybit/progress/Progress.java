package happybit.progress;

import java.util.Date;

public class Progress {
    // as long as marked done between each interval
    protected boolean isDone;
    // date it was set as Done
    protected Date completedDate;

    /**
     * Constructor for Progress when called from addProgress.
     * New increment for newly set Habit or recurring Habit
     */
    public Progress() {
        this.isDone = false;
        this.completedDate = null;
    }

    /**
     * Constructor for Progress when called from updateProgress.
     * isDone set to true
     *
     * @param completedDate Date user marks it as done/
     */
    public Progress(Date completedDate) {
        this.isDone = true;
        this.completedDate = completedDate;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }
}
