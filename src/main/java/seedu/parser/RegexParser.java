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

    protected void checkEmailRegex(String detailToParse) throws InvalidEmailException {
        //allow lowercase email ids
        String emailRegex1 = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        String emailRegex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|"
                + "(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|"
                + "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if (!detailToParse.matches(emailRegex)) {
            LOGGER.log(Level.INFO, "Regex check for Email id failed");
            throw new InvalidEmailException();
        }
    }

    protected void checkLinkedinUsernameRegex(String detailToParse) throws InvalidLinkedinUsernameException {
        //allows lowercase, numbers, underscore and hyphen. Length must be 3-100 characters
        String linkedinRegex = "^[a-z0-9-_]{3,100}$";
        if (!detailToParse.matches(linkedinRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Linkedin failed");
            throw new InvalidLinkedinUsernameException();
        }
    }

    protected void checkTwitterUsernameRegex(String detailToParse) throws InvalidTwitterUsernameException {
        //allows lowercase, numbers and underscore. Length must be max 15 characters
        String twitterRegex = "^[a-z0-9_]{1,15}$";
        if (!detailToParse.matches(twitterRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Name failed");
            throw new InvalidTwitterUsernameException();
        }
    }

    protected void checkTelegramUsernameRegex(String detailToParse) throws InvalidTelegramUsernameException {
        //allows uppercase, lowercase, numbers and underscore. Length must be atleast 5 characters
        String telegramRegex = "^[a-zA-Z0-9_]{5,}$";
        if (!detailToParse.matches(telegramRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Telegram username failed");
            throw new InvalidTelegramUsernameException();
        }
    }

    protected void checkGithubUsernameRegex(String detailToParse) throws InvalidGithubUsernameException {
        //Github username may only contain alphanumeric characters or hyphens.
        //Github username cannot have multiple consecutive hyphens.
        //Github username cannot begin or end with a hyphen.
        //Maximum is 39 characters.
        String githubUsernameRegex = "^[a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,39}$";
        if (!detailToParse.matches(githubUsernameRegex)) {
            LOGGER.log(Level.FINE, "Regex check for Github username failed");
            throw new InvalidGithubUsernameException();
        }
    }

    protected void checkNameRegex(String detailToParse) throws InvalidNameException {
        //only letters and spaces allowed
        String nameRegex = "^[ A-Za-z]+$";
        if (!detailToParse.matches(nameRegex)) {
            LOGGER.log(Level.FINE, "Regex check for name failed");
            throw new InvalidNameException();
        }
    }
}
