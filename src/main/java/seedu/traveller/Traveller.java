package seedu.traveller;

import seedu.traveller.commands.Command;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.WorldMap;


public class Traveller {
    private final Ui ui;
    private final TripsList tripsList;

    public Traveller() {
        ui = new Ui();
        tripsList = new TripsList();
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
        Traveller traveller = new Traveller();
        WorldMap.initWorldMap();
        traveller.run();
    }
}
