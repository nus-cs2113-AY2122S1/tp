package taa.logger;

//@@author leyondlee
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class TaaLoggerFormatter extends Formatter {
    private static final String MESSAGE_FORMAT_LOG_OUTPUT = "%d %s %s %s";

    private static final String DATE_FORMAT = "dd/MM/yyyy HHmm z";

    @Override
    public String format(LogRecord record) {
        String dateTimeString = msToDateTimeString(record.getMillis());

        return String.format(
            MESSAGE_FORMAT_LOG_OUTPUT,
            record.getSequenceNumber(),
            dateTimeString,
            record.getLevel().getName(),
            record.getMessage()
        );
    }

    private String msToDateTimeString(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date(ms);
        return simpleDateFormat.format(date);
    }
}
