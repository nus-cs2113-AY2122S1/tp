package seedu.duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    public static ArrayList<Trip> listOfTrips = new ArrayList<>();
    private static Scanner scanner;

    private static final ArrayList<String> validCommands = new ArrayList<>(
            Arrays.asList("create", "edit", "view", "summary", "delete", "expense", "quit"));

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Storage.scanner = scanner;
    }

    public static ArrayList<String> getValidCommands() {
        return validCommands;
    }
}
