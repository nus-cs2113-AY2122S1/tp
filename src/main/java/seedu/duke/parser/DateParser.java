package seedu.duke.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import seedu.duke.exception.ParseDateFailedException;

public class DateParser {
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";

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
            throw new ParseDateFailedException(getDefaultDateFormat());
        }
    }

    public static String getDefaultDateFormat() {
        return DATE_FORMAT;
    }
}
