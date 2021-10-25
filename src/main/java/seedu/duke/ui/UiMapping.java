package seedu.duke.ui;

import seedu.duke.constants.Constants;
import seedu.duke.modules.ModuleMapping;

import static java.lang.System.out;

public class UiMapping {

    public static void printMappingForList(ModuleMapping mm, int index) {
        out.print(index + " |");
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        System.out.println(mappingDetails);
    }

    public static void printMapping(ModuleMapping mm, int index) {
        Ui.printIndex(index, false);
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        out.println(mappingDetails);
    }
}
