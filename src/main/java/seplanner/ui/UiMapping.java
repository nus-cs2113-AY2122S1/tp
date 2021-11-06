package seplanner.ui;

import seplanner.constants.Constants;
import seplanner.modules.ModuleMapping;

import static java.lang.System.out;

// @@author titustortoiseturtle1999
public class UiMapping extends Ui {
    /**
     * printMapping prints the specified module mapping and its index.
     * @param mm The specified module mapping.
     * @param index The index of the module mapping.
     */
    public static void printMapping(ModuleMapping mm, int index, boolean printMC) {
        printIndex(index, false);
        String mappingDetails = " " + mm.getLocalModule().getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.getMappedModule().getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.getMappedModule().getModuleName();
        if (printMC) {
            mappingDetails += Constants.MODULE_NAME_SEPARATOR + mm.getLocalModule().getModuleCredits();
        }
        out.println(mappingDetails);
    }
}   
