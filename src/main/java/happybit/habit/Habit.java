package happybit.habit;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Habit {

    protected String habitName;
    protected boolean isDone;
    protected Date lastHabitDate; // change to nextHabitDate
    protected int interval;
    // Map <Date, isDone> for Progress

    // one time habit => user inputs 0 or nothing

    /**
     * Constructor for Habit class.
     *
     * @param habitName Name of habit linked to a goal.
     * @param interval Integer of interval set by user
     */
    public Habit(String habitName, int interval) {
        this.habitName = habitName;
        this.isDone = false;
        // date habit is set; current date
        this.lastHabitDate = new Date();
        this.interval = interval;
    }

    /**
     * Constructor for Habit class when importing from storage.
     * Date to keep track of last date
     *
     * @param habitName String parameter of habit name
     * @param interval integer storing interval for habit
     * @param lastHabitDate Date parameter storing last date for Habit
     */
    public Habit(String habitName,  Date lastHabitDate, int interval) {
        this.habitName = habitName;
        this.isDone = false;
        this.lastHabitDate = lastHabitDate;
        this.interval = interval;
    }

    /**
     * Setter for name of habit.
     * Used to edit the name of the goal.
     *
     * @param habitName New name the habit is to be updated with.
     */
    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    /**
     * Getter for habitName.
     *
     * @return String containing name of habit.
     */
    public String getHabitName() {
        return habitName;
    }

    /**
     * Getter for isDone.
     *
     * @return Boolean value of whether the habit has been completed.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Update the habit as completed.
     */
    public void setCompleted() {
        isDone = true;
    }

    /**
     * Update the habit as uncompleted for next interval.
     */
    public void setUncompleted() {
        isDone = false;
    }

    /**
     * Next Date depending on interval user set.
     *
     * @return next Date milestone for user
     */
    public Date getNextDate() {
        // convert habitDate to LocalDate format to add by number of days specified in interval
        LocalDate currHabitDate = lastHabitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate nextDate = currHabitDate.plusDays(interval);

        // convert nextDate to Date format
        Date nextDateInDateFormat = java.util.Date.from(
                nextDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        return nextDateInDateFormat;

    }

    /**
     * Next Date in printable format.
     *
     * @return String formatted next date milestone
     */
    public String getNextDateString() {
        Date nextDate = getNextDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(nextDate);
    }

    public Date getHabitDate() {
        return lastHabitDate;
    }

    public String getHabitDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(lastHabitDate);
    }

    public void setHabitDate(Date date) {
        this.lastHabitDate = date;
    }

    /**
     * Get interval set by the user.
     *
     * @return Integer of interval set by user
     */
    public int getInterval() {
        return this.interval;
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

}
