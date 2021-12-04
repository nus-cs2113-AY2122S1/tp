package seedu.duke.data.profile.utilities;

/**
 * Different levels of activity depending on the ActivityFactor in Profile.
 */
public enum ActivityLevel {

    DEFAULT(Constants.FACTOR_DEFAULT),
    SEDENTARY(Constants.FACTOR_SEDENTARY),
    LIGHT(Constants.FACTOR_LIGHT),
    MODERATE(Constants.FACTOR_MODERATE),
    INTENSE(Constants.FACTOR_INTENSE),
    EXTREME(Constants.FACTOR_EXTREME);

    private final double factor;

    ActivityLevel(double factor) {
        this.factor = factor;
    }

    /**
     * Returns the constant associated with the level of activity.
     *
     * @return the factor depending on level of activity
     */
    public double getFactor() {
        return factor;
    }

    private static class Constants {
        public static final double FACTOR_DEFAULT = 1.0;
        public static final double FACTOR_SEDENTARY = 1.2;
        public static final double FACTOR_LIGHT = 1.375;
        public static final double FACTOR_MODERATE = 1.55;
        public static final double FACTOR_INTENSE = 1.725;
        public static final double FACTOR_EXTREME = 1.9;
    }
}
