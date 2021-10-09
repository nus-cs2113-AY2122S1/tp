package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

public class DeleteCommand extends Command{

    private int tripIndex = -1;
    private final String tripName;

    public DeleteCommand(String tripName) {
        this.tripName = tripName;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        for(int i=0; i<TripsList.getSize(); i++){
            Trip trip = TripsList.getTrip(i);
            if(tripName.equals(trip.getTripName())){
                tripIndex = i;
                break;
            }
        }

        if(tripIndex == -1){
            System.out.println("This trip does not exist.");
            return;
        }
        TripsList.deleteTrip(tripIndex);
        ui.printDelete(tripName);
    }
}
