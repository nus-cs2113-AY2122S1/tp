package seedu.duke.ui;

import seedu.duke.constants.AsciiConstants;
import seedu.duke.enumerations.PaddingType;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.constants.Constants;

import static java.lang.System.out;

public class Ui {

    public static void printModule(Module mod, int index, boolean printMC) {
        printIndex(index, false);
        StringBuilder codePadding = stringPadder(mod.getModuleCode(), PaddingType.MODULECODE);
        StringBuilder namePadding = stringPadder(mod.getModuleName(), PaddingType.MODULENAME);
        String output;
        if (printMC) {
            output = " " + mod.getModuleCode() + codePadding + Constants.MODULE_NAME_SEPARATOR +
                    mod.getModuleName() + namePadding + mod.getModuleCredits();
        } else {
            output = " " + mod.getModuleCode() + codePadding + Constants.MODULE_NAME_SEPARATOR +
                    mod.getModuleName();
        }
        out.println(output);
    }

    public static void printMappingForList(ModuleMapping mm, int index) {
        out.print(index + " |");
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        System.out.println(mappingDetails);
    }

    public static void printUniversity(University uni, boolean printMC) {
        printIndex(uni.getIndex(), false);
        String output;
        if (printMC) {
            output = uni.getName() + "      " + uni.getMC();
        } else {
            output = uni.getName();
        }
        out.println(output);
    }

    public static void printMapping(ModuleMapping mm, int index) {
        printIndex(index, false);
        String mappingDetails
                = " " + mm.localModule.getModuleCode()
                + Constants.MODULE_MAPPING_SEPARATOR + mm.mappedModule.getModuleCode()
                + Constants.MODULE_NAME_SEPARATOR + mm.mappedModule.getModuleName();
        out.println(mappingDetails);
    }

    public static void printIndex(int index, boolean println) {
        StringBuilder padding = stringPadder(String.valueOf(index), PaddingType.INDEX);
        String format = Constants.INDEX_WRAP_FRONT + index + Constants.INDEX_WRAP_BACK + padding;
        if (println) {
            out.println(format);
        } else {
            out.print(format);
        }
    }

    private static StringBuilder stringPadder(String input, PaddingType type) {
        StringBuilder padding = new StringBuilder();
        int benchmarkLength;
        switch (type) {
        case INDEX: {
            benchmarkLength = Constants.INDEX_LENGTH;
            break;
        }
        case MODULECODE: {
            benchmarkLength = Constants.MODULE_CODE_LENGTH;
            break;
        }
        case MODULENAME: {
            benchmarkLength = Constants.MODUlE_NAME_LENGTH;
        }
        break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        int paddingCount = benchmarkLength - input.length();
        for (int i = 0; i < paddingCount; i++) {
            padding.append(" ");
        }
        return padding;
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
        out.print("Enter a command:");
    }

    public static void printLineSeparator() {
        out.println(Constants.LINE_SEPARATOR);
    }
}
