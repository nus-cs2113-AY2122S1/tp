package seedu.duke.textfiletools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

public class DeleteFromTextFile {
    /**
     * Reads the text file and then traverse through line by line separated by "\n" and
     * copy it to a temporary text file and do not copy the line to be deleted. Lastly,
     * delete the original text file and rename that text file to the original text file
     * name.
     *
     * @param textFileDirectory     Text file's directory which we want to delete a line from.
     * @param lineNumber            The line number to be deleted.
     * @param sizeOfExpenditureList The size of the expenditureList.
     */
    public static void removeLineFromFile(String textFileDirectory, int lineNumber, int sizeOfExpenditureList) {
        try {
            File inFile = new File(textFileDirectory);
            int count = 1;
            boolean deletionAtLastLine = lineNumber == sizeOfExpenditureList;

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
