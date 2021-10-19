package medbot;

import medbot.list.PatientList;
import medbot.list.PersonList;
import medbot.utilities.ViewType;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a UI class that interacts with User
 * (Reading user input and printing message to users).
 */
public class Ui {
    public static final String VERTICAL_LINE_SPACED_ESCAPED = " \\| ";
    public static final String VERTICAL_LINE_SPACED = " | ";
    public static final String ENDLINE = System.lineSeparator();

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
        printOutput("Hello, I'm MedBot!" + ENDLINE);
    }

    /**
     * Prints a welcome message when MedBot file storage is successfully loaded.
     */
    public void printWelcomeMessageTwo() {
        printOutput("How can I help you today?" + System.lineSeparator() + ENDLINE);
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
        return "The information of patient with ID " + patientId + " has been edited to:" + ENDLINE
                + patientInfo;
    }

    /**
     * Returns the information of the filtered patients.
     *
     * @param patients the filtered patients to be printed.
     * @return The information of the filtered patients
     */
    public String getFindPatientsMessage(List<String> patients) {
        String output = "";
        for (String patient : patients) {
            output += patient.toString() + ENDLINE;
        }
        if (output.length() == 0) {
            return "No patient with such attributes is found.";
        }

        return output;
    }

    /**
     * Prints an exit message when MedBot is exiting.
     *
     * @return the exit Message
     */
    public String getExitMessage() {
        return "Thank you for using MedBot!" + ENDLINE + "See you again!";
    }

    /**
     * Prints a message when viewing the profile of a patient.
     *
     * @param patientInfo the Info of the patient to be printed.
     * @return the Patient information
     */
    public String getPatientInfo(String patientInfo) {
        return "Here's the requested patient:" + ENDLINE + patientInfo;
    }

    /**
     * Prints all patients in a list.
     *
     * @param patientList the list containing patients to be printed.
     * @return all Patients' information.
     */

    public String getAllPatientsString(PersonList patientList) {
        String output = "Here is a list of all patients:" + ENDLINE;
        output += patientList.listPersons();

        return output;
    }

    /**
     * Prints a list of all available commands.
     *
     * @return all supported commands.
     */
    public String getCommandList() {
        return "Here are the list of commands:" + ENDLINE + ENDLINE
                + "help" + ENDLINE + "add" + ENDLINE + "list" + ENDLINE + "view" + ENDLINE + "edit" + ENDLINE
                + "delete" + ENDLINE + "exit" + ENDLINE + ENDLINE
                + "To obtain more information on each command and their respective required inputs, type:" + ENDLINE
                + "help [COMMAND]" + ENDLINE + ENDLINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes";
    }

    /**
     * Prints information about list command.
     *
     * @return the information on list command.
     */
    public String getListHelpMessage() {
        return "View information of all current patients." + ENDLINE
                + "Format: list" + ENDLINE
                + "Expected Output for 2 patients: " + ENDLINE
                + "Patient ID: [PATIENT_ID_1] IC: [PATIENT_IC]"
                + "Name: [PATIENT_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + ENDLINE
                + "Patient ID: [PATIENT_ID_2] IC: [PATIENT_IC]"
                + "Name: [PATIENT_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + ENDLINE;
    }

    /**
     * Prints information about help command.
     *
     * @return the information on help command.
     */
    public String getViewHelpMessage() {
        return "View a patient’s personal information." + ENDLINE
                + "Format: view PATIENT_ID" + ENDLINE
                + "Expected Output:" + ENDLINE
                + "id: PATIENT_ID " + "name: NAME " + "phone number: PHONE_NUMBER "
                + "email: EMAIL " + "address: ADDRESS" + ENDLINE;
    }

    /**
     * Prints information about add command.
     *
     * @return the information on add command.
     */
    public String getAddHelpMessage() {
        return "Add a patient to the patient’s list." + ENDLINE
                + "Format:" + ENDLINE
                + "add i/PATIENT_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + ENDLINE
                + "Expected output:" + ENDLINE
                + "Added patient with patient ID: PATIENT_ID";
    }

    /**
     * Prints information about edit command.
     *
     * @return the information on edit command.
     */
    public String getEditHelpMessage() {
        return "Edit the personal and medical information of a patient in the list." + ENDLINE
                + "Format:" + ENDLINE
                + "edit PATIENT ID [i/PATIENT_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + ENDLINE
                + "Expected output: " + ENDLINE
                + "The information of patient with ID PATIENT_ID has been edited to:" + ENDLINE
                + "Patient ID: [PATIENT_ID] IC: [PATIENT_IC] Name: [NAME] H/P: [PHONE_NUMBER] "
                + "Email: [EMAIL] Address: [ADDRESS] " + ENDLINE;
    }

    /**
     * Prints information about delete command.
     *
     * @return the information on delete command.
     */
    public String getDeleteHelpMessage() {
        return "Delete a patient from the list." + ENDLINE
                + "Format:" + ENDLINE
                + "delete PATIENT_ID" + ENDLINE
                + "Expected Output:" + ENDLINE
                + "Patient with id PATIENT_ID deleted from system." + ENDLINE;
    }

    /**
     * Prints information about exit command.
     *
     * @return the information on exot command.
     */
    public String getExitHelpMessage() {
        return "Exits the program." + ENDLINE + "Format: exit" + ENDLINE;
    }

    /**
     * Prints unrecognised command message.
     *
     * @return the error message on unrecognised command.
     */
    public String getUnrecognisedCommandHelpMessage() {
        return "Sorry, that's not a recognised command. To view a list of commands, type:" + ENDLINE + "help" + ENDLINE;
    }

    /**
     * Utility function that performs a pseudo-clear of the console. Use this for testing from within
     * the IDE.
     */
    public static void clearConsoleFromIde() {
        System.out.print(ENDLINE + ENDLINE + ENDLINE + ENDLINE + ENDLINE);
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
        } else if (viewType == ViewType.SCHEDULER) {
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
