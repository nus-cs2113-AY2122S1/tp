package seedu.traveller.save;

import seedu.traveller.Parser;
import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.commands.Command;
import seedu.traveller.exceptions.SaveDataNotFoundException;
import seedu.traveller.exceptions.TravellerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveLoader {
    //private static final Logger logger = Logger.getLogger(DataLoader.class.getName());
    private final String filePath = "./save/save.txt";
    private final String separator = " ";
    private final String newCommand = "new";
    private final Ui ui;
    private final TripsList tripsList;

    public SaveLoader(TripsList tripsList, Ui ui) {
        this.ui = ui;
        this.tripsList = tripsList;
    }

    public void readSave() {
        if (hasSave()) {
            try {
                readFromSave();
            } catch (SaveDataNotFoundException e) {
                ui.printError(e.getMessage());
            }
            deleteSave();
        }
        ui.printLine();
    }

    public void writeSave() {
        try {
            createSave();
            writeToSave();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        ui.printLine();
    }

    private boolean hasSave() {
        File save = new File(filePath);
        return save.exists();
    }

    private void readFromSave() throws SaveDataNotFoundException {
        /*logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Reading data from " + FILE_PATH);*/
        File data = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(data);
        } catch (FileNotFoundException e) {
            //logger.log(Level.WARNING, "Save data cannot be found.");
            throw new SaveDataNotFoundException();
        }
        String input;
        ui.printReadSave();
        while (scanner.hasNext()) {
            try {
                input = scanner.nextLine();
                Command c = Parser.parse(input);
                c.execute(tripsList, ui);
            } catch (TravellerException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    private void deleteSave() {
        File save = new File(filePath);
        save.delete();
    }

    private void writeToSave() throws IOException {
        /*logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Writing data to " + FILE_PATH);*/
        FileWriter fw = new FileWriter(filePath);
        ui.printWriteSave();
        ui.printLine();
        for (int i = 0; i < tripsList.getSize(); i++) {
            Trip current = tripsList.getTrip(i);
            fw.write(newCommand + separator
                    + current.getTripName() + separator
                    + current.getStartCountryCode() + separator
                    + current.getEndCountryCode() + "\n");
        }
        fw.close();
    }

    private void createSave() throws IOException {
        File save = new File(filePath);
        save.createNewFile();
    }
}
