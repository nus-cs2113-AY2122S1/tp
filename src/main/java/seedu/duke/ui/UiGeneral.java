package seedu.duke.ui;

import seedu.duke.constants.AsciiConstants;
import seedu.duke.constants.Constants;

import static java.lang.System.out;

public class UiGeneral extends Ui{
    public static void welcome() {
        printGlobe();
        printLogo();
    }

    public static void printExit() {
        printBye();
        printPlane();
    }

    public static void promptInput() {
        out.print("Enter a command: ");
    }

    public static void printLineSeparator() {
        out.println(Constants.LINE_SEPARATOR);
    }
}
