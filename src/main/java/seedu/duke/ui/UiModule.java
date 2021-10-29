package seedu.duke.ui;

import seedu.duke.constants.Constants;
import seedu.duke.enumerations.PaddingType;
import seedu.duke.modules.Module;

import static java.lang.System.out;

public class UiModule extends Ui{

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
