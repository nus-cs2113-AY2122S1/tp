package happybit.interval;

import java.util.Date;

public class Interval {
    protected boolean isDone;
    protected Date startDate;
    protected Date endDate;
    protected Date completedDate;

    /**
     * Constructor for Progress class.
     * isDone and isDoneOnTime is initialised to false.
     * completedDate is initialised to null.
     *
     * @param startDate Date from when the task can be completed.
     * @param endDate   Date that the task should be completed by.
     */
    public Interval(Date startDate, Date endDate) {
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completedDate = null;
    }

    /**
     * Updates the checkpoint once marked as completed.
     *
     * @param completionDate Date that the checkpoint was completed.
     */
    public void setCompleted(Date completionDate) {
        this.isDone = true;
        this.completedDate = completionDate;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Date getCompletedDate() {
        return this.completedDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Checks if a date is within the interval.
     *
     * @param compareDate Date to compare against the interval.
     * @return Boolean of whether the date is within the interval.
     */
    public boolean isWithinInterval(Date compareDate) {
        if (compareDate.compareTo(this.startDate) >= 0 && compareDate.compareTo(this.endDate) <= 0) {
            return true;
        }
        return false;
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */


}
