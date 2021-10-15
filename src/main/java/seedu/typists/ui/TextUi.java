package seedu.typists.ui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;
import static seedu.typists.common.Messages.LOGO;
import static seedu.typists.common.Messages.MESSAGE_ACKNOWLEDGE;
import static seedu.typists.common.Messages.MESSAGE_WELCOME;

/**
 * Text UI of the application.
 */
public class TextUi {
    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String DIVIDER = "****************************************************************";
    private static final String LINE_PREFIX = "     | ";
    private static final String LS = lineSeparator();

    public String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timeFormatter.format(timestamp);
    }

    //solution below adapted from https://github.com/se-edu/addressbook-level2/
    public void showWelcomeMessage(String version) {
        getTimeStamp();
        printScreen(
                version,
                getTimeStamp(),
                DIVIDER,
                LOGO,
                MESSAGE_WELCOME,
                MESSAGE_ACKNOWLEDGE,
                DIVIDER
        );
    }

    public void printScreen(String... message) {
        for (String m: message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public void printLine(String... message) {
        out.print(LINE_PREFIX);
        for (String m: message) {
            out.print(m + " ");
        }
        out.print("\n");
    }

    public void showSummary(int errorWordCount, double WPM, int totalWordTyped, double gameTime) {
        out.println("Wrong words:  " + errorWordCount + "/" + totalWordTyped);
        out.println("WPM: " + String. format("%.2f", WPM));
        out.println("Total Time taken for the game: " + String. format("%.2f", gameTime) + " seconds");
    }
}
