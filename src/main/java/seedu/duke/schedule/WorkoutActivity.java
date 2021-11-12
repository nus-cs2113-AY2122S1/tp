package seedu.duke.schedule;

import java.util.ArrayList;

//@@author arvejw

/**
 * Represents a workout activity. Namely, a <code>WorkoutActivity</code> objects contains information of the
 * activity description and the quantifiers tied to it.
 */
public class WorkoutActivity {

    private String activityDescription;
    private boolean isDistanceActivity = false;
    private int activityDistance;
    private int activitySets;
    private int activityReps;
    public static final String ACTIVITY_SWIMMING = "swimming";
    public static final String ACTIVITY_RUNNING = "running";
    public static final String ACTIVITY_CYCLING = "cycling";

    /**
     * Constructs a <code>WorkoutActivity</code> object.
     *
     * @param activityDescription Description of the activity.
     * @param activityQuantifier Quantifier of the activity.
     * @param isDistanceActivity Flag to determine if the activity is distance based.
     */
    public WorkoutActivity(String activityDescription,
                           ArrayList<Integer> activityQuantifier, boolean isDistanceActivity) {
        this.activityDescription = activityDescription;
        if (isDistanceActivity) {
            activityDistance = activityQuantifier.get(0);
            this.isDistanceActivity = true;
        } else {
            activitySets = activityQuantifier.get(0);
            activityReps = activityQuantifier.get(1);
        }
    }

    /**
     * Helps check if the activity is one of the 3 pre-defined distance based activity swimming/running/cycling.
     *
     * @param activityDescription Description of the activity.
     * @return boolean <code>true</code> if the activity is distance based, <code>false</code> otherwise.
     */
    public static boolean isDistanceActivity(String activityDescription) {
        return activityDescription.trim().equals(ACTIVITY_SWIMMING)
                || activityDescription.trim().equals(ACTIVITY_RUNNING)
                || activityDescription.trim().equals(ACTIVITY_CYCLING);
    }

    public boolean isDistanceActivity() {
        return isDistanceActivity;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public int getActivityDistance() {
        return activityDistance;
    }

    public int getActivitySets() {
        return activitySets;
    }

    public int getActivityReps() {
        return activityReps;
    }
}
