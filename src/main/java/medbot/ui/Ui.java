package medbot.ui;

import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

import java.util.List;
import java.util.Scanner;

/**
 * Represents a UI class that interacts with User
 * (Reading user input and printing message to users).
 */
public class Ui {
    public static final String VERTICAL_LINE_SPACED_ESCAPED = " \\| ";
    public static final String VERTICAL_LINE_SPACED = " | ";
    public static final String END_LINE = System.lineSeparator();
    private static final String ERROR_VIEW_CONTEXT_NOT_FOUND = "Cannot identify the current view type" + END_LINE;
    private static final String NO_PERSON_MESSAGE = "There is no person with such attributes in this list." + END_LINE;
    private static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;

    private final Scanner inputScanner = new Scanner(System.in);

    /**
     * Gets user input from terminal and returns it as a String.
     *
     * @return the String containing the user input.
     */
    public String readInput() {
        String line;
        line = inputScanner.nextLine();
        return line;
    }

    /**
     * Prints a message.
     *
     * @param outputMessage the message to be printed
     */
    public void printOutput(String outputMessage) {
        System.out.println(outputMessage);
    }

    /**
     * Prints a welcome message when MedBot is first loaded.
     */
    public void printWelcomeMessageOne() {
        printOutput("Hello, I'm MedBot!");
    }

    /**
     * Prints a welcome message when MedBot file storage is successfully loaded.
     */
    public void printWelcomeMessageTwo() {
        printOutput("How can I help you today?" + END_LINE);
    }

    /**
     * Returns a message when successfully add to a list.
     *
     * @param viewType the viewType context of the command
     * @return the Successful Message
     */
    public static String getAddMessage(String info, ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getAddPatientMessage(info);
        case SCHEDULER:
            return SchedulerUi.getAddAppointmentMessage(info);
        case MEDICAL_STAFF_INFO:
            return StaffUi.getAddStaffMessage(info);
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Returns a message when successfully delete an object from a list.
     *
     * @param id       the ID of the object to be deleted.
     * @param viewType the viewType context of the command.
     * @return the Successful Message
     */
    public static String getDeleteMessage(int id, ViewType viewType) throws MedBotException {
        assert id > 0;
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getDeletePatientMessage(id);
        case SCHEDULER:
            return SchedulerUi.getDeleteAppointmentMessage(id);
        case MEDICAL_STAFF_INFO:
            return StaffUi.getDeleteStaffMessage(id);
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Returns a message when successfully edit an object in a list.
     *
     * @param id       the ID of the object to be edited.
     * @param info     the information of the new object
     * @param viewType the viewType context of the command.
     * @return the Successful Message
     */
    public static String getEditMessage(int id, String info, ViewType viewType) throws MedBotException {
        assert id > 0;
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getEditPatientMessage(id, info);
        case SCHEDULER:
            return SchedulerUi.getEditAppointmentMessage(id, info);
        case MEDICAL_STAFF_INFO:
            return StaffUi.getEditStaffMessage(id, info);
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Returns a message String containing the information of the object.
     *
     * @param info the information of the object
     * @param viewType the viewType context of the command.
     * @return message String containing the information of the object
     */
    public static String getViewMessage(String info, ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getViewPatientMessage(info);
        case SCHEDULER:
            return SchedulerUi.getViewAppointmentMessage(info);
        case MEDICAL_STAFF_INFO:
            return StaffUi.getViewStaffMessage(info);
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Returns the information of the filtered patients.
     *
     * @param persons  the filtered patients to be printed.
     * @param viewType the viewType context of the command.
     * @return The information of the filtered patients
     */
    public static String getFindPersonsMessage(List<String> persons, ViewType viewType) throws MedBotException {
        if (persons.size() == 0) {
            return NO_PERSON_MESSAGE;
        }
        String output;
        switch (viewType) {
        case PATIENT_INFO:
            output = PatientUi.getPatientTableMatchedHeader();
            break;
        case MEDICAL_STAFF_INFO:
            output = StaffUi.getStaffTableMatchedHeader();
            break;
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
        for (String person : persons) {
            output += person;
            output += END_LINE;
        }
        output += TABLE_ROW_SEPARATOR;
        return output;
    }

    /**
     * Returns the successful message of archiving a person.
     *
     * @param personId the ID of the person to be hidden.
     * @param viewType the viewType context of the command.
     * @return The successful message of archiving the person
     */
    public static String getHidePersonMessage(int personId, ViewType viewType) throws MedBotException {
        String output;
        switch (viewType) {
        case PATIENT_INFO:
            output = PatientUi.getHidePatientMessage(personId);
            break;
        case MEDICAL_STAFF_INFO:
            output = StaffUi.getHideStaffMessage(personId);
            break;
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }

        return output;
    }

    /**
     * Returns the successful message of un-archiving a person.
     *
     * @param personId the ID of the person to be shown.
     * @param viewType the viewType context of the command.
     * @return The successful message of un-archiving the person
     */
    public static String getShowPersonMessage(int personId, ViewType viewType) throws MedBotException {
        String output;
        switch (viewType) {
        case PATIENT_INFO:
            output = PatientUi.getShowPatientMessage(personId);
            break;
        case MEDICAL_STAFF_INFO:
            output = StaffUi.getShowStaffMessage(personId);
            break;
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }

        return output;
    }

    /**
     * Returns the message indicate the current viewType.
     *
     * @param viewType the viewType context of the command.
     * @return The current view message
     * @throws MedBotException when the current view is unidentifiable
     */
    public static String getCurrentViewMessage(ViewType viewType) throws MedBotException {
        String output;
        switch (viewType) {
        case PATIENT_INFO:
            output = PatientUi.getCurrentViewPatientMessage();
            break;
        case MEDICAL_STAFF_INFO:
            output = StaffUi.getCurrentViewStaffMessage();
            break;
        case SCHEDULER:
            output = SchedulerUi.getCurrentViewMessage();
            break;
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }

        return output;
    }

    /**
     * Prints an exit message when MedBot is exiting.
     *
     * @return the exit Message
     */
    public static String getExitMessage() {
        return "Thank you for using MedBot!" + END_LINE + "See you again!";
    }


    /**
     * Prints a list of all available commands.
     *
     * @param viewType the viewType context of the command.
     * @return all supported commands.
     */
    public static String getCommandList(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getPatientCommandList();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getStaffCommandList();
        case SCHEDULER:
            return SchedulerUi.getSchedulerCommandList();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Prints information about list command.
     *
     * @param viewType the viewType context of the command.
     * @return the information on list command.
     */
    public String getListHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getListPatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getListStaffHelpMessage();
        case SCHEDULER:
            return SchedulerUi.getListAppointmentHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }


    /**
     * Prints information about view command.
     *
     * @param viewType the viewType context of the command.
     * @return the information on view command.
     */
    public String getViewHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getViewPatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getViewStaffHelpMessage();
        case SCHEDULER:
            return SchedulerUi.getViewAppointmentHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }


    /**
     * Prints information about add command.
     *
     * @param viewType the viewType context of the command.
     * @return the information on add command.
     */
    public String getAddHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getAddPatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getAddStaffHelpMessage();
        case SCHEDULER:
            return SchedulerUi.getAddAppointmentHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }


    /**
     * Prints information about edit command.
     *
     * @param viewType the viewType context of the command.
     * @return the information on edit command.
     */
    public String getEditHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getEditPatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getEditStaffHelpMessage();
        case SCHEDULER:
            return SchedulerUi.getEditAppointmentHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Prints information about delete command.
     *
     * @param viewType the viewType context of the command.
     * @return the information on delete command.
     */
    public String getDeleteHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getDeletePatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getDeleteStaffHelpMessage();
        case SCHEDULER:
            return SchedulerUi.getDeleteAppointmentHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }


    /**
     * Prints information about find command.
     *
     * @param viewType the viewType context of the command.
     * @return the information on find command.
     */
    public String getFindHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getFindPatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getFindStaffHelpMessage();
        case SCHEDULER:
            return SchedulerUi.getFindAppointmentHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Prints information about switch command.
     *
     * @return the information on switch command.
     */
    public String getSwitchHelpMessage() {
        return "Switches between the different views of MedBot." + END_LINE
                + "Format: switch [VIEW_TYPE]" + END_LINE
                + "If the switch command is called without any parameters," + END_LINE
                + "the view that is switched to will depend on the current view." + END_LINE
                + "(Patient Management --> Staff Management --> Scheduler --> Patient Management)" + END_LINE
                + "Expected Output:" + END_LINE
                + "[VIEW_TYPE_ASCI_TEXT_BANNER]" + END_LINE + END_LINE
                + "View has been switched to [VIEW_TYPE]" + END_LINE;
    }

    /**
     * Prints information about hide command.
     *
     * @return the information on hide command.
     */
    public String getHideHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getHidePatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getHideStaffHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }

    /**
     * Prints information about hide command.
     *
     * @return the information on hide command.
     */
    public String getShowHelpMessage(ViewType viewType) throws MedBotException {
        switch (viewType) {
        case PATIENT_INFO:
            return PatientUi.getShowPatientHelpMessage();
        case MEDICAL_STAFF_INFO:
            return StaffUi.getShowStaffHelpMessage();
        default:
            assert false;
            throw new MedBotException(ERROR_VIEW_CONTEXT_NOT_FOUND);
        }
    }


    /**
     * Prints information about exit command.
     *
     * @return the information on exit command.
     */
    public String getExitHelpMessage() {
        return "Exits the program." + END_LINE + "Format: exit" + END_LINE;
    }

    /**
     * Prints information about get View command.
     *
     * @return the information on get View command.
     */
    public String getGetCurrentViewHelpMessage() {
        return "Get the current viewType." + END_LINE + "Format: get view" + END_LINE;
    }

    //@@author Kureans

    /**
     * Utility function that performs a pseudo-clear of the console.
     */
    public static void clearConsoleFromIde() {
        System.out.print(END_LINE + END_LINE + END_LINE + END_LINE + END_LINE);
    }

    /**
     * Prints the switched view message if the view is switched to the given viewType.
     *
     * @param viewType the ViewType that is switched to
     */
    public void printSwitchedViewMessage(ViewType viewType) {
        switch (viewType) {
        case PATIENT_INFO:
            printOutput("  ___  _ _____ ___ ___ _  _ _____ \n"
                    + " | _ \\/_\\_   _|_ _| __| \\| |_   _|\n"
                    + " |  _/ _ \\| |  | || _|| .` | | |  \n"
                    + " |_|/_/ \\_\\_|_|___|___|_|\\_| |_|  \n"
                    + " |_ _| \\| | __/ _ \\               \n"
                    + "  | || .` | _| (_) |              \n"
                    + " |___|_|\\_|_|_\\___/    __         \n"
                    + " \\ \\ / /_ _| __\\ \\    / /         \n"
                    + "  \\ V / | || _| \\ \\/\\/ /          \n"
                    + "   \\_/ |___|___| \\_/\\_/           \n"
                    + "                                  ");
            printOutput("You are now in the Patient Management view");
            break;
        case SCHEDULER:
            printOutput("  ___  ___ _  _ ___ ___  _   _ _    ___ ___ \n"
                    + " / __|/ __| || | __|   \\| | | | |  | __| _ \\\n"
                    + " \\__ \\ (__| __ | _|| |) | |_| | |__| _||   /\n"
                    + " |___/\\___|_||_|___|___/_\\___/|____|___|_|_\\\n"
                    + " \\ \\ / /_ _| __\\ \\    / /                   \n"
                    + "  \\ V / | || _| \\ \\/\\/ /                    \n"
                    + "   \\_/ |___|___| \\_/\\_/                     \n"
                    + "                                            ");
            printOutput("You are now in the Scheduler view.");
            break;
        case MEDICAL_STAFF_INFO:
            printOutput("  ___ _____ _   ___ ___  \n"
                    + " / __|_   _/_\\ | __| __| \n"
                    + " \\__ \\ | |/ _ \\| _|| _|  \n"
                    + " |___/_|_/_/_\\_\\_| |_|   \n"
                    + " |_ _| \\| | __/ _ \\      \n"
                    + "  | || .` | _| (_) |     \n"
                    + " |___|_|\\_|_|_\\___/    __\n"
                    + " \\ \\ / /_ _| __\\ \\    / /\n"
                    + "  \\ V / | || _| \\ \\/\\/ / \n"
                    + "   \\_/ |___|___| \\_/\\_/  \n"
                    + "                         ");
            printOutput("You are now in the Staff Management view.");
            break;
        default:
            break;
        }

    }

    /**
     * Prints the switched view message if the view is already at the given viewType.
     *
     * @param viewType the ViewType that the application is already at
     */
    public void printUnchangedViewMessage(ViewType viewType) {
        switch (viewType) {
        case PATIENT_INFO:
            printOutput("You are already in the Patient Management view.");
            break;
        case MEDICAL_STAFF_INFO:
            printOutput("You are already in the Staff Management view.");
            break;
        case SCHEDULER:
            printOutput("You are already in the Scheduler view.");
            break;
        default:
            break;
        }
    }
}
