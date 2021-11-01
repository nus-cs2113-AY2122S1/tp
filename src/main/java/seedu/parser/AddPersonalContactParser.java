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

    public void recallPersonalDetails() {
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        parsePersonalName(false);
        parsePersonalGithubUsername();
        parsePersonalTelegramUsername();
        parsePersonalTwitterUsername();
        parsePersonalEmailAddress();
        parsePersonalLinkedinUsername();
    }

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

    private boolean checkGithubValidity(String userInput) throws InvalidGithubUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkGithubUsernameRegex(userInput);
        return true;
    }

    private void setParsedGithubUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setGithub(null);
        } else {
            this.personalContact.setGithub(userInput);
        }
    }

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

    private boolean checkTelegramValidity(String userInput) throws InvalidTelegramUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkTelegramUsernameRegex(userInput);
        return true;
    }

    private void setParsedTelegramUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setTelegram(null);
        } else {
            this.personalContact.setTelegram(userInput);
        }
    }

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

    private boolean checkTwitterValidity(String userInput) throws InvalidTwitterUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkTwitterUsernameRegex(userInput);
        return true;
    }

    private void setParsedTwitterUsername(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setTwitter(null);
        } else {
            this.personalContact.setTwitter(userInput);
        }
    }

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

    private boolean checkEmailValidity(String userInput) throws InvalidEmailException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkEmailRegex(userInput);
        return true;
    }

    private void setParsedEmailAddress(String userInput) {
        if (userInput.isEmpty()) {
            this.personalContact.setEmail(null);
        } else {
            this.personalContact.setEmail(userInput);
        }
    }

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

    private boolean checkLinkedinValidity(String userInput) throws InvalidLinkedinUsernameException {
        if (userInput.isEmpty()) {
            return true;
        }
        // if exception is thrown in there, it will not return true, so isValidDetail will remain false
        checkLinkedinUsernameRegex(userInput);
        return true;
    }

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