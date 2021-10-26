package happybit.progress;

import java.util.Date;

public class Progress {
    // as long as marked done between each interval
    protected boolean isDone;
    // if marked done ON habitDate
    protected boolean isDoneOnTime;
    // date it was set as Done
    protected Date completedDate;

    /**
     * Constructor for Progress when called from addProgress.
     * New increment for newly set Habit or recurring Habit
     */
    public Progress() {
        this.isDone = false;
        this.isDoneOnTime = false;
        this.completedDate = null;
    }

    /**
     * Constructor for Progress when called from updateProgress.
     * isDone set to true
     * completedDate set
     * isDoneOnTime indicator set based on input passed in
     */
    public Progress(boolean isDoneOnTime, Date completedDate) {
        this.isDone = true;
        this.isDoneOnTime = isDoneOnTime;
        this.completedDate = completedDate;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void setDoneOnTime() {
        this.isDoneOnTime = true;
    }

    public boolean getDoneOnTime() {
        return this.isDoneOnTime;
    }
}
