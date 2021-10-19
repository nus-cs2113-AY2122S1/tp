package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;

public interface ContactDetails {
    int CONTACT_PARAMS_START_INDEX = 1;
    int NUMBER_OF_DETAILS = 2;
    int NUMBER_OF_ADD_ARGS = 2;
    int NUMBER_OF_EDIT_ARGS = 3;
    int NUMBER_OF_FIELDS = 6;
    // int NUMBER_OF_SEARCH_ARGS = 2 OR 3; -- for andre

    String DETAIL_SEPARATOR = " -";
    int FLAG_INDEX_IN_DETAILS = 0;
    int DETAIL_INDEX_IN_DETAILS = 1;
    int USER_INFO_INDEX = 2;

    String NAME_FLAG = "n";
    String GITHUB_FLAG = "g";
    String TELEGRAM_FLAG = "te";
    String TWITTER_FLAG = "tw";
    String EMAIL_FLAG = "e";
    String LINKEDIN_FLAG = "l";

    default int getIndexToStore(String flag) throws InvalidFlagException {
        int indexToStore;
        switch (flag) {
        case NAME_FLAG:
            indexToStore = DetailType.NAME.getIndex();
            break;
        case GITHUB_FLAG:
            indexToStore = DetailType.GITHUB.getIndex();
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
        case LINKEDIN_FLAG:
            indexToStore = DetailType.LINKEDIN.getIndex();
            break;
        default:
            throw new InvalidFlagException();
        }
        return indexToStore;
    }
}
