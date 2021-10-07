package seedu.duke.textfiletools;

import java.io.FileReader;
import java.io.IOException;

public class PrintTextFile {
    private String fileDirectory;

    public PrintTextFile(String fileDirectory) {
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
}