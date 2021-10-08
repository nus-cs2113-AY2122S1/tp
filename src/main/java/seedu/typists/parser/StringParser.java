package seedu.typists.parser;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.typists.exception.InvalidStringInputException;

public class StringParser {

    /**
     * Splits the string based on the separator provided as parameter.
     *
     * @param stringToSplit the string to be split
     * @param separator    a string representing the separator that you want to split the string on
     * @return ArrayList of strings
     */
    public ArrayList<String> splitString(String stringToSplit, String separator) throws InvalidStringInputException {

        if(stringToSplit == null){
            throw new InvalidStringInputException();
        }
        ArrayList<String> stringParts = new ArrayList<>();
        stringParts.addAll(Arrays.asList(stringToSplit.split(separator)));

        return stringParts;
    }
}