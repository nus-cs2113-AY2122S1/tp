package seedu.traveller.worldmap.exceptions;

//@@author jach23
public class FlightDataNotFoundException extends WorldMapException {
    public FlightDataNotFoundException() {
        message = "\tFlight data cannot be found, are the database files under ./flightData?";
    }
}
