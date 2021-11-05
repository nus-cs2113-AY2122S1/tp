package seplanner.ui;

import seplanner.constants.Constants;
import seplanner.enumerations.PaddingType;
import seplanner.modules.Module;

import static java.lang.System.out;

// @@author titustortoiseturtle1999
public class UiModule extends Ui {

    /**
     * Print module displays the specified module and its index, as well as its module credits if specified.
     * @param mod The specified module to be displayed.
     * @param index The index of the module.
     * @param printMC Whether to print the module credits of the module.
     */
    public static void printModule(Module mod, int index, boolean printMC) {
        printIndex(index, false);
        StringBuilder codePadding = stringPadder(mod.getModuleCode(), PaddingType.MODULECODE);
        String output;
        if (printMC) {
            StringBuilder namePadding = stringPadder(mod.getModuleName(), PaddingType.MODULENAME);
            output = " " + mod.getModuleCode() + codePadding + Constants.MODULE_NAME_SEPARATOR
                    + mod.getModuleName() + namePadding + mod.getModuleCredits();
        } else {
            output = " " + mod.getModuleCode() + codePadding + Constants.MODULE_NAME_SEPARATOR
                    + mod.getModuleName();
        }
        out.println(output);
    }
}
