package seedu.duke.textfiletools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeleteFromTextFile {
    public static void removeLineFromFile(String textFileDirectory, int lineNumber) {
        System.out.println(lineNumber);

        try {
            File inFile = new File(textFileDirectory);
            int count = 1;

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
                if (count != lineNumber && scanText.hasNext() && (count + 1 != lineNumber)) {
                    fileWrite.println(line);
                    fileWrite.flush();
                } else if ((count != lineNumber && !(scanText.hasNext()))) {
                    fileWrite.print(line);
                    fileWrite.flush();
                } else if ((count + 1) == lineNumber) {
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
