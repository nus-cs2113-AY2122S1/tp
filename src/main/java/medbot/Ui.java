package medbot;

import java.util.Scanner;

/**
 * Represents a UI class that interacts with User
 * (Reading user input and printing message to users).
 */
public class Ui {
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
    public void printWelcomeMessage() {
        printOutput("Hello, I'm MedBot!" + System.lineSeparator() + "How can I help you today?");
    }

    /**
     * Returns a message when successfully add a patient to a list.
     *
     * @param patientId the ID of the patient to be added
     * @return the Successful Message
     */
    public String getAddPatientMessage(int patientId) {
        return "Added patient with patient ID: " + patientId;
    }

    /**
     * Returns a message when successfully delete a patient from a list.
     *
     * @param patientId the ID of the patient to be deleted.
     * @return the Successful Message
     */
    public String getDeletePatientMessage(int patientId) {
        return "Patient with id " + patientId + " deleted from system.";
    }

    /**
     * Returns a message when successfully edit a patient in a list.
     *
     * @param patientId the ID of the patient to be edited.
     * @return the Successful Message
     */
    public String getEditPatientMessage(int patientId, String patientInfo) {
        return "The information of patient with ID " + patientId + " has been edited to:"
                + System.lineSeparator() + patientInfo;
    }

    /**
     * Prints an exit message when MedBot is exiting.
     * @return the exit Message
     */
    public String getExitMessage() {
        return "Thank you for using MedBot!\nSee you again!";
    }

    /**
     * Prints a message when viewing the profile of a patient.
     *
     * @param patientId the ID of the patient to be viewed.
     * @param patientInfo the Info of the patient to be printed.
     * @return the Patient information
     */
    public String getPatientInfo(int patientId, String patientInfo) {
        return "Here's the patient with id " + patientId + ": " + patientInfo;
    }

    /**
     * Prints all patients in a list.
     *
     * @param patientList the list containing patients to be printed.
     * @return all Patients' information.
     */
    public String getAllPatientsString(PatientList patientList) {
        String output = "Here is a list of all patients:\n";
        output += patientList.listPatients();

        return output;
    }
}
