package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.EmptyVertexException;
import seedu.traveller.mapper.GraphList;
import seedu.traveller.mapper.Vertex;

public class SearchCommand extends Command {
    private final String startCountry;
    private final String endCountry;

    public SearchCommand(String startCountry, String endCountry) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException, EmptyVertexException {
        Vertex from;
        Vertex to;
        from = GraphList.findVertex(startCountry);
        to = GraphList.findVertex(endCountry);
        System.out.println("From : " + from + " To : " + to
                + " is " + GraphList.edgeMatrix[from.key()][to.key()]);
    }

}
