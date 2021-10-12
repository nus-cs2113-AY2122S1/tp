package seedu.traveller.worldmap.exceptions;


public class FlightDataNotFoundException extends WorldMapException {
    public FlightDataNotFoundException() {
        message = "\tFlight data cannot be found, is the database file under ./flightData/flights.txt?";
    }
}
