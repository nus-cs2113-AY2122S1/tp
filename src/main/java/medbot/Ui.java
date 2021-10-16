package medbot;

import medbot.utilities.ViewType;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a UI class that interacts with User
 * (Reading user input and printing message to users).
 */
public class Ui {
    public static final String VERTICAL_LINE_SPACED_ESCAPED = " \\| ";
    public static final String VERTICAL_LINE_SPACED = " | ";

    private Scanner inputScanner = new Scanner(System.in);

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
        printOutput("Hello, I'm MedBot!" + System.lineSeparator());
    }

    /**
     * Prints a welcome message when MedBot file storage is successfully loaded.
     */
    public void printWelcomeMessageTwo() {
        printOutput("How can I help you today?" + System.lineSeparator() + System.lineSeparator());
    }

    /**
     * Returns a message when successfully add a patient to a list.
     *
     * @param patientId the ID of the patient to be added
     * @return the Successful Message
     */
    public String getAddPatientMessage(int patientId) {
        assert patientId > 0;
        return "Added patient with patient ID: " + patientId;
    }

    /**
     * Returns a message when successfully delete a patient from a list.
     *
     * @param patientId the ID of the patient to be deleted.
     * @return the Successful Message
     */
    public String getDeletePatientMessage(int patientId) {
        assert patientId > 0;
        return "Patient with id " + patientId + " deleted from system.";
    }

    /**
     * Returns a message when successfully edit a patient in a list.
     *
     * @param patientId the ID of the patient to be edited.
     * @return the Successful Message
     */
    public String getEditPatientMessage(int patientId, String patientInfo) {
        assert patientId > 0;
        return "The information of patient with ID " + patientId + " has been edited to:"
                + System.lineSeparator() + patientInfo;
    }

    /**
     * Prints an exit message when MedBot is exiting.
     *
     * @return the exit Message
     */
    public String getExitMessage() {
        return "Thank you for using MedBot!" + System.lineSeparator() + "See you again!";
    }

    /**
     * Prints a message when viewing the profile of a patient.
     *
     * @param patientInfo the Info of the patient to be printed.
     * @return the Patient information
     */
    public String getPatientInfo(String patientInfo) {
        return "Here's the requested patient:" + System.lineSeparator() + patientInfo;
    }

    /**
     * Prints all patients in a list.
     *
     * @param patientList the list containing patients to be printed.
     * @return all Patients' information.
     */
    public String getAllPatientsString(PatientList patientList) {
        String output = "Here is a list of all patients:" + System.lineSeparator();
        output += patientList.listPatients();

        return output;
    }

    /**
     * Prints a list of all available commands.
     *
     * @return all supported commands.
     */
    public String getCommandList() {
        return "Here are the list of commands:\n\n"
                + "help\n" + "add\n" + "list\n" + "view\n" + "edit\n" + "delete\n"
                + "exit\n" + "\n"
                + "To obtain more information on each command and their respective required inputs, type:\n"
                + "help [COMMAND]\n\n"
                + "*Note that all commands will remove any '|' inputs for format parsing purposes";
    }

    /**
     * Prints information about list command.
     *
     * @return the information on list command.
     */
    public String getListHelpMessage() {
        return "View information of all current patients.\n"
                + "Format: list\n" + "Expected Output for 2 patients: \n"
                + "Patient ID: [PATIENT_ID_1] IC: [PATIENT_IC]"
                + "Name: [PATIENT_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]\n"
                + "Patient ID: [PATIENT_ID_2] IC: [PATIENT_IC]"
                + "Name: [PATIENT_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]\n";
    }

    /**
     * Prints information about help command.
     *
     * @return the information on help command.
     */
    public String getViewHelpMessage() {
        return "View a patient’s personal information.\n" + "Format: view PATIENT_ID\n"
                + "Expected Output:\n" + "id: PATIENT_ID " + "name: NAME "
                + "phone number: PHONE_NUMBER " + "email: EMAIL " + "address: ADDRESS\n";
    }

    /**
     * Prints information about add command.
     *
     * @return the information on add command.
     */
    public String getAddHelpMessage() {
        return "Add a patient to the patient’s list.\n"
                + "Format:\n"
                + "add i/PATIENT_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]\n"
                + "Expected output:\n"
                + "Added patient with patient ID: PATIENT_ID";
    }

    /**
     * Prints information about edit command.
     *
     * @return the information on edit command.
     */
    public String getEditHelpMessage() {
        return "Edit the personal and medical information of a patient in the list.\n"
                + "Format:\n"
                + "edit PATIENT ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]\n"
                + "Expected output: \n"
                + "The information of patient with ID PATIENT_ID has been edited to:\n"
                + "Patient ID: [PATIENT_ID] IC: [PATIENT_IC] Name: [NAME] H/P: [PHONE_NUMBER] "
                + "Email: [EMAIL] Address: [ADDRESS] \n";
    }

    /**
     * Prints information about delete command.
     *
     * @return the information on delete command.
     */
    public String getDeleteHelpMessage() {
        return "Delete a patient from the list.\n"
                + "Format:\n"
                + "delete PATIENT_ID\n"
                + "Expected Output:\n"
                + "Patient with id PATIENT_ID deleted from system.\n";
    }

    /**
     * Prints information about exit command.
     *
     * @return the information on exot command.
     */
    public String getExitHelpMessage() {
        return "Exits the program.\n" + "Format: exit\n";
    }

    /**
     * Prints unrecognised command message.
     *
     * @return the error message on unrecognised command.
     */
    public String getUnrecognisedCommandHelpMessage() {
        return "Sorry, that's not a recognised command. To view a list of commands, type:\n" + "help\n";
    }

    /**
     * Utility function that performs a pseudo-clear of the console. Use this for testing from within
     * the IDE.
     */
    public static void clearConsoleFromIde() {
        System.out.print("\n\n\n\n\n");
    }

    /**
     * Utility function that clears the console. Does not work within the IDE console.
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the console");
        }
    }

    /**
     * Prints switched view message.
     */
    public void printSwitchedViewMessage() {
        ViewType viewType = Parser.getViewType();

        if (viewType == ViewType.PATIENT_INFO) {
            System.out.println("  ___  _ _____ ___ ___ _  _ _____ \n"
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
        }
        if (viewType == ViewType.SCHEDULE) {
            System.out.println("  ___  ___ _  _ ___ ___  _   _ _    ___ ___ \n"
                    + " / __|/ __| || | __|   \\| | | | |  | __| _ \\\n"
                    + " \\__ \\ (__| __ | _|| |) | |_| | |__| _||   /\n"
                    + " |___/\\___|_||_|___|___/_\\___/|____|___|_|_\\\n"
                    + " \\ \\ / /_ _| __\\ \\    / /                   \n"
                    + "  \\ V / | || _| \\ \\/\\/ /                    \n"
                    + "   \\_/ |___|___| \\_/\\_/                     \n"
                    + "                                            ");
        }

        System.out.println("View has been switched to " + viewType);
    }
}
