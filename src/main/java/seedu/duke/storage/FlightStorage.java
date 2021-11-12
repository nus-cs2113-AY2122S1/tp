package seedu.duke.storage;

import seedu.duke.TourPlannerException;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//@@author Demonshaha
/**
 * Storage class for flights. Creates and loads up the FlightList.
 */
public class FlightStorage {
    private final FlightList flights = new FlightList();
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlannerFlights.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * Class constructor for FlightStorage.
     * Creates directory and files for storage if they do not already exist.
     *
     * @throws TourPlannerException if there are IOException thrown when creating a new directory/file
     * @see File
     * @see IOException
     */
    public FlightStorage() throws TourPlannerException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new TourPlannerException("File ERROR");
        }
    }

    /**
     * Getter for flight list.
     *
     * @return the list of flights stored in database
     */
    public FlightList getFlights() {
        return flights;
    }

    /**
     * Reads each line of the file and identifies flight fields -- flightId,
     * departure destination, return destination, departure date-time, return date-time.
     * Creates new Flight object based on the flight fields and adds to FlightList.
     *
     * @throws TourPlannerException if FileNotFoundException due to corrupted or missing file
     * @see FileNotFoundException
     */
    public void loadFile() throws TourPlannerException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            String flightId;
            String from;
            String to;
            String fromDate;
            String toDate;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.contains("Flight Details:")) {
                    line = scanner.nextLine();
                    flightId = line.substring(11);
                    line = scanner.nextLine();
                    int index = line.indexOf(", ");
                    from = line.substring(18, index);
                    fromDate = line.substring(index + 2);
                    line = scanner.nextLine();
                    index = line.indexOf(", ");
                    to = line.substring(15, index);
                    toDate = line.substring(index + 2);
                    String[] flightArray = {flightId, to, from, toDate, fromDate};
                    Flight flight = new Flight(flightArray);
                    flights.add(flight);
                }
            }
        } catch (FileNotFoundException e) {
            throw new TourPlannerException("File not found!");
        }
    }

    /**
     * Loops through flights and writes into storage file for Flights.
     */
    public void saveFile() {
        ArrayList<Flight> flightList = flights.getFlights();
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            for (Flight flight : flightList) {
                writer.write("Flight Details: \n");
                writer.write(flight.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
