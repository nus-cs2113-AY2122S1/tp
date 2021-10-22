package seedu.duke.ui;

import seedu.duke.constants.AsciiConstants;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.constants.Constants;
import seedu.duke.universities.UniversityList;

import static java.lang.System.out;

public class Ui {

    public static void printModule(Module mod, int index) {
        printIndex(index, false);
        out.println(" " + mod.getModuleCode() + Constants.MODULE_NAME_SEPARATOR + mod.getModuleName());
    }

    public static void printUniversity(University uni, UniversityList universityMasterList) {
        printIndex(uni.getMasterListIndex(universityMasterList), false);
        out.println(Constants.MODULE_MAPPING_SEPARATOR + uni.getName());
    }

    public static void printModuleMapping(ModuleMapping mm, int index) {
        printIndex(index, false);
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        System.out.println(mappingDetails);
    }
    
    public static void printIndex(int index, boolean println) {
        String format = Constants.INDEX_WRAP_FRONT + index + Constants.INDEX_WRAP_BACK;
        if (println) {
            out.println(format);
        } else {
            out.print(format);
        }
    }

    public static void printFindModNull() {
        out.println("No modules found");
    }

    public static void printFindUniNull() {
        out.println("No universities found");
    }

    public static void printGlobe() {
        out.println(AsciiConstants.GLOBE);
    }

    public static void printLogo() {
        out.println(AsciiConstants.LOGO);
    }

    public static void welcome() {
        printGlobe();
        printLogo();
    }

    public static void promptInput() {
        out.print("Enter a command: ");
    }

    public static void printLineSeparator() {
        out.println(Constants.LINE_SEPARATOR);
    }
}
