package seedu.ui;

import seedu.command.flags.AddFlag;
import seedu.exceptions.ProfileException;
import seedu.exceptions.UniModsException;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableUserItem;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    private static final int BALANCE_ARRAY = 1;
    private static final int SERIAL = 1;

    public static Scanner in = new Scanner(System.in);

    public static final String LINE = String.format("%120s\n","").replaceAll(" ","_");

    /*------------- PRIVATE LOGGING CONSTANTS ----------- */
    private static final String LOGO = "  _    _       _ __  __           _     \n"
        + " | |  | |     (_)  \\/  |         | |    \n"
        + " | |  | |_ __  _| \\  / | ___   __| |___ \n"
        + " | |  | | '_ \\| | |\\/| |/ _ \\ / _` / __|\n"
        + " | |__| | | | | | |  | | (_) | (_| \\__ \\\n"
        + "  \\____/|_| |_|_|_|  |_|\\___/ \\__,_|___/\n"
        + "                                        \n"
        + "                                        ";

    private static final String STARTUP = "Hello from \n " + LOGO;
    private static final String GREETING = "How can I help you today?";
    public static final String COMMAND_PROMPT = "What would you like to do?";
    public static final String NAME_PROMPT = "What is your name?";
    public static final String MAJOR_PROMPT = "What is your major?";
    public static final String YEAR_PROMPT = "Which year of study are you currently in?";
    private static final String PROFILE_SETUP = "Setup a new profile by answering the following: ";
    private static final String PROMPT_CURSOR = "==> ";
    private static final String PREREQ_SUCCESS = "Yes! You are eligible to take up: ";
    private static final String PREREQ_FAIL = "Oops, you have not met the module's prerequisite: ";
    private static final String HELP_HEADER = LINE + "\tUNIMods accepts the following commands:-\n";
    private static final String HELP_FOOTER =
            "\t ** Note: For details, refer to the User Guide of NUSModsLite at: "
            + "\n\t\thttps://ay2122s1-cs2113t-w12-2.github.io/tp/UserGuide.html\n";

    public static final String ERROR_MODULE_NOT_FOUND = "OOPS, this module does not exist in your timetable!";
    public static final String ERROR_MODULE_NOT_IN_TRANSCRIPT = "OOPS, this module does not exist in your Transcript";
    public static final String ERROR_INVALID_MODULE_CODE =
            "OOPS, it looks like the module code you entered doesn't exist, Please re-check!";
    public static final String ERROR_EMPTY_TIMETABLE = "OOPS, it seems that your timetable is already empty.";
    public static final String ERROR_INVALID_GRADE = "OOPS, it seems that the grade you entered is invalid.";
    public static final String ERROR_INVALID_RESULT_COMMAND = "OOPS, it seems that your command is in wrong format"
            + "The correct command format is =>"
            + " \n \"store\" <grade> \">\" <module_code> ";

    public static final String GRADED = "GRADED";
    public static final String UNGRADED = "UNGRADED";
    public static final String ERROR_EMPTY_RECORD = "OOPS, it seems that you have not added any modules "
            + "and grades to your transcript"
            + "\nType the command below to store the grades in our records =>"
            + "\n \"store\" <grade> \">\" <module_code> ";

    /*------------- PUBLIC COMMANDS ----------- */
    public static String getCommand(String prompt) {
        System.out.println(LINE);
        System.out.println(prompt);
        System.out.print(PROMPT_CURSOR);
        String input = in.nextLine();
        while (input.isEmpty()) {
            System.out.print(PROMPT_CURSOR);
            input = in.nextLine();
        }
        return input;
    }

    public static void printProfileException(ProfileException e) {
        System.out.println(e.getMessage());
        System.out.println(PROFILE_SETUP);
    }

    public static AddFlag getAddFlag() throws UniModsException {
        System.out.println("1. Module");
        System.out.println("2. Event");
        System.out.print("Choose your option: ");
        String input = in.nextLine();
        while (input.isEmpty()) {
            input = in.next();
        }
        if (input.equals("1")) {
            return AddFlag.LESSON;
        } else if (input.equals("2")) {
            return AddFlag.EVENT;
        } else {
            throw new UniModsException("Invalid Selection, please choose either 1 or 2");
        }
    }

    public static String getLessonCommand(String lessonType) {
        String output = "Which " + lessonType + " would you like to choose? ";
        System.out.print(output);
        String input = in.nextLine();
        while (input.isEmpty()) {
            input = in.next();
        }
        return input;
    }

    public static String printAskConfirmation(Lesson lesson) {
        String output = "Note that conflicting lessons will override current lesson/event in timetable\n"
                + "Are you sure you want to add " + lesson.lessonDetails()
                + " as it conflicts with your current timetable (y/n)";
        System.out.print(output);
        String input = in.nextLine();
        while (input.isEmpty()) {
            input = in.next();
        }
        return input;
    }

    public static void printExitMessage() {
        System.out.print(LINE + "> Bye friend!\n> See you again! :)\n" + LINE);
    }

    public static void printHelpHeader() {
        System.out.print(HELP_HEADER);
    }

    public static void printHelpFooter() {
        System.out.println(HELP_FOOTER);
    }

    public static void printInvalidCommandMessage() {
        System.out.print(LINE + "> Sorry friend, I don't know what that means. :/\n");
    }

    public static void printModBriefDescription(Module module) {
        System.out.println(module);
    }

    public static void printModFullDescription(Module module) {
        System.out.println(module.getFullInfo());
    }

    public static void printErrorMessage() {
        System.out.println("Error occurred.");
    }

    public static void printMcErrorMessage() {
        System.out.println("Invalid input for MC flag. MC flag is ignored.");
    }

    public static void printUpdateStartMessage() {
        System.out.println("Updating, standby... This may take up to 10 minutes.");
        System.out.println("Press ENTER to cancel the update.");
    }

    public static void printUpdateInterruptMessage() {
        System.out.println("\n" + LINE + "UPDATE CANCELLED.\n" + LINE);
    }

    public static void printUpdateSuccessMessage() {
        System.out.println("Local data updated successfully.");
    }

    public static void printSearchInterruptMessage() {
        System.out.println(LINE + "SEARCH CANCELLED.\n" + LINE);
    }

    public static void printWelcomeMessage() {
        System.out.println(STARTUP);
    }

    public static void printNotFoundMessage() {
        System.out.println("No matching mod found.");
    }

    public static void printNoConnectionMessage() {
        System.out.println(LINE + "Failed to connect to NUSMods API. This could either be because the mod does not "
                + "exist, or due to a connection error. \nChecking local saved information.");
        System.out.println(LINE);
    }

    public static void printModsFound(int count) {
        String mods = (count == 1) ? " mod " : " mods ";
        System.out.println(count + mods + "found.");
    }

    public static void printLocalSearchMessage() {
        System.out.println(LINE + "!WARNING! This is a local search, data might not be up to date.");
        System.out.println(LINE);
    }

    public static void printAddMessage(String moduleCode) {
        System.out.println("Now adding " + moduleCode + " into timetable");
        System.out.println("Lessons with the same class number are packed together");
        System.out.println("Adding any type of lesson will add all lessons with similar class number into timetable");
    }

    public static void printLessonMessage(String lessonType) {
        switch (lessonType) {
        case "Lecture":
            //System.out.println("Which Lecture time slot would you like to add ?");
            System.out.println("\nLecture Time slots:");
            break;
        case "Tutorial":
            //System.out.println("Which Tutorial time slot would you like to add ?");
            System.out.println("\nTutorial Time slots:");
            break;
        default:
            //System.out.println("Which Laboratory time slot would you like to add ?");
            System.out.println("\nLaboratory Time slots:");
            break;
        }
    }

    public static void printLessonAdded() {
        System.out.println("Lessons for all modules have been successfully added");
    }

    public static void printModuleDeleted(String moduleCode) {
        System.out.println(moduleCode + " is successfully deleted from your Timetable.");
    }

    public static void printTimetableCleared() {
        System.out.println("All modules have been successfully removed from your Timetable.");
    }

    public static String returnLine() {
        return "________________________________________";
    }


    public static boolean isExist(ArrayList<Lesson> lesson) {
        return lesson.size() > 0;
    }

    public static void printPrereqMet(boolean isPrereqMet, Module module) {
        if (isPrereqMet) {
            System.out.println(PREREQ_SUCCESS + module);
        } else {
            System.out.println(PREREQ_FAIL);
            System.out.println(module.getPrerequisite());
        }
    }

    public static void printAddedGrade(String moduleCode, String grade) {
        System.out.println(moduleCode + " with grade " + grade + " has been added to the list of modules completed.");
    }

    public static void printCap(double cap) {
        System.out.println("Cumulative Average Point : " + cap);
    }

    public static void printEmptyRecord() {
        System.out.println(ERROR_EMPTY_RECORD);
    }

    public static void printModuleRemoved(String moduleCode) {
        System.out.println(moduleCode + " is successfully removed from your Transcript.");
    }

    public static int changeSemesterCommand() {
        System.out.println("Change semester? (This will reset timetable)");
        String input = in.nextLine();
        while (input.isEmpty()) {
            input = in.next();
        }
        return Integer.parseInt(input);
    }

    public static void printSemesters(int semester) {
        printCurrentSemester(semester);
        System.out.println("1. Semester 1");
        System.out.println("2. Semester 2");
        System.out.println("3. Special Term 1");
        System.out.println("4. Special Term 2");
        System.out.println("5. cancel");
    }

    public static void printCurrentSemester(int semester) {
        String currentSem = "";
        switch (semester) {
        case 1:
            currentSem = "Semester 1";
            break;
        case 2:
            currentSem = "Semester 2";
            break;
        case 3:
            currentSem = "Special Term 1";
            break;
        case 4:
            currentSem = "Special Term 2";
            break;
        default:
            currentSem = "Semester 1";
        }
        System.out.println("Current semester: " + currentSem);
    }

    public static void printEvents(ArrayList<TimetableUserItem> timetableUserItems) {

        int serial = SERIAL;
        for (TimetableUserItem userItem : timetableUserItems) {
            System.out.println(serial++ + ": " + userItem.toString());
        }
    }

    public static void printEditMessage() {
        System.out.println("Noted, event name has been changed");
    }

    public static void printUpdateProgressMessage(int count) {
        System.out.print("\rApproximately ");
        System.out.printf("%.2f", (double)count / 130);
        System.out.print("% done.");
    }

    public static void printSearchStartMessage() {
        System.out.println("Searching, standby...");
        System.out.println("If nothing is appearing even after a while, press ENTER to cancel the search "
                + "and narrow down your search terms.");
    }

    public static void printInvalidMcMessage() {
        System.out.println("Your MC flag has an invalid input, please check your MC flag.");
    }

    public static void printInvalidFlagMessage() {
        System.out.println("You have inputted some invalid flags, please check your flags.");
    }

    public static void printStorageErrorMessage() {
        System.out.println("Please do not tamper with the local storage. You should be ashamed of yourself. Delete "
                + "all files in your Module directory.");
    }
}
