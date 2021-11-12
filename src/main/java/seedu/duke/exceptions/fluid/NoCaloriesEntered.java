package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when user tries to add a fluid entry without providing its calorie intake.
 *
 * @author pragyan01
 */
public class NoCaloriesEntered extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter calories.";
    }
}
