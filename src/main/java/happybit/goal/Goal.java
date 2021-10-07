package happybit.goal;

import happybit.exception.HappyBitException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal {

    protected String description;
    protected Date startDate;
    protected Date endDate;
    protected boolean isDone;

    // To be implemented in a future iteration
//    protected ArrayList<Milestone> milestoneList = new ArrayList<Milestone>();
//    protected int reminderFrequency;

    /**
     * Constructor for Goal class with startDate defined.
     *
     * @param description Description of the end goal.
     * @param startDate   Start date of progress towards a goal.
     * @param endDate     End date of progress towards a goal.
     * @throws HappyBitException If strings cannot be converted to date or/and
     *                           If start date comes after the end date.
     */
    public Goal(String description, String startDate, String endDate) throws HappyBitException {
        this.description = description;
        this.startDate = stringToDate(startDate);
        this.endDate = stringToDate(endDate);
        this.isDone = false;
        isStartDateBeforeEndDate();
    }

    /**
     * Constructor for Goal class without startDate defined.
     * startDate will be set as current date.
     *
     * @param description Description of the end goal.
     * @param endDate     End date of progress towards a goal.
     * @throws HappyBitException If strings cannot be converted to date or/and
     *                           If start date comes after the end date.
     */
    public Goal(String description, String endDate) throws HappyBitException {
        this.description = description;
        this.startDate = new Date();
        this.endDate = stringToDate(endDate);
        this.isDone = false;
        isStartDateBeforeEndDate();
    }

    /**
     * Gets a string representation of goal details.
     *
     * @return String containing goal description and start-end dates.
     */
    public String getDescription() {
        return this.description + dateDescription();
    }

    /**
     * Indicates goal completion if the current date is equal to or after the endDate.
     * (Logic to be changed to allow user to manually check off the goal as completed)
     */
    public void setDone() {
        if (!isDateBeforeToday(endDate)) {
            isDone = true;
        }
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Formats the start-end dates as a single string for print output.
     *
     * @return String containing start-end dates.
     */
    private String dateDescription() {
        String startDateString = "Start: " + dateToString(this.startDate);
        String endDateString = "End: " + dateToString(this.endDate);
        return " (" + startDateString + " | " + endDateString + ")\n";
    }

    /**
     * Converts a Date into a String.
     *
     * @param date Either a start or end date.
     * @return String formatted from a date in the dd-MMM-yyyy format e.g, 07-Oct-2021.
     */
    private String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        return formatter.format(date);
    }

    /**
     * Converts a String into a Date.
     *
     * @param strDate String representation of a start or end date.
     * @return Date formatted from a string in the dd-MMM-yyyy format e.g, 07-Oct-2021.
     */
    private Date stringToDate(String strDate) throws HappyBitException {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            date = formatter.parse(strDate);
        } catch (ParseException parseException) {
            throw new HappyBitException("Error: Invalid date format. Use dd-MMM-yyyy e.g, 07-Oct-2021.\n");
        }
        return date;
    }

    /**
     * Checks if the current date is before the compared date
     *
     * @param compareDate Date used to compare with current date.
     * @return true: If current date is before the compared date.
     *         false: If current date is after the compared date.
     */
    private boolean isDateBeforeToday(Date compareDate) {
        Date currentDate = new Date();
        if (currentDate.compareTo(compareDate) < 0) {
            return true;
        }
        return false;
    }

    private void isStartDateBeforeEndDate() throws HappyBitException {
        if (this.startDate.compareTo(this.endDate) > 0) {
            throw new HappyBitException("Error: Start Date has to come before End Date ");
        }
    }
}
