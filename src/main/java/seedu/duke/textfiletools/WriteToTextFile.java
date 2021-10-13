package seedu.duke.textfiletools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTextFile {
    public static void writeToStorage(String textToWrite, String storageDirectory) {
        try {
            File textFileToWrite = new File(storageDirectory);
            boolean fileIsEmpty = false;

            if (textFileToWrite.length() == 0) {
                fileIsEmpty = true;
            }

            FileWriter writeLineToFile = new FileWriter(storageDirectory, true);

            if (fileIsEmpty == false) {
                writeLineToFile.write("\n");
            }

            writeLineToFile.write(textToWrite);
            writeLineToFile.close();
        } catch (IOException ioe) { // Exception to be added.
            System.out.println("IOException: " + ioe.getMessage());
        }
    }
}
