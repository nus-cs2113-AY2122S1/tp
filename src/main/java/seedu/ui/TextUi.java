package seedu.ui;

import seedu.module.Mod;
import seedu.module.ModList;

import java.util.Scanner;

public class TextUi {

    public static Scanner in = new Scanner(System.in);

    public static final String LINE = "____________________________________________________________________________\n";

    public static String getCommand() {
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

    public static boolean printMatchingMod(Mod mod, String searchTerm) {
        if (codeContains(mod, searchTerm)) {
            System.out.println(mod);
            return true;
        }
        /*
        // title match not used for now
        if (codeMatch(mod, searchTerm) || titleMatch(mod, searchTerm)) {
            System.out.println(mod);
        }
        */
        return false;
    }

    private static boolean codeContains(Mod mod, String searchTerm) {
        return mod.getModuleCode().toLowerCase().contains(searchTerm.toLowerCase());
    }

    public static boolean codeMatch(Mod mod, String searchTerm) {
        return mod.getModuleCode().equalsIgnoreCase(searchTerm.toLowerCase());
    }

    private static boolean titleMatch(Mod mod, String searchTerm) {
        return mod.getTitle().toLowerCase().contains(searchTerm.toLowerCase());
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
        String greeting = (isFirstTime) ? "No save data found." : "Save data loaded.";
        System.out.println(greeting);
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

    private static void printFullInfo(Mod mod) {
        System.out.println(mod.getFullInfo());
    }

}
