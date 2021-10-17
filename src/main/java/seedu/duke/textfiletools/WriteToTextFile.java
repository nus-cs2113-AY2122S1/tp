package seedu.duke.textfiletools;

import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteToTextFile {
    /**
     * Writes the adding Budget and Expenditure command to the end of text file.
     *
     * @param textToWrite      The raw command string from the user.
     * @param storageDirectory Directory of the text file which the command is saved to.
     */
    public static void writeToStorage(String textToWrite, String storageDirectory) {
        try {
            File textFileToWrite = new File(storageDirectory);
            boolean fileIsEmpty = textFileToWrite.length() == 0;

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

    public void reloadArrayToStorage(ArrayList<RecordList> monthlyRecordList, String storageDirectory) {
        try {
            File inFile = new File(storageDirectory);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            String line = "";
            PrintWriter fileWrite = new PrintWriter(new FileWriter(storageDirectory));

            for (int i = 0; i <= 12; i += 1) {
                fileWrite.println("add b/ a/" + monthlyRecordList.get(i).getBudget().getRawValue() + " d/" + i);
                fileWrite.flush();
                for (int j = 0; j < monthlyRecordList.get(i).getExpenditureListSize(); j += 1) {
                    String description = monthlyRecordList.get(i).getExpenditureRecords().get(j).getDescription();
                    fileWrite.print("add e/" + description);
                    Double amount = monthlyRecordList.get(i).getExpenditureRecords().get(j).getAmount();
                    fileWrite.print(" a/" + amount);
                    String date = monthlyRecordList.get(i).getExpenditureRecords().get(j).getDate();
                    fileWrite.println(" d/" + date);
                    fileWrite.flush();
                }
            }
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
