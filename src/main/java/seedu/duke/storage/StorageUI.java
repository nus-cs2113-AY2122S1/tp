package seedu.duke.storage;

import seedu.duke.main.MainUI;

public class StorageUI {

    public static void printFileNotFound() {
        MainUI.printSingleLine();
        System.out.println("File is not found! A new file has been created");
        MainUI.printSingleLine();
    }

    public static void printFileReadException() {
        MainUI.printSingleLine();
        System.out.println("Cannot read the file");
        MainUI.printSingleLine();
    }

    public static void printFileWriteError() {
        MainUI.printSingleLine();
        System.out.println("Cannot be written in the file!");
        MainUI.printSingleLine();
    }

    public static void printOutOfBoundsException() {
        MainUI.printSingleLine();
        System.out.println("Exception out of bounds array caught");
        MainUI.printSingleLine();
    }
}
