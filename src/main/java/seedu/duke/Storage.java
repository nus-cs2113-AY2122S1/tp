package seedu.duke;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {

    private static final String FILE_PATH = "trips.json";
    private static ArrayList<Trip> listOfTrips = new ArrayList<>();
    private static Trip openTrip = null;
    private static Trip lastTrip = null;
    private static Expense lastExpense = null;

    private static Scanner scanner;
    private static Logger logger;

    private static final ArrayList<String> validCommands = new ArrayList<>(
            Arrays.asList("create", "edit", "view", "open", "list", "summary",
                    "delete", "expense", "quit", "help", "amount"));


    protected static void writeToFile() throws IOException {
        String jsonString = new Gson().toJson(listOfTrips);
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(jsonString);
        fileWriter.close();
    }

    protected static void readFromFile() {
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            String jsonString = scanner.nextLine();
            Type tripType = new TypeToken<ArrayList<Trip>>(){}.getType();
            listOfTrips = new Gson().fromJson(jsonString, tripType);
        } catch (JsonParseException e) {
            Ui.printJsonParseError();
            askOverwriteOrClose();
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        }
    }

    private static void askOverwriteOrClose() {
        while (true) {
            Ui.printJsonParseUserInputPrompt();
            String input = scanner.nextLine().strip();
            if (input.contains("n")) {
                Storage.getLogger().log(Level.WARNING, "JSON Parse failed, user requests program end");
                System.exit(1);
                return;
            } else if (input.contains("y")) {
                try {
                    FileWriter fileWriter = new FileWriter(FILE_PATH);
                    fileWriter.close();
                } catch (IOException e) {
                    Ui.printCreateFileFailure();
                    System.exit(1);
                }
                return;
            }
        }
    }


    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Storage.scanner = scanner;
    }

    public static ArrayList<String> getValidCommands() {
        return validCommands;
    }


    public static Trip getOpenTrip() {
        if (openTrip == null) {
            Ui.printNoOpenTripError();
            promptUserForValidTrip();
        }
        return openTrip;
    }

    private static void promptUserForValidTrip() {
        try {
            int tripIndex = Integer.parseInt(scanner.nextLine().strip()) - 1;
            setOpenTrip(listOfTrips.get(tripIndex));
        } catch (NumberFormatException e) {
            Ui.argNotNumber();
        }
    }

    public static void setOpenTripAsLastTrip() {
        lastTrip = openTrip;
    }

    /**
     * Checks if there is an open trip or not.
     *
     * @return true if there is an open trip
     */
    public static boolean checkOpenTrip() {
        return openTrip != null;
    }

    public static void setOpenTrip(Trip openTrip) {
        Storage.openTrip = openTrip;
    }

    public static void closeTrip() {
        Storage.openTrip = null;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        Storage.logger = logger;
    }

    public static ArrayList<Trip> getListOfTrips() {
        return listOfTrips;
    }

    public static Trip getLastTrip() {
        return lastTrip;
    }

    public static void setLastTrip(Trip lastTrip) {
        Storage.lastTrip = lastTrip;
    }

    public static Expense getLastExpense() {
        return lastExpense;
    }

    public static void setLastExpense(Expense lastExpense) {
        Storage.lastExpense = lastExpense;
    }


}
