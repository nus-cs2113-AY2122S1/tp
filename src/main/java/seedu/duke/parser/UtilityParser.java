package seedu.duke.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityParser {
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";

    private static final String PARSE_FAILED_ERROR_MESSAGE
            = String.format("String did not match format '%s'.", DATE_FORMAT);

    public static String getDateAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static Date getStringAsDate(String date) throws ParseException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return formatter.parse(date);
        } catch (ParseException pe) {
            throw new ParseException(PARSE_FAILED_ERROR_MESSAGE, pe.getErrorOffset());
        }
    }
}
