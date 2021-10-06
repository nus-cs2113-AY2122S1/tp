package seedu.parser;

import seedu.exception.InvalidFlagException;

public abstract class ContactParser {
    public static final int CONTACT_PARAMS_START_INDEX = 1;
    public static final int NUMBER_OF_DETAILS = 2;
    public static final int NUMBER_OF_EDIT_DETAILS = 3;

    public static final String DETAIL_SEPARATOR = " -";
    public static final int DETAILS_TO_SPLIT = 2;
    public static final int FLAG_INDEX_IN_DETAILS = 0;
    public static final int DETAIL_INDEX_IN_DETAILS = 1;

    public static final String NAME_FLAG = "n";
    public static final String GITHUB_FLAG = "g";

    public abstract String[] parseContactDetails(String userInput) throws InvalidFlagException;
}
