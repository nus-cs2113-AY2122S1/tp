package happybit.habit;

import happybit.interval.Interval;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Habit {

    private static final int START_DATE_INDEX = 0;
    private static final int END_DATE_INDEX = 1;
    private static final int NEXT_START_DATE_INDEX = 2;
    private static final int COMPLETION_RATE_INDEX = 0;
    private static final int COMPLETED_INDEX = 1;
    private static final int REMAINING_INDEX = 2;
    private static final int EXPIRED_INDEX = 3;
    private static final String FROM = " from ";

    protected String habitName;
    protected Date startDate;
    protected Date endDate;
    protected int intervalLength;
    protected ArrayList<Interval> intervals = new ArrayList<>();

    /**
     * Constructor for Habit class.
     *
     * @param habitName      Name of habit linked to a goal.
     * @param intervalLength Length of each interval for a habit.
     */
    public Habit(String habitName, int intervalLength) {
        this.habitName = habitName;
        this.startDate = getStartOfDay(new Date());
        this.intervalLength = intervalLength;
    }

    /**
     * Constructor for Habit class when importing from storage.
     * Date to keep track of last date.
     *
     * @param habitName      String parameter of habit name.
     * @param startDate      Start date of a habit.
     * @param endDate        End date of a habit.
     * @param intervalLength integer storing length of interval for habit.
     */
    public Habit(String habitName,  Date startDate, Date endDate, int intervalLength) {
        this.habitName = habitName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.intervalLength = intervalLength;
    }

    /**
     * Getter for habitName.
     *
     * @return String containing name of habit.
     */
    public String getHabitName() {
        return this.habitName;
    }

    /**
     * Getter for start date in string format (for storage).
     *
     * @return String of the start date.
     */
    public String getStartDate() {
        return dateToString(this.startDate);
    }

    /**
     * Getter for end date in string format (for storage).
     *
     * @return String of the end date.
     */
    public String getEndDate() {
        return dateToString(this.endDate);
    }

    /**
     * Get interval set by the user.
     *
     * @return Integer of interval set by user
     */
    public int getIntervalLength() {
        return this.intervalLength;
    }

    /**
     * Gets the number of intervals in the interval list.
     *
     * @return Total number of intervals.
     */
    public int getTotalIntervals() {
        return this.intervals.size();
    }

    public ArrayList<Interval> getIntervals() {
        return this.intervals;
    }

    /**
     * Updates the habit name with a new one from user.
     *
     * @param habitName New habit name.
     */
    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    /**
     * Setter for endDate of habit.
     * Used in AddHabitCommand to manually set the endDate since goalList is only accessible there.
     *
     * @param endDate End date of a goal.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setIntervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }

    public void addInterval(Interval interval) {
        this.intervals.add(interval);
    }

    /**
     * Fills the interval list with all intervals during habit creation.
     * Method is called in goalList after the endDate has been added.
     */
    public void populateIntervalsDuringHabitCreation() {
        populateIntervals(this.startDate);
    }

    /**
     * Linearly checks through interval list to find interval and set interval as completed.
     *
     * @param completionDate Date that an interval is completed on.
     */
    public void completeInterval(Date completionDate) {
        for (Interval interval : intervals) {
            if (interval.isWithinInterval(completionDate)) {
                interval.setCompleted(completionDate);
                break;
            }
        }
    }

    /**
     * Gets the required dates when an interval is marked as completed.
     * 1) Start date of current interval.
     * 2) End date of current interval.
     * 3) Start date of next interval.
     *
     * @param completionDate Date that an interval is completed.
     * @return String array containing the 3 dates mentioned in the description.
     */
    public String[] getDoneHabitDates(Date completionDate) {
        String[] strDates = new String[3];
        for (Interval interval : intervals) {
            if (interval.isWithinInterval(completionDate)) {
                strDates[START_DATE_INDEX] = dateToString(interval.getStartDate());
                strDates[END_DATE_INDEX] = dateToString(interval.getEndDate());
                strDates[NEXT_START_DATE_INDEX] = dateToString(getStartOfNextDay(interval.getEndDate()));
            }
        }
        return strDates;
    }

    /**
     * Updates the name of a habit.
     *
     * @param habitName New name the habit is to be updated with.
     */
    public void updateHabitName(String habitName) {
        this.habitName = habitName;
    }

    /**
     * Updates a new interval for the specified habit.
     *
     * @param lengthOfInterval Integer value of the new interval user wishes to have.
     */
    public void updateLengthOfInterval(int lengthOfInterval) {
        this.intervalLength = lengthOfInterval;
        updateIntervals();
    }

    /**
     * Gets the statistics of the habit's completion.
     * Statistics includes: expired, completed, remaining intervals, and completion rate.
     *
     * @return Statistics of habit's completion.
     */
    public int[] getListStatistics() {
        int[] statistics = new int[4];
        statistics[COMPLETION_RATE_INDEX] = computeHabitCompletionRate();
        statistics[COMPLETED_INDEX] = computeNumOfCompletedIntervals();
        statistics[REMAINING_INDEX] = computeNumOfRemainingIntervals();
        statistics[EXPIRED_INDEX] = computeNumOfExpiredIntervals();
        return statistics;
    }

    /**
     * Computes the completion rate of the habit.
     * Calculated by taking the number of completed intervals / total number of intervals.
     *
     * @return Completion rate of habit.
     */
    public int computeHabitCompletionRate() {
        int numOfCompletedIntervals = computeNumOfCompletedIntervals();
        int totalIntervals = getTotalIntervals();
        return numOfCompletedIntervals / totalIntervals;
    }

    /**
     * Returns details of the current interval if due but not completed.
     *
     * @return Details of habit interval.
     */
    public String getIntervalDescriptionIfDue() {
        int intervalIndex = getIndexOfCurrentInterval();
        Interval currInterval = intervals.get(intervalIndex);
        boolean isIntervalDone = currInterval.getDone();
        if (isIntervalDone) {
            return null;
        }
        return this.habitName + FROM + currInterval.getDescription();
    }

    /**
     * Returns longest consecutive streak for current habit.
     *
     * @return Longest chain streak for the habit
     */
    public int getStreak() {
        int streak = 0;
        int currStreak = 0;
        boolean isConsecutive = false;

        for (Interval interval : intervals) {
            if (interval.getDone()) {
                isConsecutive = true;
                currStreak++;
            } else {
                isConsecutive = false;
                if (currStreak >= streak) {
                    streak = currStreak;
                }
                currStreak = 0;
            }
        }

        return streak;
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /* The following sub-methods are used to populate the Checkpoint list.
     * populateCheckpoints()
     * populateCheckpointsIfNoInterval()
     * populateCheckpointsIfNonZeroInterval()
     * daysBetweenStartAndEndDates()
     * getNumOfCheckpoints()
     * addIntervalToDate()
     */

    /**
     * Populate the interval list with all the days.
     *
     * @param startDate Start date of the habit.
     */
    private void populateIntervals(Date startDate) {
        if (this.intervalLength == 0) {
            populateIntervalsIfZeroLengthInterval(startDate);
        } else {
            populateIntervalsIfNonZeroLengthInterval();
        }
    }

    /**
     * Adds a single interval to the list.
     *
     * @param startDate Start date of when there is a single interval.
     */
    private void populateIntervalsIfZeroLengthInterval(Date startDate) {
        Interval interval = new Interval(startDate, this.endDate);
        this.intervals.add(interval);
    }

    /**
     * Populates interval list with intervals assuming a non-zero interval.
     * The endDate for the last checkpoint is manually set in the scenario where the number of habit days
     * is not a multiple of the interval length (prevents endDate from overrunning habit days).
     */
    private void populateIntervalsIfNonZeroLengthInterval() {
        int numOfCheckpoints = getNumOfIntervals();
        Date assignedStartDate = this.startDate;
        Date assignedEndDate = getEndOfDay(addDaysToDate(assignedStartDate, this.intervalLength));
        for (int checkpoint = 0; checkpoint < numOfCheckpoints; checkpoint++) {
            Interval interval = new Interval(assignedStartDate, assignedEndDate);
            this.intervals.add(interval);
            assignedStartDate = addDaysToDate(assignedStartDate, this.intervalLength);
            assignedEndDate = addDaysToDate(assignedEndDate, this.intervalLength);
        }
        this.intervals.get(numOfCheckpoints - 1).setEndDate(this.endDate);
    }

    /**
     * Computes the number of days between the start date (inclusive) and end dates (inclusive).
     * If startDate = 01-Jan-2021 and endDate = 05-Jan-2021, then return value = 5.
     *
     * @return Number of days between start and end dates.
     */
    private int daysBetweenStartAndEndDates() {
        LocalDate startLocalDate = convertDateToLocalDate(this.startDate);
        LocalDate endLocalDate = convertDateToLocalDate(this.endDate);
        return (int) ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + 1;
    }

    /**
     * Obtains the total checkpoints possible given the interval and range of dates.
     * Addition of 1 is to account for the startDate being treated as the 1st checkpoint.
     *
     * @return Total number of checkpoints.
     */
    private int getNumOfIntervals() {
        int numberOfDays = daysBetweenStartAndEndDates();
        if (numberOfDays % this.intervalLength != 0) {
            return numberOfDays / this.intervalLength + 1;
        }
        return numberOfDays / this.intervalLength;
    }

    /**
     * Adds number of dates equal to the interval to the base date.
     *
     * @param baseDate Date to have days added to.
     * @param days     Number of days to be added to base date.
     * @return Date with days added.
     */
    private Date addDaysToDate(Date baseDate, int days) {
        LocalDate baseLocalDate = convertDateToLocalDate(baseDate);
        LocalDate nextLocalDate = baseLocalDate.plusDays(days);
        return convertLocalDateToDate(nextLocalDate);
    }

    /* The following are sub-methods used to update the Interval list following change in interval length.
     * updateIntervals()
     * getIndexOfCurrentInterval()
     * setEndDateOfCurrentInterval()
     * deleteFutureIntervals()
     */

    /**
     * Update the interval list following a change in interval length.
     */
    private void updateIntervals() {
        int currIntervalIndex = getIndexOfCurrentInterval();
        setEndDateOfCurrentInterval(currIntervalIndex);
        deleteFutureIntervals(currIntervalIndex);
        Date currDate = new Date();
        Date startOfNextDay = getStartOfNextDay(currDate);
        populateIntervals(startOfNextDay);
    }

    /**
     * Get the index of the interval corresponding to the current date.
     *
     * @return Index of current interval.
     */
    private int getIndexOfCurrentInterval() {
        Date currDate = new Date();
        for (int intervalIndex = 0; intervalIndex < getTotalIntervals(); intervalIndex++) {
            if (intervals.get(intervalIndex).isWithinInterval(currDate)) {
                return intervalIndex;
            }
        }
        assert false; // The current date should always be present in an interval.
        return -1;
    }

    /**
     * Updates endDate of current interval to the end of the current date.
     *
     * @param currIntervalIndex Index of the current interval.
     */
    private void setEndDateOfCurrentInterval(int currIntervalIndex) {
        Date currEndDate = getEndOfDay(new Date());
        intervals.get(currIntervalIndex).setEndDate(currEndDate);
    }

    /**
     * Delete all intervals after the current interval.
     *
     * @param currIntervalIndex Index of the current interval.
     */
    private void deleteFutureIntervals(int currIntervalIndex) {
        intervals.subList(currIntervalIndex + 1, getTotalIntervals()).clear();
    }

    /* The following are methods used to treat Date classes.
     * convertDateToLocalDate()
     * convertLocalDateToDate()
     * getStartOfDay()
     * getEndOfDay()
     */

    /**
     * Converts date into a string.
     *
     * @param date Date to be converted.
     * @return String format of a date.
     */
    private String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        return formatter.format(date);
    }

    /**
     * 'Type-casting' a Date to a LocalDate.
     *
     * @param date Date to be 'type-casted'.
     * @return LocalDate that has been 'type-casted' from Date.
     */
    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 'Type-casting' a LocalDate to a Date.
     *
     * @param localDate LocalDate to be 'type-casted'.
     * @return Date that has been 'type-casted' from LocalDate.
     */
    private Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Sets the hours, minute, seconds, milliseconds of a date to the start of the day.
     *
     * @param date Date to set to start of the day.
     * @return Date corresponding to start of the day.
     */
    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Sets the hours, minute, seconds, milliseconds of a date to the end of the day.
     *
     * @param date Date to set to end of the day.
     * @return Date corresponding to end of the day.
     */
    private Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Gets the start of the next day.
     *
     * @param date Date used to get the next day.
     * @return Date of the start of the next day.
     */
    private Date getStartOfNextDay(Date date) {
        Date currStartOfDay = getStartOfDay(date);
        return addDaysToDate(currStartOfDay, 1);
    }

    /* The following are methods used to calculate progress statistics.
     * computeNumOfExpiredIntervals()
     * computeNumOfCompletedIntervals()
     * computeNumOfRemainingIntervals()
     */

    /**
     * Computes the number of intervals that have expired (not completed within interval).
     * 1) Checks if the current date has passed the interval end date.
     * 2) Checks that the interval is still uncompleted.
     *
     * @return Number of expired intervals.
     */
    private int computeNumOfExpiredIntervals() {
        int count = 0;
        Date currDate = new Date();
        for (Interval interval : intervals) {
            if (currDate.compareTo(interval.getEndDate()) > 0 && !interval.getDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Computes the number of intervals that are completed (completed within interval).
     *
     * @return Number of completed intervals.
     */
    private int computeNumOfCompletedIntervals() {
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.getDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Computes the number of habits that are remaining (includes current interval if not done).
     *
     * @return Number of remaining intervals.
     */
    private int computeNumOfRemainingIntervals() {
        int count = 0;
        Date currDate = new Date();
        for (Interval interval : intervals) {
            if (interval.getEndDate().compareTo(currDate) > 0 && !interval.getDone()) {
                count++;
            }
        }
        return count;
    }

}
