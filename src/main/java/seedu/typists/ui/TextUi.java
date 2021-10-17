package seedu.typists.ui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;
import static seedu.typists.common.Messages.CLOCK;
import static seedu.typists.common.Messages.KEYBOARD;
import static seedu.typists.common.Messages.LETTER;
import static seedu.typists.common.Messages.LOGO;
import static seedu.typists.common.Messages.MESSAGE_ACKNOWLEDGE;
import static seedu.typists.common.Messages.MESSAGE_HELP;
import static seedu.typists.common.Messages.MESSAGE_WELCOME;
import static seedu.typists.common.Messages.SUMMARY;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {
    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String DIVIDER = "****************************************************************";
    private static final String LINE_PREFIX = "     | ";
    private static final String LS = lineSeparator();

    //get current timestamp
    //unused because it interferes with the EXPECTED.TXT in runtest
    public String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timeFormatter.format(timestamp);
    }

    //solution below adapted from https://github.com/se-edu/addressbook-level2/
    public void showWelcomeMessage(String version) {
        getTimeStamp();
        printScreen(
                version,
                //getTimeStamp(),
                DIVIDER,
                LOGO,
                MESSAGE_WELCOME,
                MESSAGE_ACKNOWLEDGE,
                MESSAGE_HELP,
                DIVIDER
        );
    }

    public void printScreen(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public void printLine(String... message) {
        out.print(LINE_PREFIX);
        for (String m : message) {
            out.print(m + " ");
        }
        out.print("\n");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Print error message.
     *
     * @param meg obtained from DukeException message
     */
    public void showText(String meg) {
        System.out.println(meg);
    }

    public void showNumber(int i) {
        System.out.println(String.valueOf(i));
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printGameMode1Progress(int a, int b) {
        System.out.println("Your progress:" + String.valueOf(a) + "/" + String.valueOf(b));
    }

    public void printSuccess() {
        System.out.println("Finished!");
    }

    public void printKeyboard() {
        out.println(KEYBOARD);
    }

    public void printLetter() {
        out.println(LETTER);
    }

    public void printClock() {
        out.println(CLOCK);
    }

    public void printBookSelection() {
        System.out.println("Input '0' to go back.\n"
                + "Content list:\n"
                + "1. A Confederacy of Dunces\n"
                + "2. Moby Dick\n"
                + "3. A Farewell to Arms\n"
                + "4. A Tale of Two Cities\n"
                + "5. On the Road\n"
                + "6. Bright on Rock\n"
                + "7. Pick Up\n"
                + "8. Pride and Prejudice\n"
                + "9. White Fang\n"
                + "10. American Psycho\n"
                + "11. Notes from Underground\n"
                + "12. The Haunting of Hill House\n"
                + "13. Metamorphosis\n"
                + "14. Invisible Man\n"
                + "15. The Adventures of Huckleberry Finn");
    }

    public void showErrorSummary(int wrongWordCount, int totalWordCount, double sentenceErrorRate) {
        out.println("Wrong words:  " + wrongWordCount + "/" + totalWordCount);
        out.println("Error rate:  " + String.format("%.2f", sentenceErrorRate));
    }

    public void showSummary(int errorWordCount, double errorPercentage, double wpm,
                            int totalWordTyped, double gameTime) {
        out.print(SUMMARY + '\n');
        out.print("Wrong Words: " + errorWordCount + "/" + totalWordTyped + '\n');
        out.print("Error Percentage: " + String.format("%.2f", errorPercentage) + "%\n");
        out.print("WPM: " + String.format("%.2f", wpm) + '\n');
        out.print("Total Time taken for the game: " + String.format("%.2f", gameTime) + " seconds\n");
    }
}
