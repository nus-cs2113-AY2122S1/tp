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
        String mappingDetails = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        if (printMC) {
            mappingDetails += Constants.MODULE_NAME_SEPARATOR + mm.localModule.getModuleCredits();
        }
        out.println(mappingDetails);
    }
}   
