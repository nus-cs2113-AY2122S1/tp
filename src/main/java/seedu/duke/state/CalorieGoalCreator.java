package seedu.duke.state;

import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.ui.Ui;

//@@author tttyyzzz

/**
 * Creates a CalorieGoal Class and prompts user for valid calorie goal input.
 */
public class CalorieGoalCreator extends AttributeCreator {


    private CalorieGoal calorieGoal;
    private static final String MESSAGE_CALORIE_GOAL = "You calorie goal is %s cal.";
    private static final String MESSAGE_INTRO_CALORIE_GOAL = "Please input your net calorie goal.";
    private static final String MESSAGE_INVALID_POSITIVE_INT_INPUT = "Invalid input, "
            + "please input a valid whole number from "
            + CalorieGoal.LIMIT_LOWER_CALORIES + " to "
            + CalorieGoal.LIMIT_UPPER_CALORIES;

    public CalorieGoalCreator(Ui ui) {
        super(ui);
    }

    /**
     * Creates a valid profile calorie goal for the profile instance.
     *
     * @throws MissingParamException if user input a string of 0 characters.
     */
    public CalorieGoal createNewCalorieGoal() throws MissingParamException {
        boolean checkInput = false;// check whether calorie goal has the correct input
        do {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_CALORIE_GOAL);
            try {
                String userInput = ui.getUserInput().trim();
                setCalorieGoal(userInput);
                checkCalorieGoal();
                checkInput = true;
            } catch (NumberFormatException e) {
                ui.formatMessageWithTopDivider(MESSAGE_INVALID_POSITIVE_INT_INPUT);
            }
        } while (!checkInput || !calorieGoal.isValid());
        return calorieGoal;
    }

    private void checkCalorieGoal() {
        if (calorieGoal.isValid()) {
            ui.formatMessageWithTopDivider(
                    String.format(MESSAGE_CALORIE_GOAL,
                            calorieGoal.getCalorieGoal()));
        } else {
            ui.formatMessageWithTopDivider(ProfileUtils.ERROR_CALORIE_GOAL);

        }
    }

    private void setCalorieGoal(String userInput) throws MissingParamException {
        ui.checkEmptyUserInput(userInput);
        confirmInputBye(userInput);
        int calorieGoalInput = (Double.parseDouble(userInput) > Integer.MAX_VALUE)
                ? Integer.MAX_VALUE : Integer.parseInt(userInput);
        calorieGoal = new CalorieGoal(calorieGoalInput);
    }
}
