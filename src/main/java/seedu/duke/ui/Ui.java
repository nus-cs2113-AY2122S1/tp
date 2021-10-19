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
        out.println(" " + mod.getModuleCode());
    }

    public static void printUniversity(University uni, int index, UniversityList universityMasterList) {
        printIndex(index, false);
        out.println(" " + uni.getMasterListIndex(universityMasterList) + "-" + uni.getName());
    }

    public static void printModuleMapping(ModuleMapping mm, int index) {
        printIndex(index, false);
        out.print(" " + mm.localModule + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule);
    }
    
    public static void printIndex(int index, boolean println) {
        String format = Constants.INDEX_WRAP_FRONT + index + Constants.INDEX_WRAP_BACK;
        if (println) {
            out.println(format);
        } else {
            out.print(format);
        }
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
}
