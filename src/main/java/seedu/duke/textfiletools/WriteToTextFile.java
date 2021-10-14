package seedu.duke.textfiletools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToTextFile {
    /**
     * Writes the adding Budget and Expenditure command to the text file.
     *
     * @param textToWrite The raw command string from the user.
     * @param storageDirectory Directory of the text file which the command is saved to.
     */
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
