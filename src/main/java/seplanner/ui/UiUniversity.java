package seplanner.ui;

import seplanner.enumerations.PaddingType;
import seplanner.universities.University;

import static java.lang.System.out;

// @@author titustortoiseturtle1999
public class UiUniversity extends Ui {

    public static void printUniversity(University uni, boolean printMC) {
        printIndex(uni.getIndex(), false);
        String output;
        if (printMC) {
            StringBuilder padding = stringPadder(uni.getName(), PaddingType.UNIVERSITYNAME);
            output = uni.getName() + " " + padding + " " + uni.getMC();
        } else {
            output = uni.getName();
        }
        out.println(output);
    }

}