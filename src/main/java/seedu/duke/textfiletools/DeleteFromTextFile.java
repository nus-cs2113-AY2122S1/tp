package seedu.duke.textfiletools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteFromTextFile {
    public static void removeLineFromFile(String textFileDirectory, int lineNumber) {
        try {
            File inFile = new File(textFileDirectory);
            int count = 1;

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader bufferRead = new BufferedReader(new FileReader(textFileDirectory));
            PrintWriter fileWrite = new PrintWriter(new FileWriter(tempFile));

            String line = "";

            while ((line = bufferRead.readLine()) != null) {
                if (count != lineNumber) {
                    fileWrite.println(line); // No newline...
                    fileWrite.flush();
                }
                count += 1;
            }

            assert count >= lineNumber;

            fileWrite.close();
            bufferRead.close();
            // Put into exception...
            //----------------------------------------------------
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }
            //----------------------------------------------------
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
