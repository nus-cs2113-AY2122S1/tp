package seplanner.ui;

import seplanner.constants.Constants;

//@@author madhanse
/**
 * Ui class for returning message when an invalid data is found in the file.
 */
public class UiStorage extends Ui {
    public static String getInvalidModuleMessage() {
        return Constants.LINE_SEPARATOR + System.lineSeparator()
                + Constants.STORAGE_INVALID_MODULE + System.lineSeparator()
                + getWarningMessage();
    }

    public static String getInvalidMappingMessage() {
        return Constants.LINE_SEPARATOR + System.lineSeparator()
                + Constants.STORAGE_INVALID_MAPPING + System.lineSeparator()
                + getWarningMessage();
    }

    public static String getInvalidUniversityMessage() {
        return Constants.LINE_SEPARATOR + System.lineSeparator()
                + Constants.STORAGE_INVALID_UNIVERSITY + System.lineSeparator()
                + getWarningMessage();
    }

    public static String getWarningMessage() {
        return Constants.STORAGE_WARNING_MESSAGE;
    }
}