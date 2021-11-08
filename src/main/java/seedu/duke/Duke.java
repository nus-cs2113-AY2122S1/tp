package seedu.duke;


import seedu.duke.common.Elderly;
import seedu.duke.list.ElderlyList;
import seedu.duke.ui.TextUi;
import seedu.duke.parser.Parser;

import javax.swing.text.html.Option;

import java.util.Optional;

import static seedu.duke.common.MagicValues.DEFAULT_FILE_PATH;
import static seedu.duke.common.MagicValues.DELETE_ELDERLY;
import static seedu.duke.common.MagicValues.FIND_BY_DIET;
import static seedu.duke.common.MagicValues.FIND_BY_MED;
import static seedu.duke.common.MagicValues.FIND_BY_NAME;
import static seedu.duke.common.MagicValues.GET_HELP_MENU;
import static seedu.duke.common.MagicValues.LIST_ELDERLY;
import static seedu.duke.common.MagicValues.LOAD_FROM_FILE;
import static seedu.duke.common.MagicValues.STORE_INTO_FILE;
import static seedu.duke.common.MagicValues.ui;
import static seedu.duke.common.MagicValues.ADD_APPOINTMENT;
import static seedu.duke.common.MagicValues.ADD_ELDERLY;
import static seedu.duke.common.MagicValues.ADD_MEDICINE;
import static seedu.duke.common.MagicValues.BYE_STRING;
import static seedu.duke.common.MagicValues.VIEW_APPOINTMENT;
import static seedu.duke.common.MagicValues.VIEW_MEDICINE;
import static seedu.duke.common.MagicValues.ADD_NOK;
import static seedu.duke.common.MagicValues.ADD_RECORD;
import static seedu.duke.common.MagicValues.VIEW_NOK;
import static seedu.duke.common.MagicValues.DELETE_NOK;
import static seedu.duke.common.MagicValues.DELETE_MEDICINE;
import static seedu.duke.common.MagicValues.DELETE_APPOINTMENT;
import static seedu.duke.common.MagicValues.VIEW_RECORD;
import static seedu.duke.common.MagicValues.VIEW_BLOOD_PRESSURE;
import static seedu.duke.common.MagicValues.SET_BLOOD_PRESSURE;
import static seedu.duke.common.MagicValues.VIEW_BIRTHDAY;
import static seedu.duke.common.MagicValues.SET_BIRTHDAY;
import static seedu.duke.common.MagicValues.SET_VACCINATED;
import static seedu.duke.common.MagicValues.VIEW_VACCINATION;
import static seedu.duke.common.MagicValues.SET_DIET;
import static seedu.duke.common.MagicValues.VIEW_DIET;
import static seedu.duke.common.MagicValues.ADD_MEDICAL_HISTORY;
import static seedu.duke.common.MagicValues.VIEW_MEDICAL_HISTORY;
import static seedu.duke.common.MagicValues.DELETE_MEDICAL_HISTORY;
import static seedu.duke.common.MagicValues.hospitalArrayList;


public class Duke {

    /**
     * Global variable used to show if loop to get user input should continue running.
     */
    private static Boolean toContinue = true;

    private ElderlyList elderlyHelperFunction;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Handles the running of the program.
     */
    private void run() {
        start();
        loopUntilByeInitiated();
    }

    /**
     * Set up objects and prints Welcome Message.
     */
    private void start() {
        ui = new TextUi();
        elderlyHelperFunction = new ElderlyList(DEFAULT_FILE_PATH);
        ui.printWelcomeMessage();
        hospitalArrayList.initHospitals();
    }

    /**
     * Continues running until bye command is issued.
     */
    private void loopUntilByeInitiated() {
        // Continue Running Loop until bye is called
        while (toContinue) {
            // Gets user input
            try {
                String userInput = ui.getUserInput();
                executeCommand(userInput);
            } catch (Exception e) {
                ui.printGeneralException(e);
            }
        }
    }

    /**
     * Stops loop and print bye message.
     */
    private void terminateInput() {
        toContinue = false;
    }

    /**
     * Executes the command based on what is given by the user.
     *
     * @param userLine Line that is inputted by the user.
     */
    private void executeCommand(String userLine) {
        String keyword = new Parser().getKeywordFromUserInput(userLine);
        Optional<Elderly> targetElderly;

        // Checks for the input for keywords
        switch (keyword.toUpperCase()) {
        case BYE_STRING:
            // Stop loop and print Goodbye
            terminateInput();
            ui.printGoodByeMessage();
            break;
        case ADD_MEDICINE:
            elderlyHelperFunction.addMedicine(userLine);
            break;
        case VIEW_MEDICINE:
            elderlyHelperFunction.viewMedicine(userLine);
            break;
        case ADD_APPOINTMENT:
            elderlyHelperFunction.addAppointment(userLine);
            break;
        case VIEW_APPOINTMENT:
            elderlyHelperFunction.viewAppointment(userLine);
            break;
        case ADD_ELDERLY:
            elderlyHelperFunction.addElderly(userLine);
            break;
        case ADD_NOK:
            elderlyHelperFunction.addNok(userLine);
            break;
        case VIEW_NOK:
            elderlyHelperFunction.viewNok(userLine);
            break;
        case ADD_RECORD:
            elderlyHelperFunction.addRecord(userLine);
            break;
        case VIEW_RECORD:
            elderlyHelperFunction.viewRecord(userLine);
            break;
        case VIEW_BLOOD_PRESSURE:
            elderlyHelperFunction.viewBloodPressure(userLine);
            break;
        case SET_BLOOD_PRESSURE:
            elderlyHelperFunction.setBloodPressure(userLine);
            break;
        case VIEW_BIRTHDAY:
            elderlyHelperFunction.viewBirthday(userLine);
            break;
        case SET_BIRTHDAY:
            targetElderly = elderlyHelperFunction.setBirthday(userLine);
            targetElderly.ifPresent(ui::printSetBirthdayMessage);
            break;
        case SET_VACCINATED:
            targetElderly = elderlyHelperFunction.setVaccinated(userLine);
            targetElderly.ifPresent(ui::printSetVaccinationMessage);
            break;
        case VIEW_VACCINATION:
            elderlyHelperFunction.getVaccinationStatus(userLine);
            break;
        case LIST_ELDERLY:
            elderlyHelperFunction.printElderly();
            break;
        case SET_DIET:
            targetElderly = elderlyHelperFunction.setDietaryPreference(userLine);
            targetElderly.ifPresent(ui::printSetDietMessage);
            break;
        case VIEW_DIET:
            elderlyHelperFunction.viewDietaryPreference(userLine);
            break;
        case FIND_BY_MED:
            elderlyHelperFunction.getElderlyGivenMedicine(userLine);
            break;
        case FIND_BY_DIET:
            elderlyHelperFunction.getElderlyGivenDiet(userLine);
            break;
        case FIND_BY_NAME:
            elderlyHelperFunction.getAllElderlyDetailsByName(userLine);
            break;
        case DELETE_ELDERLY:
            elderlyHelperFunction.deleteElderlyByUsername(userLine);
            break;
        case DELETE_MEDICINE:
            elderlyHelperFunction.deleteMedicine(userLine);
            break;
        case GET_HELP_MENU:
            ui.printHelpMenu();
            break;
        case STORE_INTO_FILE:
            elderlyHelperFunction.storeFromFilePath(userLine);
            break;
        case LOAD_FROM_FILE:
            elderlyHelperFunction.loadFromFilePath(userLine);
            break;
        case DELETE_NOK:
            elderlyHelperFunction.deleteNextOfKin(userLine);
            break;
        case DELETE_APPOINTMENT:
            elderlyHelperFunction.deleteAppointment(userLine);
            break;
        case ADD_MEDICAL_HISTORY:
            targetElderly = elderlyHelperFunction.addMedicalHistory(userLine);
            targetElderly.ifPresent(ui::printAddMedicalHistoryMessage);
            break;
        case VIEW_MEDICAL_HISTORY:
            elderlyHelperFunction.viewMedicalHistory(userLine);
            break;
        case DELETE_MEDICAL_HISTORY:
            targetElderly = elderlyHelperFunction.deleteMedicalHistory(userLine);
            targetElderly.ifPresent(ui::printDeleteMedicalHistoryMessage);
            break;
        default:
            // Command is not recognized
            // assert keyword.equals("a") : "Nothing has been input";
            ui.printUnknownCommandMessage();
            break;
        }

    }

}
