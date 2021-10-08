package seedu.duke;

import java.util.Scanner;

public class Storage {

    private static Scanner scanner;

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Storage.scanner = scanner;
    }
}
