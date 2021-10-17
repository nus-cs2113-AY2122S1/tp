package seedu.parser;

import seedu.contact.Contact;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidEmailException;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

public class AddPersonalContactParser extends RegexParser {
    private Contact personalContact = new Contact(null, null,null,null,
            null,null);
    private boolean isValidDetail = false;

    public AddPersonalContactParser() {
        TextUi.welcomeMessage();
    }


    public void startCollectingPersonalDetails() {
        promptPersonalName();
        TextUi.greetingMessage(personalContact);
        String userConfirmation = UserInputTextUi.getUserConfirmation();
        if (userConfirmation != null || userConfirmation.isBlank()) {
            promptPersonalGithubUsername();
            promptPersonalTelegramUsername();
            promptPersonalTwitterUsername();
            promptPersonalEmailAddress();
            promptPersonalLinkedInUsername();
            TextUi.finishSetUpMessage();
        }
    }

    private void promptPersonalName() {
        isValidDetail = false;
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

    private void promptPersonalGithubUsername() {
        isValidDetail = false;
        TextUi.promptPersonalGithubUsernameMessage(this.personalContact.getName());
        do {
            try {
                String personalGithubUsername = UserInputTextUi.getUserInput();
                setGithubIfValid(personalGithubUsername);
            } catch (InvalidGithubUsernameException e) {
                ExceptionTextUi.invalidPersonalGithubUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    private void setGithubIfValid(String userInput) throws InvalidGithubUsernameException {
        if (userInput.isEmpty() || userInput == null) {
            isValidDetail = true;
        } else {
            checkGithubUsernameRegex(userInput);
            isValidDetail = true;
            this.personalContact.setGithub(userInput);
        }
    }

    private void promptPersonalTelegramUsername() {
        isValidDetail = false;
        TextUi.promptPersonalTelegramUsernameMessage();
        do {
            try {
                String personalTelegram = UserInputTextUi.getUserInput();
                setTelegramIfValid(personalTelegram);
            } catch (InvalidTelegramUsernameException e) {
                ExceptionTextUi.invalidPersonalTelegramUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    private void setTelegramIfValid(String userInput) throws InvalidTelegramUsernameException {
        if (userInput.isEmpty() || userInput == null) {
            isValidDetail = true;
        } else {
            checkTelegramUsernameRegex(userInput);
            isValidDetail = true;
            this.personalContact.setTelegram(userInput);
        }
    }

    private void promptPersonalTwitterUsername() {
        isValidDetail = false;
        TextUi.promptPersonalTwitterUsernameMessage();
        do {
            try {
                String personalTwitter = UserInputTextUi.getUserInput();
                setTwitterIfValid(personalTwitter);
            } catch (InvalidTwitterUsernameException e) {
                ExceptionTextUi.invalidPersonalTwitterUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    private void setTwitterIfValid(String userInput) throws InvalidTwitterUsernameException {
        if (userInput.isEmpty() || userInput == null) {
            isValidDetail = true;
        } else {
            checkTwitterUsernameRegex(userInput);
            isValidDetail = true;
            this.personalContact.setTwitter(userInput);
        }
    }

    private void promptPersonalEmailAddress() {
        isValidDetail = false;
        TextUi.promptPersonalEmailMessage();
        do {
            try {
                String personalEmail = UserInputTextUi.getUserInput();
                setEmailIfValid(personalEmail);
            } catch (InvalidEmailException e) {
                ExceptionTextUi.invalidPersonalEmailErrorMessage();
            }
        } while (!isValidDetail);
    }

    private void setEmailIfValid(String userInput) throws InvalidEmailException {
        if (userInput.isEmpty() || userInput == null) {
            isValidDetail = true;
        } else {
            checkEmailRegex(userInput);
            isValidDetail = true;
            this.personalContact.setEmail(userInput);
        }
    }

    private void promptPersonalLinkedInUsername() {
        isValidDetail = false;
        TextUi.promptPersonalLinkedInUsernameMessage();
        do {
            try {
                String personalLinkedIn = UserInputTextUi.getUserInput();
                setLinkedInIfValid(personalLinkedIn);
            } catch (InvalidLinkedinUsernameException e) {
                ExceptionTextUi.invalidPersonalLinkedinUsernameErrorMessage();
            }
        } while (!isValidDetail);
    }

    private void setLinkedInIfValid(String userInput) throws InvalidLinkedinUsernameException {
        if (userInput.isEmpty() || userInput == null) {
            isValidDetail = true;
        } else {
            checkLinkedinUsernameRegex(userInput);
            isValidDetail = true;
            this.personalContact.setLinkedin(userInput);
        }
    }

    public Contact getPersonalContact() {
        return personalContact;
    }
}