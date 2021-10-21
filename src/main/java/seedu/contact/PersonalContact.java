package seedu.contact;

import seedu.exception.InvalidGithubUsernameException;

//should be interface?
public class PersonalContact extends Contact {
    public PersonalContact(String name, String github, String linkedin, String telegram, String twitter, String email) {
        super(name, github, linkedin, telegram, twitter, email);
    }

    private boolean setGithubIfValid(String userInput, Contact personalContact) throws InvalidGithubUsernameException {
        if (userInput.isEmpty() || userInput == null) {
            return true;
        } else {
            checkGithubUsernameRegex(userInput);
            this.setGithub(userInput);
            return true;
        }
    }

}
