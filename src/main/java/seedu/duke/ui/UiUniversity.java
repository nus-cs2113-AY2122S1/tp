package seedu.duke.ui;

import seedu.duke.enumerations.PaddingType;
import seedu.duke.universities.University;

import static java.lang.System.out;

public class UiUniversity {

    public static void printUniversity(University uni, boolean printMC) {
        Ui.printIndex(uni.getIndex(), false);
        String output;
        if (printMC) {
            StringBuilder padding = Ui.stringPadder(uni.getName(), PaddingType.UNIVERSITYNAME);
            output = uni.getName() + " " + padding + " " + uni.getMC();
        } else {
            output = uni.getName();
        }
        out.println(output);
    }

}
