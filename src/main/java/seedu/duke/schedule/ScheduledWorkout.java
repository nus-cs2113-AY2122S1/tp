package seedu.duke.schedule;

import seedu.duke.Parser;
import seedu.duke.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

//@@author arvejw

/**
 * Represents a scheduled workout. Namely, a <code>ScheduledWorkout</code> stores information about
 * the scheduled workout such as its description, date and time, as well as whether the workout is recurring.
 */
public class ScheduledWorkout {

    private String workoutDescription;
    private ArrayList<WorkoutActivity> activities;
    private String workoutDate;
    private String workoutTime;
    private LocalDateTime workoutDateTime;
    private boolean isRecurring;

    /**
     * Constructs a <code>ScheduledWorkout</code> object.
     *
     * @param workoutDescription The description of the workout.
     * @param workoutDate        The date of the workout.
     * @param workoutTime        The time of the workout.
     * @param activityMap        The activities of the workout.
     * @param isRecurring        The type of workout, namely whether its recurring or not.
     */
    public ScheduledWorkout(String workoutDescription, String workoutDate, String workoutTime,
                            Map<String, ArrayList<Integer>> activityMap, boolean isRecurring) {
        this.workoutDescription = workoutDescription;
        this.workoutDate = workoutDate;
        this.workoutTime = workoutTime;
        workoutDateTime = LocalDateTime.of(
                LocalDate.parse(workoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(workoutTime, DateTimeFormatter.ofPattern("HH:mm")));
        this.isRecurring = isRecurring;
        activities = new ArrayList<>();
        if (!activityMap.isEmpty()) {
            for (var entry : activityMap.entrySet()) {
                activities.add(
                        new WorkoutActivity(
                                entry.getKey().trim(), entry.getValue(),
                                WorkoutActivity.isDistanceActivity(entry.getKey())
                        )
                );
            }
        }
    }

    /**
     * Copies and constructs a <code>ScheduledWorkout</code> object with the same parameters.
     *
     * @param scheduledWorkout <code>ScheduledWorkout</code> object to be copied.
     */
    public ScheduledWorkout(ScheduledWorkout scheduledWorkout) {
        this.workoutDescription = scheduledWorkout.workoutDescription;
        this.activities = scheduledWorkout.activities;
        this.workoutDate = scheduledWorkout.workoutDate;
        this.workoutTime = scheduledWorkout.workoutTime;
        this.workoutDateTime = scheduledWorkout.workoutDateTime;
        this.isRecurring = scheduledWorkout.isRecurring;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public void incrementWorkoutDate(long days) {
        workoutDateTime = workoutDateTime.plusDays(days);
        workoutDate = workoutDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getWorkoutDateAsLocalDate() {
        return workoutDateTime.toLocalDate();
    }

    public String getWorkoutTime() {
        return workoutTime;
    }

    public LocalDateTime getWorkoutDateTime() {
        return workoutDateTime;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public String isRecurringStatus() {
        return isRecurring ? " [R]" : "";
    }

    public String isRecurringStatusAsText() {
        return isRecurring ? "recurring " : "";
    }

    /**
     * Returns the activities in a more readable String format to be printed.
     *
     * @param isListing Whether the activities are being printed by list commands.
     * @return String Activity breakdown as a single String to be printed.
     */
    public String getActivitiesAsStringToPrint(boolean isListing) {
        StringBuilder output = new StringBuilder();
        output.append(System.lineSeparator()).append("Activities Breakdown: ").append(System.lineSeparator());
        if (activities.isEmpty()) {
            output.append("nil").append(System.lineSeparator()).append(Ui.HORIZONTAL_BAR_SHORT);
            return output.toString();
        }
        int currentIndex = 1;
        for (WorkoutActivity a : activities) {
            if (a.isDistanceActivity()) {
                output.append(currentIndex).append(". ").append(a.getActivityDescription())
                        .append(": ").append(a.getActivityDistance()).append("metres");
            } else {
                output.append(currentIndex).append(". ").append(a.getActivityDescription())
                        .append(": ").append(a.getActivitySets()).append("sets x ").append(a.getActivityReps())
                        .append("reps");
            }
            if (currentIndex < activities.size()) {
                output.append(System.lineSeparator());
            }
            currentIndex++;
        }
        if (isListing) {
            output.append(System.lineSeparator()).append(Ui.HORIZONTAL_BAR_SHORT);
        }
        return output.toString();
    }

    /**
     * Returns the information of the ScheduledWorkout in a data file compatible format.
     *
     * @return String Scheduled workout information as a single String.
     */
    public String getScheduledWorkoutAsDataString() {
        StringBuilder output = new StringBuilder();
        output.append(workoutDescription).append(Parser.DATE_SEPARATOR).append(workoutDate)
                .append(Parser.TIME_SEPARATOR).append(workoutTime).append(getActivitiesAsDataString());
        if (isRecurring) {
            output.append(Parser.RECURRING_FLAG);
        }
        return output.toString();
    }

    /**
     * Returns the activity breakdown of the ScheduledWorkout in a data file compatible format.
     *
     * @return String Activity breakdown information as a single String to be stored.
     */
    public String getActivitiesAsDataString() {
        StringBuilder activityString = new StringBuilder();
        if (activities.isEmpty()) {
            return activityString.toString();
        }
        activityString.append(Parser.ACTIVITY_SEPARATOR);
        int currentIndex = 0;
        for (WorkoutActivity activity : activities) {
            if (activity.isDistanceActivity()) {
                activityString.append(activity.getActivityDescription())
                        .append(Parser.ACTIVITY_SPLITTER)
                        .append(activity.getActivityDistance());
            } else {
                activityString.append(activity.getActivityDescription())
                        .append(Parser.ACTIVITY_SPLITTER)
                        .append(activity.getActivitySets())
                        .append(Parser.QUANTIFIER_SPLITTER)
                        .append(activity.getActivityReps());
            }
            currentIndex++;
            activityString.append(
                    (currentIndex < activities.size()) ? Parser.MULTIPLE_ACTIVITY_MARKER : Parser.EMPTY_STRING);
        }
        return activityString.toString();
    }

    /**
     * Returns a boolean depending on whether an activity breakdown was specified.
     *
     * @return <code>true</code> if has activities, <code>false</code> otherwise.
     */
    public boolean hasActivities() {
        return !activities.isEmpty();
    }
}
