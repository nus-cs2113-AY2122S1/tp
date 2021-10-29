package seplanner.ui;

import seplanner.constants.AsciiConstants;
import seplanner.enumerations.PaddingType;
import seplanner.constants.Constants;

import static java.lang.System.out;

public abstract class Ui {

    public static void printIndex(int index, boolean println) {
        StringBuilder padding = stringPadder(String.valueOf(index), PaddingType.INDEX);
        String format = Constants.INDEX_WRAP_FRONT + index + Constants.INDEX_WRAP_BACK + padding;
        if (println) {
            out.println(format);
        } else {
            out.print(format);
        }
    }

    protected static StringBuilder stringPadder(String input, PaddingType type) {
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

    protected static void printGlobe() {
        out.println(AsciiConstants.GLOBE);
    }

    protected static void printLogo() {
        out.println(AsciiConstants.LOGO);
    }

    protected static void printPlane() {
        out.println(AsciiConstants.PLANE);
    }

    protected static void printBye() {
        out.println(AsciiConstants.EXIT);
    }
}
