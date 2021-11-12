package happybit.interval;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Interval {

    private static final String DATE_CONNECTOR_WORD = " to ";

    protected boolean isDone;
    protected Date startDate;
    protected Date endDate;
    protected Date completedDate;

    /**
     * Constructor for Interval class.
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
     * Updates the interval once marked as completed.
     *
     * @param completionDate Date that the checkpoint was completed.
     */
    public void setCompleted(Date completionDate) {
        this.isDone = true;
        this.completedDate = completionDate;
    }

    /**
     * Gets the description of the interval (in terms of dates).
     *
     * @return Description of the interval.
     */
    public String getDescription() {
        return dateToString(startDate) + DATE_CONNECTOR_WORD + dateToString(endDate);
    }

    /**
     * Getter for whether the interval is completed.
     *
     * @return True if interval is completed, false otherwise.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Getter for start date of interval.
     *
     * @return Start date of interval.
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for end date of interval.
     *
     * @return End date of interval.
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Getter for completed date of interval.
     * 
     * @return Completed date of interval.
     */
    public Date getCompletedDate() {
        return this.completedDate;
    }

    /**
     * Setter for end date of interval.
     * The setting of end date is not done in the constructor due to inaccessibility of goalList at that stage.
     *
     * @param endDate End date of interval.
     */
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
        return compareDate.after(this.startDate) && compareDate.before(this.endDate);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Converts date into a string.
     *
     * @param date Date to be converted.
     * @return String format of a date.
     */
    private String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        return formatter.format(date);
    }

}
