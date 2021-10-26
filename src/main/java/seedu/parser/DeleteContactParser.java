package seedu.parser;

import seedu.exception.InvalidDeleteDetailException;
import seedu.exception.InvalidFlagException;

import static seedu.parser.ContactParser.DETAIL_SEPARATOR;
import static seedu.parser.ContactParser.USER_INFO_INDEX;

//@@author ng-andre
public class DeleteContactParser implements ContactDetails {
    private static final int DELETE_CONTACT_INDEX = 6;
    public static final int NUMBER_OF_DELETE_ARGS = 3;
    public static final String BUFFER = " ";

    boolean[] hasDeletedDetail(String userInput) throws InvalidFlagException, InvalidDeleteDetailException {
        boolean[] hasDeletedDetail = new boolean[7]; //all false by default
        //deleteDetails[6] is true if delete entire contact
        String[] inputDetails = userInput.trim().split(" ", NUMBER_OF_DELETE_ARGS);
        if (inputDetails.length < NUMBER_OF_DELETE_ARGS) { //no flags specified, delete entire contact
            hasDeletedDetail[DELETE_CONTACT_INDEX] = true;
            return hasDeletedDetail;
        }
        assert inputDetails.length == NUMBER_OF_DELETE_ARGS; //flags may be specified
        String[] destructuredInputs = (BUFFER + inputDetails[USER_INFO_INDEX]).split(DETAIL_SEPARATOR);
        //handles illegal input "delete 0 -" and "delete 0 [invalid string]"
        //valid input will take the form of [, -flag input] so min length should be 2
        if (destructuredInputs.length < NUMBER_OF_DELETE_ARGS - 1) {
            //input is in the form delete INDEX [invalid string] so delete entire contact
            hasDeletedDetail[DELETE_CONTACT_INDEX] = true;
            return hasDeletedDetail;
        }
        for (int i = 1; i < destructuredInputs.length; i++) {
            int flag = getIndexToStore(destructuredInputs[i].trim());
            //if invalid flag, this will not be executed
            if (flag == 0) { //cannot delete -n
                throw new InvalidDeleteDetailException();
            } else {
                hasDeletedDetail[flag] = true;
            }
        }
        return hasDeletedDetail;
    }
}
