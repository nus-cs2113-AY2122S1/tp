package seedu.duke.storage;

import seedu.duke.main.MainUI;

public class StorageUI {

    public static void printFileNotFound() {
        MainUI.printSingleLine();
        System.out.println("File is not found! A new file has been created");
        MainUI.printSingleLine();
    }

    public static void printFileReadException() {
        System.out.println("Cannot read the file");
    }

    public static void printFileWriteError() {
        System.out.println("Cannot be written in the file!");
    }

    public static void printOutOfBoundsException() {
        System.out.println("Exception out of bounds array caught");
    }
}
