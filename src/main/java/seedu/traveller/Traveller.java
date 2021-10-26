package seedu.traveller;

import seedu.traveller.objects.TripsList;
import seedu.traveller.save.SaveLoader;
import seedu.traveller.commands.Command;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.WorldMap;

import java.util.logging.LogManager;


public class Traveller {
    private final Ui ui;
    private final TripsList tripsList;
    private static SaveLoader save;

    public Traveller() {
        ui = new Ui();
        tripsList = new TripsList();
        save = new SaveLoader(tripsList, ui);
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tripsList, ui);
                isExit = c.getExit();
            } catch (TravellerException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        Traveller traveller = new Traveller();
        WorldMap.initWorldMap();
        save.readSave();
        traveller.run();
        save.writeSave();
    }
}
