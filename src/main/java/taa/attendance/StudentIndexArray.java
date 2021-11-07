package taa.attendance;

//@@author daknam2001

import taa.exception.TaaException;

import java.util.ArrayList;
import java.util.List;


public class StudentIndexArray {
    private final ArrayList<Integer> studentIndexes;

    public StudentIndexArray() {
        this.studentIndexes = new ArrayList<>();
    }

    public int getSize() {
        return studentIndexes.size();
    }

    /**
     * Sets the studentIndexes ArrayList when the user inputs a range of indexes.
     *
     * @param rangeInput The inputted String of a range of student index.
     * @throws TaaException If the user inputs an invalid range.
     */
    public void setStudentIndexesForRange(String rangeInput) throws TaaException {
        ParseStudentIndexes.getRange(rangeInput);
        for (int i = ParseStudentIndexes.getStartIndex(); i <= ParseStudentIndexes.getEndIndex(); i++) {
            studentIndexes.add(i);
        }
    }

    /**
     * Sets the studentIndexes ArrayList when the user inputs a selection of indexes.
     *
     * @param selectionInput The inputted String of a selection of student index.
     * @throws TaaException If the user inputs an invalid selection.
     */
    public void setStudentIndexesForSelection(String selectionInput) throws TaaException {
        List<String> studentIndexStrings = ParseStudentIndexes.getIndexes(selectionInput);
        for (int i = 0; i < studentIndexStrings.size(); i++) {
            studentIndexes.add(Integer.parseInt(studentIndexStrings.get(i)));
        }
    }

    /**
     * Sets the studentIndexes ArrayList when the user only inputs one index.
     *
     * @param indexInput The inputted student index.
     * @throws TaaException If the user inputs an invalid student index.
     */
    public void setSingleStudentIndex(String indexInput) throws TaaException {
        ParseStudentIndexes.checkIndex(indexInput);
        studentIndexes.add(Integer.parseInt(indexInput));
    }

    /**
     * Sets the studentIndexes ArrayList depending on the type (range, selection, single) of inputted student indexes.
     *
     * @param indexesInput The String of inputted student indexes.
     * @throws TaaException If the user inputs invalid student index/indexes.
     */
    public void setStudentIndexes(String indexesInput) throws TaaException {
        if (indexesInput.contains("-")) {
            setStudentIndexesForRange(indexesInput);
        } else if (indexesInput.contains(",")) {
            setStudentIndexesForSelection(indexesInput);
        } else {
            setSingleStudentIndex(indexesInput);
        }
    }

    public int getStudentIndex(int index) {
        return studentIndexes.get(index);
    }
}
