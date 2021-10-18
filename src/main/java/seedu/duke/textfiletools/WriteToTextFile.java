package seedu.duke.textfiletools;

import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;
import seedu.duke.data.records.Expenditure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

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

            if (!fileIsEmpty) {
                writeLineToFile.write("\n");
            }

            writeLineToFile.write(textToWrite);
            writeLineToFile.close();
        } catch (IOException ioe) { // Exception to be added.
            System.out.println("IOException: " + ioe.getMessage());
        }
    }

    public void reloadArrayToStorage(Hashtable<Integer, RecordList> monthlyRecordList, String storageDirectory) {
        try {
            File inFile = new File(storageDirectory);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            String line = "";
            PrintWriter fileWrite = new PrintWriter(new FileWriter(storageDirectory));

            for (int i = 1; i <= 12; i++) {
                RecordList currentMonthRecordList = monthlyRecordList.get(i);
                fileWrite.println("add b/ a/" + currentMonthRecordList.getBudget().getRawValue() + " m/" + i);
                fileWrite.flush();
                for (int j = 0; j < currentMonthRecordList.getExpenditureListSize(); j++) {
                    ArrayList<Expenditure> expenditureRecords = currentMonthRecordList.getExpenditureRecords();
                    Expenditure currentExpenditure = expenditureRecords.get(j);
                    String description = currentExpenditure.getDescription();
                    double amount = currentExpenditure.getAmount();
                    String date = currentExpenditure.getDate();
                    fileWrite.println("add e/" + description + " a/" + amount + " d/" + date);
                    fileWrite.flush();
                }
            }
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
