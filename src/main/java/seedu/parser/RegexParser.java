//@@author mayankp291

package seedu.parser;

import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RegexParser {
    public static final String NAME_FLAG = "n";
    public static final String GITHUB_FLAG = "g";
    public static final String LINKEDIN_FLAG = "l";
    public static final String TELEGRAM_FLAG = "te";
    public static final String TWITTER_FLAG = "tw";
    public static final String EMAIL_FLAG = "e";

    protected static final String[] FLAG_SEQUENCE = {NAME_FLAG, GITHUB_FLAG, LINKEDIN_FLAG,
        TELEGRAM_FLAG, TWITTER_FLAG, EMAIL_FLAG};
    private static final Logger LOGGER = Logger.getLogger(RegexParser.class.getName());

    /**
     * Throws an exception if the field does not comply with the regex.
     * If the field passes the regex check nothing is returned and program
     * flow continues.
     * The flag provided is checked in a switch case to invoke the respective
     * function to check the regex of the field.
     *
     * @param flag          field type
     * @param detailToParse field contents
     * @throws InvalidGithubUsernameException   If GitHub username does not match regex
     * @throws InvalidNameException             If Name does not match regex
     * @throws InvalidFlagException             If flag is invalid
     * @throws InvalidTelegramUsernameException If Telegram username does not match regex
     * @throws InvalidTwitterUsernameException  If Twitter username does not match regex
     * @throws InvalidLinkedinUsernameException If LinkedIn username does not match regex
     * @throws InvalidEmailException            If Email does not match regex
     */
    public void checkRegex(String flag, String detailToParse)
            throws InvalidGithubUsernameException, InvalidNameException, InvalidFlagException,
            InvalidTelegramUsernameException, InvalidTwitterUsernameException,
            InvalidLinkedinUsernameException, InvalidEmailException {
        assert flag != null;
        assert detailToParse != null;
        switch (flag) {
        case NAME_FLAG:
            checkNameRegex(detailToParse);
            break;
        case GITHUB_FLAG:
            checkGithubUsernameRegex(detailToParse);
            break;
        case TELEGRAM_FLAG:
            checkTelegramUsernameRegex(detailToParse);
            break;
        case TWITTER_FLAG:
            checkTwitterUsernameRegex(detailToParse);
            break;
        case LINKEDIN_FLAG:
            checkLinkedinUsernameRegex(detailToParse);
            break;
        case EMAIL_FLAG:
            checkEmailRegex(detailToParse);
            break;
        default:
            throw new InvalidFlagException();
        }
    }

    /**
     * Throws an exception if the email id does not comply with the regex.
     * If the email passes the regex check nothing is returned and program
     * flow continues.
     * The function only allows emails in lowercase and uppercase letters,
     * numbers, with symbols including hyphens, underscores, "@" and dot.
     * The symbols cannot be placed at the start or end of the
     * email or be consecutive. Also, only one "@" symbol allowed.
     *
     * @param detailToParse email id to be checked
     * @throws InvalidEmailException If email does not match regex
     */
    protected void checkEmailRegex(String detailToParse) throws InvalidEmailException {
        // Allows lowercase, uppercase, numbers, hyphen, underscore, "@" and dot.
        // Blocks consecutive symbols or if placed at start or end
        // Only one "@" symbol allowed
        String emailRegex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|"
                + "(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|"
                + "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if (!detailToParse.matches(emailRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Email id failed");
            throw new InvalidEmailException();
        }
    }

    /**
     * Throws an exception if the LinkedIn username does not comply with the regex.
     * If the LinkedIn username passes the regex check nothing is returned and program
     * flow continues.
     * The regex only allows usernames in lowercase letters, numbers, underscore and
     * hyphen with a length between 3 and 100 characters.
     *
     * @param detailToParse LinkedIn username to be checked
     * @throws InvalidLinkedinUsernameException If LinkedIn does not match regex
     */
    protected void checkLinkedinUsernameRegex(String detailToParse) throws InvalidLinkedinUsernameException {
        // Allows lowercase, numbers, underscore and hyphen
        // Length must be 3-100 characters
        String linkedinRegex = "^[a-z0-9-_]{3,100}$";
        if (!detailToParse.matches(linkedinRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Linkedin failed");
            throw new InvalidLinkedinUsernameException();
        }
    }

    /**
     * Throws an exception if the Twitter username does not comply with the regex.
     * If the Twitter username passes the regex check nothing is returned and program
     * flow continues.
     * The regex only allows usernames in lowercase letters, numbers, and underscore
     * with a max length of 15 characters.
     *
     * @param detailToParse Twitter username id to be checked
     * @throws InvalidTwitterUsernameException If Twitter username does not match regex
     */
    protected void checkTwitterUsernameRegex(String detailToParse) throws InvalidTwitterUsernameException {
        // Allows lowercase, numbers and underscore
        // Length must be max 15 characters
        String twitterRegex = "^[a-z0-9_]{1,15}$";
        if (!detailToParse.matches(twitterRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Name failed");
            throw new InvalidTwitterUsernameException();
        }
    }

    protected void checkTelegramUsernameRegex(String detailToParse) throws InvalidTelegramUsernameException {
        //allows uppercase, lowercase, numbers and underscore. Length must be at least 5 characters
        String telegramRegex = "^[a-zA-Z0-9_]{5,}$";
        if (!detailToParse.matches(telegramRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Telegram username failed");
            throw new InvalidTelegramUsernameException();
        }
    }

    protected void checkGithubUsernameRegex(String detailToParse) throws InvalidGithubUsernameException {
        //GitHub username may only contain alphanumeric characters or hyphens.
        //GitHub username cannot have multiple consecutive hyphens.
        //GitHub username cannot begin or end with a hyphen.
        //Maximum is 39 characters.
        String githubUsernameRegex = "^[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,39}$";
        if (!detailToParse.matches(githubUsernameRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Github username failed");
            throw new InvalidGithubUsernameException();
        }
    }

    protected void checkNameRegex(String detailToParse) throws InvalidNameException {
        //only letters and spaces allowed
        String nameRegex = "^([a-zA-Z.'/]+\\s)*[a-zA-Z.'/]+$";
        if (!detailToParse.matches(nameRegex)) {
            LOGGER.log(Level.FINE, "Regex check for name failed");
            throw new InvalidNameException();
        }
    }
}
