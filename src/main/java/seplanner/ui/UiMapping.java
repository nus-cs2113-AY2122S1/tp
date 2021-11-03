package seplanner.ui;

import seplanner.constants.Constants;
import seplanner.modules.ModuleMapping;

import static java.lang.System.out;

public class UiMapping extends Ui {

    public static void printMappingForList(ModuleMapping mm, int index) {
        out.print(index + " |");
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleCredits();
        System.out.println(mappingDetails);
    }

    public static void printMapping(ModuleMapping mm, int index) {
        printIndex(index, false);
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        out.println(mappingDetails);
    }
}   
