package seedu.ui;

import seedu.module.Module;
import seedu.module.ModList;

import java.util.Scanner;

public class TextUi {

    public static Scanner in = new Scanner(System.in);

    public static final String LINE = "____________________________________________________________________________\n";

    /*------------- PRIVATE LOGGING CONSTANTS ----------- */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String STARTUP = "Hello from \n " + LOGO;
    private static final String GREETING = "How can I help you today?";


    /*------------- PUBLIC COMMANDS ----------- */
    public static String getCommand() {
        System.out.println();
        return in.nextLine();
    }

    public static void printExitMessage() {
        System.out.print(LINE + "> Bye friend!\n> See you again! :)\n" + LINE);
    }

    public static void printInvalidCommandMessage() {
        System.out.print(LINE + "> Sorry friend, I don't know what that means. :/\n" + LINE);
    }

    public static void searchMods(ModList modList, String searchTerm) {
        int count = 0;
        for (int i = 0; i < modList.getSize(); i++) {
            if (printMatchingMod(modList.getMod(i), searchTerm)) {
                count += 1;
            }
        }
        System.out.println(count + " matching mods found.");
    }

    public static boolean printMatchingMod(Module module, String searchTerm) {
        if (codeContains(module, searchTerm)) {
            System.out.println(module);
            return true;
        }
        // title match not used for now
//        if (codeMatch(module, searchTerm) || titleMatch(module, searchTerm)) {
//            System.out.println(module);
//        }
        return false;
    }

    private static boolean codeContains(Module module, String searchTerm) {
        return module.getModuleCode().toLowerCase().contains(searchTerm.toLowerCase());
    }

    public static boolean codeMatch(Module module, String searchTerm) {
        return module.getModuleCode().equalsIgnoreCase(searchTerm.toLowerCase());
    }

    private static boolean titleMatch(Module module, String searchTerm) {
        return module.getTitle().toLowerCase().contains(searchTerm.toLowerCase());
    }

    public static void printErrorMessage() {
        System.out.println("Error occurred.");
    }

    public static void printUpdateStartMessage() {
        System.out.println("Updating, standby...");
    }

    public static void printUpdateSuccessMessage() {
        System.out.println("Local data updated successfully.");
    }

    public static void printWelcomeMessage(boolean isFirstTime) {
        String loadDataResult = (isFirstTime) ? "No save data found." : "Save data loaded.";
        System.out.println(STARTUP);
        System.out.println(loadDataResult);
        System.out.println(GREETING);
    }

    public static void printLoadError() {
        System.out.println("Save data failed to load. Fetch data from NUSMods with \"update\".");
    }

    public static void showMod(ModList modList, String searchTerm) {
        for (int i = 0; i < modList.getSize(); i++) {
            if (codeMatch(modList.getMod(i), searchTerm)) {
                printFullInfo(modList.getMod(i));
                return;
            }
        }
        printNotFoundMessage();
    }

    private static void printNotFoundMessage() {
        System.out.println("No matching mod found.");
    }

    private static void printFullInfo(Module module) {
        System.out.println(module.getFullInfo());
    }

}
