package seedu.duke.parser;

public interface ParserPrefix {
    boolean IS_COMPULSORY = true;
    boolean IS_NOT_COMPULSORY = false;

    String PREFIX_MONTH = "m/";
    String PREFIX_INDEX = "i/";
    String PREFIX_DESCRIPTION = "c/";
    String PREFIX_AMOUNT = "a/";
    String PREFIX_DATE = "d/";
    String PREFIX_NAME = "n/";
}
