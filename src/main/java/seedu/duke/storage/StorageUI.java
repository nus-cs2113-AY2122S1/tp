package seedu.duke.storage;

import seedu.duke.main.MainUI;

public class StorageUI {

    /**
     * Prints a message to the user whenever the file cannot been found and create a new file.
     */
    public static void printFileNotFound() {
        MainUI.printSingleLine();
        System.out.println("File is not found! A new file has been created");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever the file cannot been read.
     */
    public static void printFileReadException() {
        MainUI.printSingleLine();
        System.out.println("Cannot read the file");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever the file cannot be written.
     */
    public static void printFileWriteError() {
        MainUI.printSingleLine();
        System.out.println("Cannot be written in the file!");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever the item is out of range.
     */
    public static void printOutOfBoundsException() {
        MainUI.printSingleLine();
        System.out.println("Exception out of bounds array caught");
        MainUI.printSingleLine();
    }
}
