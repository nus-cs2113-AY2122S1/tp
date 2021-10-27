package seedu.duke.ui;

import seedu.duke.constants.Constants;
import seedu.duke.enumerations.PaddingType;
import seedu.duke.modules.Module;

import static java.lang.System.out;

public class UiModule {

    public static void printModule(Module mod, int index, boolean printMC) {
        Ui.printIndex(index, false);
        StringBuilder codePadding = Ui.stringPadder(mod.getModuleCode(), PaddingType.MODULECODE);
        String output;
        if (printMC) {
            StringBuilder namePadding = Ui.stringPadder(mod.getModuleName(), PaddingType.MODULENAME);
            output = " " + mod.getModuleCode() + codePadding + Constants.MODULE_NAME_SEPARATOR
                    + mod.getModuleName() + namePadding + mod.getModuleCredits();
        } else {
            output = " " + mod.getModuleCode() + codePadding + Constants.MODULE_NAME_SEPARATOR
                    + mod.getModuleName();
        }
        out.println(output);
    }
}
