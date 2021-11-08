package seedu.duke.ui;

import static seedu.duke.common.MagicValues.INDEX_OF_DIASTOLIC_PRESSURE_IN_ARRAY;
import static seedu.duke.common.MagicValues.INDEX_OF_SYSTOLIC_PRESSURE_IN_ARRAY;
import static seedu.duke.common.Messages.LOGO;
import static seedu.duke.common.Messages.MESSAGE_ADD_APPOINTMENT;
import static seedu.duke.common.Messages.MESSAGE_ADD_APPOINTMENT_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_BIRTHDAY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_BP_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_DIET_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_ELDERLY;
import static seedu.duke.common.Messages.MESSAGE_ADD_ELDERLY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_MEDHISTORY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_MEDICINE;
import static seedu.duke.common.Messages.MESSAGE_ADD_MED_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_NOK;
import static seedu.duke.common.Messages.MESSAGE_ADD_NOK_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_RECORD;
import static seedu.duke.common.Messages.MESSAGE_ADD_RECORD_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ADD_VACCINATION_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_BYE_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_CLOSEST_SEARCH_RESULT;
import static seedu.duke.common.Messages.MESSAGE_CREATION_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_DATA_STORE_SUCCESS;
import static seedu.duke.common.Messages.MESSAGE_DELETE_APPOINTMENT_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_DELETE_ELDERLY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_DELETE_ELDERLY_SUCCESSFUL;
import static seedu.duke.common.Messages.MESSAGE_DELETE_MEDHISTORY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_DELETE_MEDICAL_HISTORY;
import static seedu.duke.common.Messages.MESSAGE_DELETE_MEDICINE_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_DELETE_NOK_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_DELETION_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_ENTER_CONDITIONS;
import static seedu.duke.common.Messages.MESSAGE_ENTER_DOCTOR;
import static seedu.duke.common.Messages.MESSAGE_ENTER_HOSPITAL;
import static seedu.duke.common.Messages.MESSAGE_ENTER_NOTES_ON_CARE;
import static seedu.duke.common.Messages.MESSAGE_FILE_DOES_NOT_EXISTS;
import static seedu.duke.common.Messages.MESSAGE_FILE_EXISTS;
import static seedu.duke.common.Messages.MESSAGE_FILE_IO_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_FILE_WRONG_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_FILTER_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_FIND_BY_DIET_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_FIND_BY_MED_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_FIND_BY_NAME_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_GOODBYE;
import static seedu.duke.common.Messages.MESSAGE_HELP_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_HELP_MENU;
import static seedu.duke.common.Messages.MESSAGE_INVALID_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_DATE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_SEARCH_TERM;
import static seedu.duke.common.Messages.MESSAGE_INVALID_TIME;
import static seedu.duke.common.Messages.MESSAGE_LIST_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_LOAD_ELDERLY_ERROR;
import static seedu.duke.common.Messages.MESSAGE_LOAD_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_MISC_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_PROMPT;
import static seedu.duke.common.Messages.MESSAGE_QUERY_RESULTS_INTRO;
import static seedu.duke.common.Messages.MESSAGE_READING_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_SET_BIRTHDAY;
import static seedu.duke.common.Messages.MESSAGE_SET_BLOOD_PRESSURE;
import static seedu.duke.common.Messages.MESSAGE_SET_DIET;
import static seedu.duke.common.Messages.MESSAGE_SET_MEDICAL_HISTORY;
import static seedu.duke.common.Messages.MESSAGE_SET_VACCINATED;
import static seedu.duke.common.Messages.MESSAGE_STORE_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_APPOINTMENT_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_BIRTHDAY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_BP_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_DIET_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_MEDHISTORY_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_MED_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_NOK_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_RECORD_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_VIEW_VACCINATION_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import seedu.duke.common.Elderly;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InvalidInputException;

public class TextUi {
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Sets the default constructor for interaction with UI.
     *
     * @param in  A Scanner object.
     * @param out A PrintStream object.
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcomeMessage() {
        out.println(LOGO);
        out.println(MESSAGE_WELCOME);
    }

    /**
     * Prompts the user for input and returns the received input.
     *
     * @return Line entered by the user.
     */
    public String getUserInput() {
        out.print(MESSAGE_PROMPT);
        String input = in.nextLine();
        // Keep getting input until it is not empty
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Prints Goodbye message to user.
     */
    public void printGoodByeMessage() {
        out.println(MESSAGE_GOODBYE);
    }

    /**
     * Prints to the screen that you don't understand the commands.
     */
    public void printUnknownCommandMessage() {
        out.println(MESSAGE_INVALID_COMMAND);
    }

    /**
     * Prints to the screen asking for hospital choice.
     */
    public void printEnterHospitalMessage() {
        out.println(MESSAGE_ENTER_HOSPITAL);
    }

    /**
     * Prints to the screen asking for conditions.
     */
    public void printEnterConditionsMessage() {
        out.println(MESSAGE_ENTER_CONDITIONS);
    }

    /**
     * Prints to the screen asking for notes on care.
     */
    public void printEnterNotesOnCareMessage() {
        out.println(MESSAGE_ENTER_NOTES_ON_CARE);
    }

    /**
     * Prints to the screen asking for doctor choice.
     */
    public void printEnterDoctorMessage() {
        out.println(MESSAGE_ENTER_DOCTOR);
    }


    /**
     * Prints Add Elderly message to user.
     */
    public void printAddElderlyMessage(Elderly addedElderly) {
        out.printf(MESSAGE_ADD_ELDERLY, addedElderly.getName());
    }

    /**
     * Prints acknowledgement of appointment added to user.
     */
    public void printAddAppointmentMessage() {
        out.println(MESSAGE_ADD_APPOINTMENT);
    }

    public void printInvalidDateMessage() {
        out.println(MESSAGE_INVALID_DATE);
    }

    public void printInvalidTimeMessage() {
        out.println(MESSAGE_INVALID_TIME);
    }

    /**
     * Prints acknowledgement of medicine added to user.
     */
    public void printAddMedicineMessage() {
        out.println(MESSAGE_ADD_MEDICINE);
    }

    /**
     * Prints acknowledgement of NOK added to user.
     */
    public void printAddNokMessage() {
        out.println(MESSAGE_ADD_NOK);
    }

    /**
     * Prints acknowledgement of record added to user.
     */
    public void printAddRecordMessage() {
        out.println(MESSAGE_ADD_RECORD);
    }

    /**
     * Prints acknowledgement of Vaccination status added to user.
     */
    public void printSetVaccinationMessage(Elderly elderly) {
        out.printf(MESSAGE_SET_VACCINATED, elderly.getName());
    }

    /**
     * Prints acknowledgement of birthday added to user.
     */
    public void printSetBirthdayMessage(Elderly elderly) {
        out.printf(MESSAGE_SET_BIRTHDAY, elderly.getName(), elderly.getBirthday());
    }

    /**
     * Prints acknowledgement of blood pressure added to user.
     */
    public void printSetBloodPressureMessage(Elderly elderly) {
        out.printf(MESSAGE_SET_BLOOD_PRESSURE, elderly.getName(),
                elderly.getElderlyBloodPressure()[INDEX_OF_SYSTOLIC_PRESSURE_IN_ARRAY],
                elderly.getElderlyBloodPressure()[INDEX_OF_DIASTOLIC_PRESSURE_IN_ARRAY]);
    }

    /**
     * Prints the list of elderly given a consolidated list of elderly.
     *
     * @param elderlyListString A string containing a consolidated list of elderly.
     */
    public void printElderlyList(String elderlyListString) {
        out.println(elderlyListString);
    }

    /**
     * Prints acknowledgement of dietary preference of elderly added to user.
     */
    public void printSetDietMessage(Elderly elderly) {
        out.printf(MESSAGE_SET_DIET, elderly.getName(), elderly.getElderlyDiet());
    }

    /**
     * Prints acknowledgement of the medical history of the elderly is updated.
     */
    public void printAddMedicalHistoryMessage(Elderly elderly) {
        out.printf(MESSAGE_SET_MEDICAL_HISTORY, elderly.getName());
    }

    /**
     * Prints acknowledgement of the medical history of the elderly is updated.
     */
    public void printDeleteMedicalHistoryMessage(Elderly elderly) {
        out.printf(MESSAGE_DELETE_MEDICAL_HISTORY, elderly.getName());
    }

    /**
     * Prints the error message of the caught Duke exception.
     */
    public void printDukeException(DukeException dukeException) {
        out.println(dukeException.getMessage());
    }

    /**
     * Prints the error message of the caught invalid input exception.
     */
    public void printInvalidInputException(InvalidInputException invalidInputException) {
        out.printf("Invalid input detected %n%s%n", invalidInputException.getMessage());
    }

    /**
     * Prints the error message of the caught general exception.
     */
    public void printGeneralException(Exception exception) {
        out.println(exception.getMessage());
    }

    /**
     * Prints intro message for query results.
     *
     * @param searchTerm String containing medicine query by user.
     */
    public void printQueryResultsIntroString(String searchTerm) {
        out.printf(MESSAGE_QUERY_RESULTS_INTRO, searchTerm);
    }

    /**
     * Prints the help menu with relevant syntax.
     */
    public void printHelpMenu() {
        String creationCommands = String.format(MESSAGE_CREATION_FORMAT,
                MESSAGE_ADD_ELDERLY_FORMAT,
                MESSAGE_ADD_MED_FORMAT,
                MESSAGE_ADD_APPOINTMENT_FORMAT,
                MESSAGE_ADD_NOK_FORMAT,
                MESSAGE_ADD_RECORD_FORMAT,
                MESSAGE_ADD_MEDHISTORY_FORMAT,
                MESSAGE_ADD_BIRTHDAY_FORMAT,
                MESSAGE_ADD_VACCINATION_FORMAT,
                MESSAGE_ADD_DIET_FORMAT,
                MESSAGE_ADD_BP_FORMAT
        );

        String readCommands = String.format(MESSAGE_READING_FORMAT,
                MESSAGE_VIEW_MED_FORMAT,
                MESSAGE_VIEW_APPOINTMENT_FORMAT,
                MESSAGE_VIEW_NOK_FORMAT,
                MESSAGE_VIEW_RECORD_FORMAT,
                MESSAGE_VIEW_MEDHISTORY_FORMAT,
                MESSAGE_VIEW_BIRTHDAY_FORMAT,
                MESSAGE_VIEW_VACCINATION_FORMAT,
                MESSAGE_VIEW_DIET_FORMAT,
                MESSAGE_VIEW_BP_FORMAT
        );

        String miscCommands = String.format(MESSAGE_MISC_FORMAT,
                MESSAGE_LIST_FORMAT,
                MESSAGE_HELP_FORMAT,
                MESSAGE_BYE_FORMAT
        );

        String filterCommands = String.format(MESSAGE_FILTER_FORMAT,
                MESSAGE_FIND_BY_MED_FORMAT,
                MESSAGE_FIND_BY_DIET_FORMAT,
                MESSAGE_FIND_BY_NAME_FORMAT
        );

        String fileIoCommands = String.format(MESSAGE_FILE_IO_FORMAT,
                MESSAGE_STORE_FORMAT,
                MESSAGE_LOAD_FORMAT
        );

        String deleteCommands = String.format(MESSAGE_DELETION_FORMAT,
                MESSAGE_DELETE_ELDERLY_FORMAT,
                MESSAGE_DELETE_MEDICINE_FORMAT,
                MESSAGE_DELETE_APPOINTMENT_FORMAT,
                MESSAGE_DELETE_NOK_FORMAT,
                MESSAGE_DELETE_MEDHISTORY_FORMAT
        );

        out.println();
        out.printf(MESSAGE_HELP_MENU,
                miscCommands,
                creationCommands,
                readCommands,
                filterCommands,
                deleteCommands,
                fileIoCommands
        );
    }

    /**
     * Print the closest match to what the user query.
     *
     * @param closestMatch String containing the closest match.
     */
    public void printClosestMatch(String closestMatch) {
        out.printf(MESSAGE_CLOSEST_SEARCH_RESULT, closestMatch);
    }

    /**
     * Print that you don't know what the user is querying for.
     *
     * @param searchTerm String containing search Term.
     */
    public void printCannotFindQuery(String searchTerm) {
        out.printf(MESSAGE_INVALID_SEARCH_TERM, searchTerm);
    }

    /**
     * Print that you have deleted from the arrayList.
     *
     * @param searchTerm String containing search Term.
     */
    public void printDeleteByName(String searchTerm) {
        out.printf(MESSAGE_DELETE_ELDERLY_SUCCESSFUL, searchTerm);
    }

    /**
     * Prints exception message.
     *
     * @param e Exception that has been thrown
     */
    public void printExceptionMessage(Exception e) {
        out.println(e.getMessage());
    }

    /**
     * Prints that the given file path exists.
     *
     * @param newFilePath String containing the filepath.
     */
    public void printFileExists(String newFilePath) {
        out.printf(MESSAGE_FILE_EXISTS, newFilePath);
    }

    /**
     * Prints that the given file path does not exist.
     *
     * @param newFilePath String containing the new filepath.
     */
    public void printFileDoesNotExists(String newFilePath) {
        out.printf(MESSAGE_FILE_DOES_NOT_EXISTS, newFilePath);
    }

    /**
     * Prints that the file is in the wrong file format.
     */
    public void printWrongFileSyntax() {
        out.println(MESSAGE_FILE_WRONG_FORMAT);
    }

    /**
     * Prints that data is stored successfully.
     */
    public void printDataStoredSuccess() {
        out.println(MESSAGE_DATA_STORE_SUCCESS);
    }

    /**
     * Prints that has error parsing elderly information.
     *
     * @param elderlyName String containing elderly name.
     */
    public void printAffectedElderlyLoadIssue(String elderlyName) {
        out.printf(MESSAGE_LOAD_ELDERLY_ERROR, elderlyName);
    }
}
