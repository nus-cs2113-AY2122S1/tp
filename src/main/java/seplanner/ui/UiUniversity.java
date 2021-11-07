package seplanner.ui;

import seplanner.enumerations.PaddingType;
import seplanner.modules.ModuleList;
import seplanner.universities.University;

import static java.lang.System.out;

// @@author titustortoiseturtle1999
public class UiUniversity extends Ui {

    /**
     * printUniversity displays the specified university, and optionally the cumulative module credits of the
     * module mappings assigned to it.
     * @param uni The specified university to be printed.
     * @param printMC Whether to print the module credits.
     */
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

    /**
     * Print module mappings for modules in the selected module list.
     *
     * @param uni university selected to search module mappings for.
     * @param moduleSelectedList The selected module list which contains only the module selected by the user.
     */
    public static void printMappings(University uni, ModuleList moduleSelectedList) {
        assert uni.getName() != null;
        assert uni.getClass() != null;
        String header = "Potential mappings for " + uni.getName() + " ";
        out.print(header);
        Ui.printIndex(uni.getIndex(), false);
        out.println(":");
        uni.listSelectedMappings(moduleSelectedList);
    }

}