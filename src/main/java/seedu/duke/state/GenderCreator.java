package seedu.duke.state;

import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.ui.Ui;

/**
 * Creates a Gender Class and prompts user for valid gender input.
 */
public class GenderCreator extends AttributeCreator {

    private Gender gender = new Gender();
    public static final String MESSAGE_INTRO_GENDER = "What is your gender? (If you are a male, type 'm'"
            + ", if you are a female, type 'f')";
    public static final char MALE_CHAR = 'M';
    public static final String MESSAGE_MALE = "You are a male.";
    public static final String MESSAGE_FEMALE = "You are a female.";

    public GenderCreator(Ui ui) {
        super(ui);
    }

    /**
     * Creates a valid profile gender for the profile instance.
     *
     * @throws MissingParamException if user input a string of 0 characters.
     */
    public Gender createNewGender() throws MissingParamException {
        while (!gender.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_GENDER);
            String userInput = ui.getUserInput();
            setGender(userInput);
            checkGender();
        }
        return gender;
    }

    private void checkGender() {
        if (gender.isValid()) {
            ui.formatMessageWithTopDivider(
                    getGenderMessage(gender.getGender()));
        } else {
            ui.formatMessageWithTopDivider(ProfileUtils.ERROR_GENDER);
        }
    }

    private void setGender(String userInput) throws MissingParamException {
        ui.checkEmptyUserInput(userInput);
        confirmInputBye(userInput);
        if (userInput.length() == 1) {
            char genderInput = userInput.charAt(0);
            gender.setGender(genderInput);
        }
    }

    private String getGenderMessage(char gender) {
        return gender == MALE_CHAR ? MESSAGE_MALE : MESSAGE_FEMALE;
    }
}
