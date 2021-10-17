package seedu.parser;


import seedu.command.ExitCommand;
import seedu.command.FailedCommand;
import seedu.command.HelpCommand;
import seedu.command.ListContactsCommand;
import seedu.contact.Contact;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.ui.ExceptionTextUi;
import seedu.ui.TextUi;
import seedu.ui.UserInputTextUi;

public class AddPersonalContactParser extends RegexParser {
    public static final String NAME_FLAG = "n";
    public static final String GITHUB_FLAG = "g";
    public static final String TELEGRAM_FLAG = "te";
    public static final String TWITTER_FLAG = "tw";
    public static final String EMAIL_FLAG = "e";
    public static final String LINKEDIN_FLAG = "l";

    private Contact personalContact = new Contact(null, null,null,null,
            null,null);
    private boolean isValidDetail = false;

    public AddPersonalContactParser() {
        TextUi.welcomeMessage();
        promptPersonalName();
        promptPersonalGithubUsername();
        promptPersonalTelegramUsername();
        promptPersonalTwitterUsername();
        TextUi.greetingMessage(personalContact);
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

    public void setGithubIfValid(String userInput) throws InvalidGithubUsernameException {
        if (userInput.isEmpty()) {
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

    public void setTelegramIfValid(String userInput) throws InvalidTelegramUsernameException {
        if (userInput.isEmpty()) {
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

    public void setTwitterIfValid(String userInput) throws InvalidTwitterUsernameException {
        if (userInput.isEmpty()) {
            isValidDetail = true;
        } else {
            checkTwitterUsernameRegex(userInput);
            isValidDetail = true;
            this.personalContact.setTwitter(userInput);
        }
    }


//    private void setDetailsIfValid(String userInput, String flag) throws InvalidGithubUsernameException,
//            InvalidTelegramUsernameException {
//        if (userInput.isEmpty()) {
//            isValidDetail = true;
//            return;
//        }
//        switch (flag) {
//        case GITHUB_FLAG:
//            checkGithubUsernameRegex(userInput);
//            isValidDetail = true;
//            this.personalContact.setGithub(userInput);
//            break;
//        case TELEGRAM_FLAG:
//            checkTelegramUsernameRegex(userInput);
//            isValidDetail = true;
//            this.personalContact.setTelegram(userInput);
//            break;
//        default:
//            return;
//        }
//    }

    public Contact getPersonalContact() {
        return personalContact;
    }
}