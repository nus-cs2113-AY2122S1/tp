package seedu.duke.logic.commands;

import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.data.profile.utilities.ProfileUtils;

/**
 * Represents the command that when executed, calculates the BMI of the current Profile.
 */
public class CalculateBmiWithProfileCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Your BMI value according to your current profile is:"
            + CommandMessages.INDENTED_LS + "%1$,.1f (%2$s)";
    public static final String MESSAGE_UNINITIALIZED_PROFILE = "Your profile has not been initialized yet.";


    @Override
    public CommandResult execute() {
        try {
            final double height = super.profile.getProfileHeight().getHeight();
            final double weight = super.profile.getProfileWeight().getWeight();
            final double bmi = ProfileUtils.calculateBmi(height, weight);
            return new CommandResult(String.format(MESSAGE_SUCCESS, bmi, ProfileUtils.retrieveBmiStatus(bmi)));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(MESSAGE_UNINITIALIZED_PROFILE);
        }
    }
}
