package seedu.duke.textfiletools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReadTextFile {
    private String fileDirectory;

    public ReadTextFile(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public void printText() {
        try {
            FileReader readTextFile = new FileReader(this.fileDirectory);

            int singleCharacters;

            while ((singleCharacters = readTextFile.read()) != -1) {
                System.out.print((char) singleCharacters);
            }
            readTextFile.close();
        } catch (IOException except) {
            System.out.println("IO EXCEPTION!");
            System.out.println("Ensure that you have files in correct directory and named correctly!");
            except.printStackTrace();
        }

        System.out.println("");
    }

    public ArrayList<String> readTextFileToString() throws IOException {
        ArrayList<String> arrayListOfRawCommands = new ArrayList<String>();
        //---------------------------------------------------------
        // Change to exception...
        File inFile = new File(fileDirectory);

        if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
        }
        //---------------------------------------------------------

        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory));

        String line;

        while ((line = fileReader.readLine()) != null) {
            arrayListOfRawCommands.add(line);
        }

        fileReader.close();

        return arrayListOfRawCommands;
    }
}