package seedu.duke.logic.commands;

import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.data.profile.utilities.ProfileUtils;

/**
 * Represents the command that when executed, calculates the BMI with the given height and weight data.
 */
public class CalculateBmiCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_BMI
            + " " + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Z"
            + CommandMessages.QUOTATION + " where X is the height in CM and Y is the weight in KG.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to calculate BMI for a certain height and weight? Use this format:"
            + CommandMessages.INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "The calculated BMI value is %1$,.1f (%2$s)";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_HEIGHT,
            COMMAND_PREFIX_WEIGHT
    };

    private final Height height;
    private final Weight weight;

    public CalculateBmiCommand(double height, double weight) {
        this.height = new Height(height);
        this.weight = new Weight(weight);
    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!this.weight.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_WEIGHT);
        }
        if (!this.height.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_HEIGHT);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            checkIfCommandShouldExecute();
            final double bmi = ProfileUtils.calculateBmi(this.height.getHeight(), this.weight.getWeight());
            return new CommandResult(String.format(MESSAGE_SUCCESS, bmi, ProfileUtils.retrieveBmiStatus(bmi)));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }

    }
}
