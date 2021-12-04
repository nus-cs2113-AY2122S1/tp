package seedu.duke.data.profile.utilities;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;

/**
 * Utilities that can be used for the Profile class.
 */
public class ProfileUtils {

    private static final String LS = System.lineSeparator();

    public static final int NON_POSITIVE_LIMIT = 0;

    private static final String BMI_STATUS_UNDERWEIGHT = "Underweight";
    private static final String BMI_STATUS_HEALTHY = "Healthy";
    private static final String BMI_STATUS_OVERWEIGHT = "Overweight";
    private static final String BMI_STATUS_OBESE = "Obese";
    public static final double BMI_LIMIT_UNDERWEIGHT = 18.5;
    public static final double BMI_LIMIT_HEALTHY = 24.9;
    public static final double BMI_LIMIT_OVERWEIGHT = 29.9;

    private static final char GENDER_M = 'M';
    private static final char GENDER_F = 'F';

    public static final double GENDER_M_WEIGHT_FACTOR = 13.397;
    public static final double GENDER_M_HEIGHT_FACTOR = 4.799;
    public static final double GENDER_M_AGE_FACTOR = 5.677;
    public static final double GENDER_M_CONSTANT = 88.362;
    public static final double GENDER_F_WEIGHT_FACTOR = 9.247;
    public static final double GENDER_F_HEIGHT_FACTOR = 3.098;
    public static final double GENDER_F_AGE_FACTOR = 4.330;
    public static final double GENDER_F_CONSTANT = 447.593;

    public static final String ERROR_NAME = "Try not to use / and | in your name as it can make our commands invalid.\n"
            + "Maybe you can replace them with \\ or - and try again!";
    public static final String ERROR_HEIGHT = "Your height cannot be of this value!" + LS
            + "Maybe you can try a number from " + Height.LOWER_HEIGHT_LIMIT + " to " + Height.UPPER_HEIGHT_LIMIT + ".";
    public static final String ERROR_WEIGHT = "Your weight cannot be of this value!" + LS
            + "Maybe you can try a number from " + Weight.LOWER_WEIGHT_LIMIT + " to " + Weight.UPPER_WEIGHT_LIMIT + ".";
    public static final String ERROR_GENDER = "Please type in M or F only!";
    public static final String ERROR_AGE = "Your age cannot be this value!" + LS
            + "Maybe you can try a whole number from " + Age.LOWER_AGE_LIMIT + " to " + Age.UPPER_AGE_LIMIT + ".";
    public static final String ERROR_ACTIVITY_FACTOR = "Your activity factor cannot be of this value!" + LS
            + "Maybe you can try a whole number from "
            + ActivityFactor.LIMIT_LOWER_ACTIVITY_FACTOR
            + " to "
            + ActivityFactor.LIMIT_UPPER_ACTIVITY_FACTOR + ".";
    public static final String ERROR_CALORIE_GOAL =
            "Your calorie goal cannot be of this value!" + LS
                    + "Maybe you can try a whole number from "
                    + CalorieGoal.LIMIT_LOWER_CALORIES
                    + " to "
                    + CalorieGoal.LIMIT_UPPER_CALORIES + ".";

    /**
     * Retrieves the Basal Metabolic Rate of the user based on their activity factor indicated on the profile.
     * A higher activity factor indicates a greater metabolic rate and thus more calories they burn off.
     *
     * @return BMR value based on their indicated activity factor
     */
    public static int getBmr(Profile profile) {
        double bmr = getBaseBmrValue(profile);
        double factor = profile.getProfileActivityFactor().getActivityLevel().getFactor();
        return (int) Math.round(bmr * factor);
    }

    /**
     * Manually calculates the BMI.
     * Used when the user input values that is not be stored in his/her profile.
     *
     * @param height Value of manual input of height
     * @param weight Value of manual input of weight
     * @return The calculated BMI of the manual inputs
     * @throws InvalidCharacteristicException When the user inputs negative values for either height or weight
     */
    public static double calculateBmi(double height, double weight) throws InvalidCharacteristicException {
        checkWeightValidity(weight);
        checkHeightValidity(height);
        assert weight > 0 : "Weight cannot be non-positive.";
        assert height > 0 : "Height cannot be non-positive.";
        return computeBmi(height, weight);
    }

    private static double computeBmi(double height, double weight) {
        double heightInM = height / 100.0;
        return Math.round((weight / (Math.pow(heightInM, 2))) * 10) / 10.0;
    }

    private static void checkWeightValidity(double weight) throws InvalidCharacteristicException {
        if (weight <= NON_POSITIVE_LIMIT) {
            throw new InvalidCharacteristicException(ERROR_WEIGHT);
        }
    }

    private static void checkHeightValidity(double height) throws InvalidCharacteristicException {
        if (height <= NON_POSITIVE_LIMIT) {
            throw new InvalidCharacteristicException(ERROR_HEIGHT);
        }
    }

    /**
     * Retrieves the indication with regard to the value of their BMI.
     * Should not have any exceptions thrown since the other functions handled invalid cases.
     *
     * @param bmi The bmi of the user
     * @return The status of his current body
     **/
    public static String retrieveBmiStatus(double bmi) {
        String result;
        assert bmi > 0 : "BMI cannot be non-positive.";
        if (bmi < BMI_LIMIT_UNDERWEIGHT) {
            result = BMI_STATUS_UNDERWEIGHT;
        } else if (bmi <= BMI_LIMIT_HEALTHY) {
            result = BMI_STATUS_HEALTHY;
        } else if (bmi <= BMI_LIMIT_OVERWEIGHT) {
            result = BMI_STATUS_OVERWEIGHT;
        } else {
            result = BMI_STATUS_OBESE;
        }
        return result;
    }

    private static double getBaseBmrValue(Profile profile) {
        char gender = profile.getProfileGender().getGender();
        double weight = profile.getProfileWeight().getWeight();
        double height = profile.getProfileHeight().getHeight();
        int age = profile.getProfileAge().getAge();
        double bmr;
        if (gender == GENDER_M) {
            bmr = GENDER_M_WEIGHT_FACTOR * weight
                    + GENDER_M_HEIGHT_FACTOR * height
                    - GENDER_M_AGE_FACTOR * age
                    + GENDER_M_CONSTANT;
        } else {
            bmr = GENDER_F_WEIGHT_FACTOR * weight
                    + GENDER_F_HEIGHT_FACTOR * height
                    - GENDER_F_AGE_FACTOR * age
                    + GENDER_F_CONSTANT;
        }
        return bmr;
    }

    /**
     * Calculates the difference between food calories and exercise calories, factoring in metabolic rate.
     *
     * @param foodCalories     Total intake consumption
     * @param exerciseCalories Total output exerted
     * @return The net calories of food - (exercise + BMR)
     * @throws InvalidCharacteristicException Only if activity factor has been misappropriated in .txt file
     */
    public static int calculateNetCalories(int foodCalories, int exerciseCalories, Profile p)
            throws InvalidCharacteristicException {
        return foodCalories - exerciseCalories - getBmr(p);
    }

}
