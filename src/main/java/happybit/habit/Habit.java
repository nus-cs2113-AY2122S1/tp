package happybit.habit;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Habit {

    protected String habitName;
    protected boolean isDone;
    protected Date setDate;
    protected int interval;

    /**
     * Constructor for Habit class.
     *
     * @param habitName Name of habit linked to a goal.
     */
    public Habit(String habitName, int interval) {
        this.habitName = habitName;
        this.isDone = false;
        // date habit is set; current date
        this.setDate = new Date();
        this.interval = interval;
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
     * Next Date depending on interval user set.
     *
     * @return String formatted next date milestone for user
     */
    public String getNextDate() {
        LocalDate currSetDate = setDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate nextDate = currSetDate.plusDays(interval);

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date nextDateInDateFormat = java.util.Date.from(
                nextDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return dateFormat.format(nextDateInDateFormat);
    }

    /**
     * Get interval set by the user.
     *
     * @return Integer of interval set by user
     */
    public int getInterval() {
        return this.interval;
    }

}
