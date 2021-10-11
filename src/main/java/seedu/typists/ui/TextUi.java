package seedu.typists.ui;


<<<<<<< Updated upstream
=======
import java.sql.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;
import static seedu.typists.common.Messages.LOGO;
import static seedu.typists.common.Messages.MESSAGE_ACKNOWLEDGE;
import static seedu.typists.common.Messages.MESSAGE_WELCOME;

>>>>>>> Stashed changes
/**
 * Text UI of the application.
 */
public class TextUi {

<<<<<<< Updated upstream
=======
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

    public ArrayList<String> splitLineIntoWordList(String line) {
        ArrayList<String> wordList = new ArrayList <>();
        wordList.addAll(Arrays.asList(line.split(" ")));
        return wordList;
    }

    /** Print to screen the target words, each line 10 words */
    public void showTargetWordSet(String TargetWordSet) {
        ArrayList<String> wordList = splitLineIntoWordList(TargetWordSet);
        int i = 1;
        for (String w : wordList) {
            out.print(w + " ");
            if (i % 10 == 0) {
                out.print("\n");
            }
            i++;
        }
        out.print("\n");
    }
>>>>>>> Stashed changes
}
