package seedu.duke.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import seedu.duke.exception.ParseDateFailedException;

public class UtilityParser {
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";


    private static final String NOT_NULL_ASSERTION = "Cannot parse null string!";
    public static String getDateAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static Date getStringAsDate(String date) throws ParseDateFailedException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return formatter.parse(date);
        } catch (ParseException pe) {
            throw new ParseDateFailedException(DATE_FORMAT);
        }
    }
}
