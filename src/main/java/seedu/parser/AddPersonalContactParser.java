//@@author lezongmun

package seedu.parser;

import seedu.contact.Contact;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

public class AddPersonalContactParser extends RegexParser {
    private Contact personalContact;

    public AddPersonalContactParser() {
        personalContact = new Contact(null, null, null, null, null, null);
    }

    /**
     * This method is used to start the process of welcoming the user
     * and prompting them for their contact details when they first
     * use ConTech app.
     */
    public void collectPersonalDetails() {
        TextUi.welcomeMessage();
        parsePersonalName(true);
        TextUi.greetingMessage(personalContact);
        UserInputTextUi.getUserConfirmation();
        parsePersonalGithubUsername();
        parsePersonalTelegramUsername();
        parsePersonalTwitterUsername();
        parsePersonalEmailAddress();
        parsePersonalLinkedinUsername();
        TextUi.finishSetUpMessage();
    }

    /**
     * This method is used to restart the process of collecting the
     * user's contact details if any of the data in the personal contact
     * file becomes invalid or lost, and cannot be loaded.
     */
    public void recallPersonalDetails() {
        UserInputTextUi.getUserConfirmation();
        parsePersonalName(false);
        parsePersonalGithubUsername();
        parsePersonalTelegramUsername();
        parsePersonalTwitterUsername();
        parsePersonalEmailAddress();
        parsePersonalLinkedinUsername();
    }

    /**
     * This method is used to obtain the user's name as an input, and then
     * parses it as the personalContact's name field. It checks if the name given
     * is in a valid format, and if it is not, this method will continue
     * to prompt the user until a valid name is given and parses it.
     *
     * @param isFirstTime boolean to check if it is the user's first time
     *                    running the application
     */
    private void parsePersonalName(boolean isFirstTime) {
        boolean isValidDetail = false;
        TextUi.promptPersonalNameMessage(isFirstTime);
        do {
            try {
                String personalName = UserInputTextUi.getUserInput();
                checkNameRegex(personalName);
                //checkNameRegex passes so the name must be valid
                isValidDetail = true;
                this.personalContact.setName(personalName);
            } catch (InvalidNameException e) {
                ExceptionTextUi.invalidPersonalNameErrorMessage();
            }
        } while (!isValidDetail);
    }

    /**
     * This method is used to obtain the user's Github username as an input,
     * and then parses it as the personalContact's Github username field. It checks
     * if the username given is in a valid format, and if it is not, this method
     * will continue to prompt the user, until a valid username is given and
     * parses it.
     */
    private void parsePersonalGithubUsername() {
        boolean isValidDetail = false;
        TextUi.promptPersonalGithubUsernameMessage();
        do {
            try {
                String personalGithubUsername = UserInputTextUi.getUserInput();
                isValidDetail = checkGithubValidity(personalGithubUsername);
                setParsedGithubUsername(personalGithubUsername);
            } catch (InvalidGithubUsernameException e) {
                ExceptionTextUi.invalidPersonalGithubUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    /**
     * This method is used to check the validity of the Github username given
     * by the user. Inputs that are blank or adhere to the Github username
     * restrictions are allowed.
     *
     * @param userInput String that user has input
     * @return boolean to determine if Github username is valid
     * @throws InvalidGithubUsernameException If input does not adhere to the Github username restrictions
     */
    private boolean checkGithubValidity(String userInput) throws InvalidGithubUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkGithubUsernameRegex(userInput);
        return true;
    }

    /**
     * This method is used to set personalContact Github username field. NULL
     * is set for blank inputs.
     *
     * @param userInput String that user has input
     */
    private void setParsedGithubUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setGithub(null);
        } else {
            this.personalContact.setGithub(userInput);
        }
    }

    /**
     * This method is used to obtain the user's Telegram username as an input,
     * and then parses it as the personalContact's Telegram username field. It checks
     * if the username given is in a valid format, and if it is not, this method
     * will continue to prompt the user, until a valid username is given and
     * parses it.
     */
    private void parsePersonalTelegramUsername() {
        boolean isValidDetail = false;
        TextUi.promptPersonalTelegramUsernameMessage();
        do {
            try {
                String personalTelegramUsername = UserInputTextUi.getUserInput();
                isValidDetail = checkTelegramValidity(personalTelegramUsername);
                setParsedTelegramUsername(personalTelegramUsername);
            } catch (InvalidTelegramUsernameException e) {
                ExceptionTextUi.invalidPersonalTelegramUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    /**
     * This method is used to check the validity of the Telegram username given
     * by the user. Inputs that are blank or adhere to the Telegram username
     * restrictions are allowed.
     *
     * @param userInput String that user has input
     * @return boolean to determine if Telegram username is valid
     * @throws InvalidTelegramUsernameException If input does not adhere to the Telegram username restrictions
     */
    private boolean checkTelegramValidity(String userInput) throws InvalidTelegramUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkTelegramUsernameRegex(userInput);
        return true;
    }

    /**
     * This method is used to set personalContact Telegram username field. NULL
     * is set for blank inputs.
     *
     * @param userInput String that user has input
     */
    private void setParsedTelegramUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setTelegram(null);
        } else {
            this.personalContact.setTelegram(userInput);
        }
    }

    /**
     * This method is used to obtain the user's Twitter username as an input,
     * and then parses it as the personalContact's Twitter username field. It checks
     * if the username given is in a valid format, and if it is not, this method
     * will continue to prompt the user, until a valid username is given and
     * parses it.
     */
    private void parsePersonalTwitterUsername() {
        boolean isValidDetail = false;
        TextUi.promptPersonalTwitterUsernameMessage();
        do {
            try {
                String personalTwitterUsername = UserInputTextUi.getUserInput();
                isValidDetail = checkTwitterValidity(personalTwitterUsername);
                setParsedTwitterUsername(personalTwitterUsername);
            } catch (InvalidTwitterUsernameException e) {
                ExceptionTextUi.invalidPersonalTwitterUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    /**
     * This method is used to check the validity of the Twitter username given
     * by the user. Inputs that are blank or adhere to the Twitter username
     * restrictions are allowed.
     *
     * @param userInput String that user has input
     * @return boolean to determine if Twitter username is valid
     * @throws InvalidTwitterUsernameException If input does not adhere to the Twitter username restrictions
     */
    private boolean checkTwitterValidity(String userInput) throws InvalidTwitterUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkTwitterUsernameRegex(userInput);
        return true;
    }

    /**
     * This method is used to set personalContact Twitter username field. NULL
     * is set for blank inputs.
     *
     * @param userInput String that user has input
     */
    private void setParsedTwitterUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setTwitter(null);
        } else {
            this.personalContact.setTwitter(userInput);
        }
    }

    /**
     * This method is used to obtain the user's email address as an input,
     * and then parses it as the personalContact's email address field. It checks
     * if the email address given is in a valid format, and if it is not,
     * this method will continue to prompt the user, until a valid email address
     * is given and parses it.
     */
    private void parsePersonalEmailAddress() {
        boolean isValidDetail = false;
        TextUi.promptPersonalEmailMessage();
        do {
            try {
                String personalEmailAddress = UserInputTextUi.getUserInput();
                isValidDetail = checkEmailValidity(personalEmailAddress);
                setParsedEmailAddress(personalEmailAddress);
            } catch (InvalidEmailException e) {
                ExceptionTextUi.invalidPersonalEmailErrorMessage();
            }
        } while (!isValidDetail);
    }

    /**
     * This method is used to check the validity of the email address given
     * by the user. Inputs that are blank or adhere to the email address
     * restrictions are allowed.
     *
     * @param userInput String that user has input
     * @return boolean to determine if email address is valid
     * @throws InvalidEmailException If input does not adhere to the email address restrictions
     */
    private boolean checkEmailValidity(String userInput) throws InvalidEmailException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkEmailRegex(userInput);
        return true;
    }

    /**
     * This method is used to set personalContact email address field. NULL
     * is set for blank inputs.
     *
     * @param userInput String that user has input
     */
    private void setParsedEmailAddress(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setEmail(null);
        } else {
            this.personalContact.setEmail(userInput);
        }
    }

    /**
     * This method is used to obtain the user's LinkedIn username as an input,
     * and then parses it as the personalContact's LinkedIn username field. It checks
     * if the username given is in a valid format, and if it is not, this method
     * will continue to prompt the user, until a valid username is given and
     * parses it.
     */
    private void parsePersonalLinkedinUsername() {
        boolean isValidDetail = false;
        TextUi.promptPersonalLinkedinUsernameMessage();
        do {
            try {
                String personalLinkedinUsername = UserInputTextUi.getUserInput();
                isValidDetail = checkLinkedinValidity(personalLinkedinUsername);
                setParsedLinkedinUsername(personalLinkedinUsername);
            } catch (InvalidLinkedinUsernameException e) {
                ExceptionTextUi.invalidPersonalLinkedinUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    /**
     * This method is used to check the validity of the LinkedIn username given
     * by the user. Inputs that are blank or adhere to the LinkedIn username
     * restrictions are allowed.
     *
     * @param userInput String that user has input
     * @return boolean to determine if LinkedIn username is valid
     * @throws InvalidEmailException If input does not adhere to the LinkedIn username restrictions
     */
    private boolean checkLinkedinValidity(String userInput) throws InvalidLinkedinUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkLinkedinUsernameRegex(userInput);
        return true;
    }

    /**
     * This method is used to set personalContact LinkedIn username field. NULL
     * is set for blank inputs.
     *
     * @param userInput String that user has input
     */
    private void setParsedLinkedinUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setLinkedin(null);
        } else {
            this.personalContact.setLinkedin(userInput);
        }
    }

    public Contact getPersonalContact() {
        return personalContact;
    }
}