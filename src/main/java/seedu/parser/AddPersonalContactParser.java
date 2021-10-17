package seedu.parser;


import seedu.contact.Contact;
import seedu.exception.InvalidNameException;
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

    public AddPersonalContactParser() {
        TextUi.welcomeMessage();
        promptPersonalName();
        TextUi.greetingMessage(personalContact);
    }

    private void promptPersonalName() {
        boolean isValid = false;
        String personalName = "";
        do {
            try {
                personalName = UserInputTextUi.getUserInput();
                checkNameRegex(personalName);
                //checkNameRegex passes so the name must be valid
                isValid = true;
            } catch (InvalidNameException e) {
                ExceptionTextUi.invalidPersonalNameErrorMessage();
            }
        } while (!isValid);
        this.personalContact.setName(personalName);
    }

    public Contact getPersonalContact() {
        return personalContact;
    }
}