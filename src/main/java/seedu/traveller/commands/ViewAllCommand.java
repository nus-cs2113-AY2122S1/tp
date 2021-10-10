package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.GraphList;
import seedu.traveller.mapper.Vertex;


public class ViewAllCommand extends Command {
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        for (int i = 0; i < tripsList.getSize(); i++) {
            Trip trip = tripsList.getTrip(i);
            Vertex from;
            Vertex to;
            from = GraphList.findVertex(trip.);
            to = GraphList.findVertex(endCountry);
            System.out.println("From : " + from + " To : " + to
                    + " is " + GraphList.edgeMatrix[from.key()][to.key()]);
        }
    }
}
