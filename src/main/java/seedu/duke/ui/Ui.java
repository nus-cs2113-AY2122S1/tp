package seedu.duke.ui;

import seedu.duke.enumerations.PaddingType;
import seedu.duke.constants.Constants;

import static java.lang.System.out;

public class Ui {

    public static void printIndex(int index, boolean println) {
        StringBuilder padding = stringPadder(String.valueOf(index), PaddingType.INDEX);
        String format = Constants.INDEX_WRAP_FRONT + index + Constants.INDEX_WRAP_BACK + padding;
        if (println) {
            out.println(format);
        } else {
            out.print(format);
        }
    }

    static StringBuilder stringPadder(String input, PaddingType type) {
        StringBuilder padding = new StringBuilder();
        int benchmarkLength;
        String fill = " ";
        switch (type) {
        case INDEX: {
            benchmarkLength = Constants.INDEX_LENGTH;
            break;
        }
        case MODULECODE: {
            benchmarkLength = Constants.MODULE_CODE_LENGTH;
            break;
        }
        case MODULENAME: {
            benchmarkLength = Constants.MODUlE_NAME_LENGTH;
            break;
        }
        case UNIVERSITYNAME: {
            benchmarkLength = Constants.UNIVERSITY_NAME_LENGTH;
            fill = "-";
            break;
        }
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        int paddingCount = benchmarkLength - input.length();
        for (int i = 0; i < paddingCount; i++) {
            padding.append(fill);
        }
        return padding;
    }

    public static void welcome() {
        UiAscii.printGlobe();
        UiAscii.printLogo();
    }

    public static void promptInput() {
        out.print("Enter a command:");
    }

    public static void printLineSeparator() {
        out.println(Constants.LINE_SEPARATOR);
    }
}
