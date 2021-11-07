package taa.attendance;

//@@author daknam2001

import taa.exception.TaaException;
import taa.util.Util;

import java.util.Arrays;
import java.util.List;

public class ParseStudentIndexes {
    private static final String MESSAGE_INVALID_RANGE_FORMAT = "Invalid range format.";
    private static final String MESSAGE_INVALID_SELECTED_FORMAT = "Invalid format for selected indexes.";
    private static final String MESSAGE_INVALID_STUDENT_INDEX = "Invalid student index.";
    private static final int NUMBER_OF_SPLIT = 2;
    private static int startIndex;
    private static int endIndex;

    /**
     * Finds the start index and end index from an inputted String of a range of student index.
     *
     * @param rangeInput The inputted String of a range of student index.
     * @throws TaaException If the user inputs an invalid format.
     */
    public static void getRange(String rangeInput) throws TaaException {
        String[] indexRange = rangeInput.split("-", NUMBER_OF_SPLIT);
        if (!Util.isStringInteger(indexRange[0]) | !Util.isStringInteger(indexRange[1])) {
            throw new TaaException(MESSAGE_INVALID_RANGE_FORMAT);
        }
        startIndex = Integer.parseInt(indexRange[0]);
        endIndex = Integer.parseInt(indexRange[1]);
    }

    /**
     * Returns a list of student index, from an inputted String of selected indexes.
     *
     * @param selectionInput The inputed String of selected indexes.
     * @return A list of student index.
     * @throws TaaException If the user inputs an invalid format.
     */
    public static List<String> getIndexes(String selectionInput) throws TaaException {
        String[] indexes = selectionInput.split(",");
        List<String> studentIndexes = Arrays.asList(indexes);
        for (int i = 0; i < studentIndexes.size(); i++) {
            if (!Util.isStringInteger(studentIndexes.get(i))) {
                throw new TaaException(MESSAGE_INVALID_SELECTED_FORMAT);
            }
        }
        return studentIndexes;
    }

    /**
     * Checks whether an inputted index is an integer.
     *
     * @param indexInput The inputted index.
     * @throws TaaException If the user inputs an invalid index.
     */
    public static void checkIndex(String indexInput) throws TaaException {
        if (!Util.isStringInteger(indexInput)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
    }

    public static int getStartIndex() {
        return startIndex;
    }

    public static int getEndIndex() {
        return endIndex;
    }
}
