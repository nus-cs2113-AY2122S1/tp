//@@author marcusbory

package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;

public interface ContactDetails {

    String NAME_FLAG = "n";
    String GITHUB_FLAG = "g";
    String TELEGRAM_FLAG = "te";
    String TWITTER_FLAG = "tw";
    String EMAIL_FLAG = "e";
    String LINKEDIN_FLAG = "l";

    /**
     * Returns the index that identifies the detail type denoted by the flag.
     * @param flag Flag specified by the user representing the field
     * @return int Unique index of the detail
     * @throws InvalidFlagException If the flag given is not recognised
     */
    default int getIndexToStore(String flag) throws InvalidFlagException {
        int indexToStore;
        switch (flag) {
        case NAME_FLAG:
            indexToStore = DetailType.NAME.getIndex();
            break;
        case GITHUB_FLAG:
            indexToStore = DetailType.GITHUB.getIndex();
            break;
        case LINKEDIN_FLAG:
            indexToStore = DetailType.LINKEDIN.getIndex();
            break;
        case TELEGRAM_FLAG:
            indexToStore = DetailType.TELEGRAM.getIndex();
            break;
        case TWITTER_FLAG:
            indexToStore = DetailType.TWITTER.getIndex();
            break;
        case EMAIL_FLAG:
            indexToStore = DetailType.EMAIL.getIndex();
            break;
        default:
            throw new InvalidFlagException();
        }
        return indexToStore;
    }
}
