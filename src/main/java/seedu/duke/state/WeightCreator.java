package seedu.duke.state;


import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.ui.Ui;

/**
 * Creates a Weight Class and prompts user for valid weight input.
 */
public class WeightCreator extends AttributeCreator {

    public static final String MESSAGE_INTRO_WEIGHT = "What's your weight? (in kg)";
    public static final String MESSAGE_WEIGHT = "Your weight is %skg.";
    public static final String MESSAGE_INVALID_WEIGHT_INPUT = "Invalid input, please input a valid positive number";
    private Weight weight = new Weight();

    public WeightCreator(Ui ui) {
        super(ui);
    }

    /**
     * Creates a valid profile weight for the profile instance.
     *
     * @throws MissingParamException if user input a string of 0 characters.
     */
    public Weight createNewWeight() throws MissingParamException {
        while (!weight.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_WEIGHT);
            try {
                String userInput = ui.getUserInput();
                setWeight(userInput);
                checkWeight();
            } catch (NumberFormatException e) {
                ui.formatMessageWithTopDivider(MESSAGE_INVALID_WEIGHT_INPUT);
            }
        }
        return weight;
    }

    private void checkWeight() {
        if (weight.isValid()) {
            ui.formatMessageWithTopDivider(
                    String.format(MESSAGE_WEIGHT,
                            weight.getWeight()));
        } else {
            ui.formatMessageFramedWithDivider(ProfileUtils.ERROR_WEIGHT);
        }
    }

    private void setWeight(String userInput) throws MissingParamException {
        ui.checkEmptyUserInput(userInput);
        confirmInputBye(userInput);
        double weightInput = Double.parseDouble(userInput);
        weight.setWeight(weightInput);
    }
}
