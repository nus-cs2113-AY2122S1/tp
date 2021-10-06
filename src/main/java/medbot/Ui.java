package medbot;

import java.util.Scanner;

/**
 * Represents a UI class that interacts with User
 * (Reading user input and printing message to users)
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

    public void printOutput(String outputMessage) {
        System.out.println(outputMessage);
    }

    public void printWelcomeMessage() {
        printOutput("Hello, I'm MedBot!" + System.lineSeparator() + "How can I help you today?");
    }

    public void printAddPatientMessage(int patientId) {
        printOutput("Added patient with patient ID: " + patientId);
    }

    public void printDeletePatientMessage(int patientId) {
        printOutput("Patient with id " + patientId + " deleted from system.");
    }

    public void printEditPatientMessage(int patientId, String patientInfo) {
        printOutput("The information of patient with ID " + patientId + " has been edited to:"
                + System.lineSeparator() + patientInfo);
    }

    public void printExitMessage() {
        printOutput("Thank you for using MedBot!");
        printOutput("See you again!");
    }

    public void printPaitentInfo(int patientId, String patientInfo) {
        printOutput("Here's the patient with id " + patientId + ": " + patientInfo);
    }

    public void printAllPatients(PatientList patientList) {
        printOutput("Here is a list of all patients:");
        patientList.listPatients();
    }
}
