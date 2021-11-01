//@@author lezongmun

package seedu.parser;

import seedu.exception.MissingIndexException;

public class IndexParser {
    private static final int COMD_WORD_LENGTH = 1;
    private static final int INDEX_POSITION = 1;
    private static final int SIGNIFICANT_INDEX_POSITION = 0;
    private static final int COMD_INDEX_LENGTH = 2;
    private static final String PERSONAL_CONTACT_INDEX = "me";
    private static final int PERSONAL_CONTACT_ID = -1;
    private static final String REMOVE_ALL_INDEX = "all";
    private static final int REMOVE_ALL_ID = -2;
    private static final String numbers = "^-?\\d+$";

    //@@author ng-andre
    public static int getIndexFromInput(String userInput)
            throws NumberFormatException, MissingIndexException {
        //split user input into 2 strings: command word string and index string
        String[] destructuredInputs = userInput.split(" ", COMD_INDEX_LENGTH);
        if (destructuredInputs.length <= COMD_WORD_LENGTH) {
            throw new MissingIndexException();
        }
        assert destructuredInputs.length == 2;
        // split index string into words
        String[] indexSplit = destructuredInputs[INDEX_POSITION].trim().split(" ");
        // Since inputs like "rm 1 2 3" are allowed
        // 1 is the significant index that is important and chosen as the index for command
        // index can be "me" or "all" or an integer
        String significantIndex = indexSplit[SIGNIFICANT_INDEX_POSITION].trim();
        if (significantIndex.equalsIgnoreCase(PERSONAL_CONTACT_INDEX)) {
            return PERSONAL_CONTACT_ID;
        }
        if (significantIndex.equalsIgnoreCase(REMOVE_ALL_INDEX)) {
            return REMOVE_ALL_ID;
        }
        if (!significantIndex.matches(numbers)) {
            throw new MissingIndexException();
        }
        // takes only the first word/ element as given user input, and throws NumberFormatExcept if it is not integer
        int index = Integer.parseInt(significantIndex);
        if (index < 0) {
            throw new NumberFormatException();
        }
        return index;
    }
}
