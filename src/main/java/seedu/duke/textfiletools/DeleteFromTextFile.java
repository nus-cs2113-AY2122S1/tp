package seedu.duke.textfiletools;

import java.util.logging.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeleteFromTextFile {

    public static void removeLineFromFile(String textFileDirectory, int lineNumber, int sizeOfExpenditureList) {
        try {
            File inFile = new File(textFileDirectory);
            int count = 1;
            boolean deletionAtLastLine = false;

            if (lineNumber == sizeOfExpenditureList) {
                deletionAtLastLine = true;
            }

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            File budgetStorageFile = new File("./data/BudgetList1.txt");
            Scanner scanText = new Scanner(budgetStorageFile); // create a Scanner using the File as the source
            String line = "";

            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            PrintWriter fileWrite = new PrintWriter(new FileWriter(tempFile));

            while (scanText.hasNext()) {
                line = scanText.nextLine();

                if (count != lineNumber && count != sizeOfExpenditureList) {
                    fileWrite.println(line);
                    fileWrite.flush();
                } else if (count == sizeOfExpenditureList && !(deletionAtLastLine)) {
                    fileWrite.print(line);
                    fileWrite.flush();
                }

                count += 1;
            }

            

            assert count >= lineNumber;

            fileWrite.close();
            scanText.close();
            // Put into exception...
            //----------------------------------------------------

            Logger logger = Logger.getLogger("deletion");
            // logger.log(Level.INFO, "Deletion Or Renaming failed!");
            // logger.setLevel(Level.INFO);

            if (!inFile.delete()) {
                logger.info("Deletion Or Renaming failed!");
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(inFile)) {
                logger.info("Deletion Or Renaming failed!");
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
