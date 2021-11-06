package seedu.traveller.save;

import seedu.traveller.Parser;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.commands.Command;
import seedu.traveller.exceptions.SaveDataNotFoundException;
import seedu.traveller.exceptions.TravellerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author gavienwz
/**
 * This class implements a save functionality for the Traveller program.
 * A text file is created (if it doesn't exist) and written to whenever the program opens/closes.
 * It saves all existing trip objects and their corresponding days/items.
 */
public class SaveLoader {
    private static final Logger logger = Logger.getLogger(SaveLoader.class.getName());
    private final String filePath = "./save/save.txt";
    private final String directoryPath = "./save";
    private final Ui ui;
    private final TripsList tripsList;

    public SaveLoader(TripsList tripsList, Ui ui) {
        this.ui = ui;
        this.tripsList = tripsList;
    }

    /**
     * The main function of reading the save file.
     * It is called at the start when Traveller opens.
     */
    public void readSave() {
        if (!hasDirectory()) {
            createDir();
        }
        if (hasSave()) {
            try {
                readFromSave();
            } catch (SaveDataNotFoundException e) {
                ui.printError(e.getMessage());
            }
        } else {
            assert !hasSave();
            try {
                createSave();
            } catch (IOException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printLine();
    }

    /**
     * The main function of writing the save file.
     * It is called at the end when Traveller closes and when any input is given.
     * @param status Status can either be "run" or "exit" as called for in Traveller main() and run(),
     *     so that it will only print to the command line when exiting.
     */
    public void writeSave(String status) {
        try {
            writeToSave();
        } catch (IOException | TravellerException e) {
            ui.printError(e.getMessage());
        }
        if (status.equals("exit")) {
            ui.printWriteSave();
            ui.printLine();
        }
    }

    /**
     * A check if the save text file exists.
     * @return boolean Returns whether save.txt file exists.
     */
    private boolean hasSave() {
        File save = new File(filePath);
        return save.exists();
    }

    /**
     * A check if the save directory exists.
     * @return boolean Returns where save folder exists.
     */
    private boolean hasDirectory() {
        File dir = new File(directoryPath);
        return dir.exists();
    }

    /**
     * This function reads from the save.txt file.
     * Each line is a command to create a trip object or the corresponding day/item objects,
     * to replicate the previous state before Traveller was closed.
     * When a line is an invalid command, it will print an error message and disregard that line.
     * @throws SaveDataNotFoundException The save file doesn't exist.
     */
    private void readFromSave() throws SaveDataNotFoundException {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Reading data from " + filePath);
        File data = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(data);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Save data cannot be found.");
            throw new SaveDataNotFoundException();
        }
        ui.printReadSave();
        int lineNumber = 1;
        while (scanner.hasNext()) {
            executeSaveData(scanner, lineNumber);
            lineNumber++;
        }
    }

    /**
     * This function is similar to the run function in traveller, handling the executing of the save commands.
     * @param scanner Scanner object to read from the save text file.
     * @param lineNumber Line number to indicate where an error occurs.
     */
    private void executeSaveData(Scanner scanner, int lineNumber) {
        String input;
        try {
            input = scanner.nextLine();
            Command c = Parser.parse(input);
            c.execute(tripsList, ui);
        } catch (TravellerException e) {
            ui.printReadSaveError(lineNumber);
        }
    }

    /**
     * This function writes to save.txt the minimal number of commands to recreate the trip objects
     * and their corresponding days/list.
     * Each line written is a command in the format of commands that is written by the user.
     * @throws IOException If an I/O error occurs due to file writer class.
     * @throws TravellerException If an error occurs in referencing the current trip.
     */
    private void writeToSave() throws IOException, TravellerException {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Writing data to " + filePath);
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tripsList.getSize(); i++) {
            Trip current = tripsList.getTrip(i);
            fw.write(current.getSaveTrip());
            fw.write(current.getSaveDay());
            fw.write(current.getSaveItem());
        }
        fw.close();
    }

    /**
     * Creates save.txt. Is called if the save text file doesn't exist.
     * @throws IOException If an I/O error occurs due to file class.
     */
    private void createSave() throws IOException {
        File save = new File(filePath);
        save.createNewFile();
    }

    /**
     * Creates a directory. Is called if the save directory doesn't exist.
     */
    private void createDir() {
        File dir = new File(directoryPath);
        dir.mkdir();
    }
}
