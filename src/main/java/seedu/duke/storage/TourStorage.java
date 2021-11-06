package seedu.duke.storage;

import seedu.duke.TourPlannerException;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TourStorage {
    private final TourList tours = new TourList();
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlannerTours.txt");
    private static final Path dirPath = Paths.get(root, "data");


    public TourStorage() throws TourPlannerException {
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

    public TourList getTours() {
        return tours;
    }

    public void loadFile() throws TourPlannerException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            String tourName;
            String tourId;
            String tourPrice;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.contains("Tour Details:")) {
                    line = scanner.nextLine();
                    tourName = line.substring(6);
                    line = scanner.nextLine();
                    tourId = line.substring(4);
                    line = scanner.nextLine();
                    int index = line.indexOf("$");
                    tourPrice = line.substring(index + 1);
                    String[] tourArray = {tourId, tourName, tourPrice};
                    Tour tour = new Tour(tourArray);
                    tours.add(tour);
                }
            }
        } catch (FileNotFoundException e) {
            throw new TourPlannerException("File not found!");
        }
    }

    public void saveFile() {
        ArrayList<Tour> tourList = tours.getTours();
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            for (Tour tour : tourList) {
                writer.write("Tour Details: \n");
                writer.write(tour.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
