package seedu.budgettracker.storage.textfiletools;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_IO;
import static seedu.budgettracker.common.Messages.MESSAGE_WARNING_FILE_DIRECTORY;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTextFile {
    private final String fileDirectory;

    public ReadTextFile(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Reads the text file containing all the commands for adding Budget or Expenditure
     * and return an ArrayList of String containing those commands.
     *
     * @return An ArrayList of String containing all the individual commands saved in the text file.
     *
     * @throws IOException When the FileReader fails to read the file .txt file in the
     *                     stated directory.
     */
    public ArrayList<String> readTextFileToString() throws IOException {
        ArrayList<String> arrayListOfRawCommands = new ArrayList<String>();

        File inFile = new File(fileDirectory);

        if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
            return arrayListOfRawCommands;
        }

        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory));

        String line;

        while ((line = fileReader.readLine()) != null) {
            arrayListOfRawCommands.add(line);
        }

        fileReader.close();

        return arrayListOfRawCommands;
    }
}