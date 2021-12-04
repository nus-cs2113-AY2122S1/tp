package seedu.duke.state;

import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.MissingParamException;

import seedu.duke.ui.Ui;

/**
 * Creates a Name Class and prompts user for valid name input.
 */
public class NameCreator extends AttributeCreator {

    private static final String LS = System.lineSeparator();
    private static final String MESSAGE_BYE_DETECTED = "The command word 'bye' is detected." + LS
            + "Type '1' if you wish to exit. Type '2' if you wish to set your name as 'bye'." + LS
            + "Else, type in any key for Fitbot to ask for your name again.";
    private static final String CHECK_REPEAT_MESSAGE = "";
    public static final String FIRST_OPTION = "1";
    public static final String SECOND_OPTION = "2";
    private Name name = new Name();
    private static final String MESSAGE_INTRO_NAME = "What's your name?";
    private static final String MESSAGE_NAME = "Nice name you have there! Hello %s.";

    public NameCreator(Ui ui) {
        super(ui);
    }

    /**
     * Creates a valid profile name for the profile instance.
     *
     * @throws MissingParamException if user input a string of 0 characters.
     */
    public Name createNewName() throws MissingParamException {
        while (!name.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_NAME);
            String userInput = ui.getUserInput().trim();
            if (setName(userInput)) {
                continue;
            }
            checkName();
        }
        assert name.isValid() : " name is valid";
        return name;
    }

    private void checkName() {
        if (name.isValid()) {
            ui.formatMessageWithTopDivider(
                    String.format(MESSAGE_NAME,
                            name.getName()));
        } else {
            ui.formatMessageWithTopDivider(ProfileUtils.ERROR_NAME);
        }
    }

    private boolean setName(String userInput) throws MissingParamException {
        ui.checkEmptyUserInput(userInput);
        userInput = checkAndConfirmInputBye(userInput);
        if (userInput.equals(CHECK_REPEAT_MESSAGE)) {
            ui.formatMessageWithTopDivider();
            return true;
        }
        name.setName(userInput);
        return false;
    }

    private String checkAndConfirmInputBye(String userInput) {
        boolean isBye = isBye(userInput);
        if (!isBye) {
            return userInput;
        }
        ui.formatMessageFramedWithDivider(String.format(MESSAGE_BYE_DETECTED, userInput));
        String userConfirmByeInput = ui.getUserInput().trim();
        if (userConfirmByeInput.equals(FIRST_OPTION)) {
            exit();
        } else if (userConfirmByeInput.equals(SECOND_OPTION)) {
            return userInput;
        }
        assert !userConfirmByeInput.equals("1") && !userConfirmByeInput.equals("2") : "other inputs";
        return CHECK_REPEAT_MESSAGE;
    }

}
