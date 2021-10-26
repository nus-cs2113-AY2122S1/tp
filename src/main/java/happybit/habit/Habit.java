package happybit.habit;

import happybit.progress.Progress;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class Habit {

    protected String habitName;
    protected Date habitDate; // most recent date habit was due
    protected Date nextHabitDate;
    protected int interval; // one time habit => user inputs 0 or nothing
    protected HashMap<Date, Progress> progressHashMap;

    /**
     * Constructor for Habit class.
     *
     * @param habitName Name of habit linked to a goal.
     * @param interval Integer of interval set by user
     */
    public Habit(String habitName, int interval) {
        this.habitName = habitName;
        // date habit is set; current date
        this.habitDate = new Date();
        this.interval = interval;
        // get next date upon declaration
        if (interval == 0) {
            this.nextHabitDate = null;
        } else {
            setNextHabitDate();
        }
        progressHashMap = new HashMap<>();
    }

    // ACCOUNT FOR USER NOT LOGGING IN FOR MANY DAYS
    /**
     * Constructor for Habit class when importing from storage.
     * Date to keep track of last date
     *
     * @param habitName String parameter of habit name
     * @param interval integer storing interval for habit
     * @param habitDate Date parameter storing last date for Habit
     */
    public Habit(String habitName,  Date habitDate, int interval,
                 HashMap<Date, Progress> progressHashMap) {
        this.habitName = habitName;
        this.habitDate = habitDate;
        this.interval = interval;
        if (interval == 0) {
            this.nextHabitDate = null;
        } else {
            setNextHabitDate();
        }
        this.progressHashMap = progressHashMap;
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


    public Date getHabitDate() {
        return habitDate;
    }

    public String getHabitDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(habitDate);
    }

    public void setHabitDate(Date date) {
        this.habitDate = date;
    }

    /**
     * Next Date set for current habit based on interval.
     *
     * @return next Date milestone for user
     */
    public Date getNextHabitDate() {
        return nextHabitDate;
    }

    /**
     * Next Date in printable format.
     *
     * @return String formatted next date milestone
     */
    public String getNextHabitDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(nextHabitDate);
    }

    public void setNextHabitDate() {
        this.nextHabitDate = calculateNextDate();
    }

    /**
     * Get interval set by the user.
     *
     * @return Integer of interval set by user
     */
    public int getInterval() {
        return this.interval;
    }

    /**
     * Sets a new interval for the specified habit.
     *
     * @param interval Integer value of the new interval user wishes to have
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * Method checking if next cycle for habit has started.
     * Next cycle reached if currDate user is logging in is equal to nextCycleDate
     *
     * @return Boolean indicating whether next cycle has started.
     */
    public boolean isNextCycle(Date currDate) {
        LocalDate currDateLD = convertDateToLocalDate(currDate);
        LocalDate nextHabitDateLD = convertDateToLocalDate(nextHabitDate);
        if (currDateLD.isEqual(nextHabitDateLD) || currDateLD.isAfter(nextHabitDateLD)) {
            return true;
        }
        return false;
    }

    /**
     * Method called whenever habitDate is set (new habit instantiation) or changed(recurring).
     * Adds new KeyValuePair with habitDate as key and Progress object as value.
     *
     */
    public void addProgress() {
        Progress newProgress = new Progress();
        progressHashMap.put(habitDate, newProgress);
    }

    /**
     * Method called whenever user calls DoneCommand for a habit.
     * Updates isDone and isDoneOnTime attributes in Progress along with Date
     */
    public void updateProgress() {
        Date currDate = new Date();
        // check if already past habit Date; not done on time
        // all marking as Done progress will be for current iteration
        // past added progress not updated will remain as undone => allow user to go back and change
        Progress doneProgress = new Progress(currDate);
        // find desired instance using current iteration habitDate
        progressHashMap.put(habitDate, doneProgress);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private Date calculateNextDate() {
        // convert habitDate to LocalDate format to add by number of days specified in interval
        LocalDate currHabitDate = convertDateToLocalDate(habitDate);
        LocalDate nextDate = currHabitDate.plusDays(interval);
        // convert nextDate to Date format and return
        return convertLocalDateToDate(nextDate);
    }

    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date convertLocalDateToDate(LocalDate localDate) {
        return java.util.Date.from(
                localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
