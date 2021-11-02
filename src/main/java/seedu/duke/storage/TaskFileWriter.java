package seedu.duke.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class TaskFileWriter {
    public static final String LIST_STORAGE_FILE = "./data/task.txt";

    public static void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(LIST_STORAGE_FILE, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(LIST_STORAGE_FILE);
        fw.write("");
        fw.close();
    }

    public static void storetoHardDisk(ArrayList<String> taskLines) {
        try {
            clearFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (String taskLine : taskLines) {
            try {
                appendToFile(taskLine + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: \n" + e.getMessage());
            }
        }
    }
}
