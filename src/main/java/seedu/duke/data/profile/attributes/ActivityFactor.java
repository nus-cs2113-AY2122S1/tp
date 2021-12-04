package seedu.duke.data.profile.attributes;

import seedu.duke.data.Verifiable;
import seedu.duke.data.profile.utilities.ActivityLevel;

/**
 * Activity Factor attribute of profile.
 */
public class ActivityFactor implements Verifiable {

    public static final int LIMIT_LOWER_ACTIVITY_FACTOR = 1;
    public static final int LIMIT_UPPER_ACTIVITY_FACTOR = 5;

    protected int userInput;

    public ActivityFactor() {

    }

    /**
     * Constructs an activity factor object.
     *
     * @param activityFactor activity factor input by user
     */
    public ActivityFactor(int activityFactor) {
        setUserInput(activityFactor);
    }

    /**
     * Obtains a ActivityLevel object depending on the activity factor.
     *
     * @return ActivityLevel along with its associated factor.
     */
    public ActivityLevel getActivityLevel() {
        switch (userInput) {
        case 1:
            return ActivityLevel.SEDENTARY;
        case 2:
            return ActivityLevel.LIGHT;
        case 3:
            return ActivityLevel.MODERATE;
        case 4:
            return ActivityLevel.INTENSE;
        case 5:
            return ActivityLevel.EXTREME;
        default:
            return ActivityLevel.DEFAULT;
        }
    }

    /**
     * Retrieves the activity factor of ActivityFactorobject.
     *
     * @return the activity factor of ActivityFactor object
     */
    public int getUserInput() {
        return userInput;
    }

    /**
     * Sets the activity factor of ActivityFactor object.
     *
     * @param userInput activity factor input by user
     */
    public void setUserInput(int userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean isValid() {
        return userInput >= LIMIT_LOWER_ACTIVITY_FACTOR && userInput <= LIMIT_UPPER_ACTIVITY_FACTOR;
    }
}
