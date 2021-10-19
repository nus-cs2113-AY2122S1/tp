package seedu.duke.ui;

import seedu.duke.constants.AsciiConstants;
import seedu.duke.modules.Module;
import seedu.duke.universities.University;
import seedu.duke.constants.Constants;

public class Ui {

    public static void printModule(Module mod, int index) {
        printIndex(index, false);
        System.out.println(" " + mod.getModuleCode());
    }

    public static void printUniversity(University uni, int index) {
        printIndex(index, false);
        System.out.println(" " + uni.getName());
    }
    
    public static void printIndex(int index, boolean println) {
        String format = Constants.INDEX_WRAP_FRONT + index + Constants.INDEX_WRAP_BACK;
        if (println) {
            System.out.println(format);
        } else {
            System.out.print(format);
        }
    }

    public static void printGlobe() {
        System.out.println(AsciiConstants.GLOBE);
    }

    public static void welcome() {
        System.out.println(AsciiConstants.GLOBE);
        System.out.println();
    }
}
