package seedu.duke.textfiletools;

import seedu.duke.data.RecordList;
import seedu.duke.data.records.Expenditure;
import seedu.duke.data.records.Loan;
import seedu.duke.ui.TextUi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.common.Messages.MESSAGE_FILE_NOT_EXIST;
import static seedu.duke.common.Messages.MESSAGE_INVALID_IO;

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
        } catch (IOException ioe) {
            System.out.println(MESSAGE_INVALID_IO + ioe.getMessage());
        }
    }

    public void reloadArrayToStorage(Hashtable<Integer, RecordList> monthlyRecordList, String storageDirectory) {
        try {
            File inFile = new File(storageDirectory);

            if (!inFile.isFile()) {
                TextUi.showInvalidCommandMessage(MESSAGE_FILE_NOT_EXIST);
                return;
            }

            String line = "";
            PrintWriter fileWrite = new PrintWriter(new FileWriter(storageDirectory));

            for (int i = 1; i <= 12; i++) {
                RecordList currentMonthRecordList = monthlyRecordList.get(i);
                fileWrite.println("add -b a/" + currentMonthRecordList.getBudget().getRawValue() + " m/" + i);
                fileWrite.flush();
                for (int j = 0; j < currentMonthRecordList.getExpenditureListSize(); j++) {
                    ArrayList<Expenditure> expenditureRecords = currentMonthRecordList.getExpenditureRecords();
                    Expenditure currentExpenditure = expenditureRecords.get(j);
                    String description = currentExpenditure.getDescription();
                    double amount = currentExpenditure.getAmount();
                    String date = currentExpenditure.getDate();
                    String category = currentExpenditure.getCategory();
                    fileWrite.println("add -e n/" + description + " a/" + amount + " d/" + date + " c/" + category);
                    fileWrite.flush();
                }

                for (int k = 0; k < currentMonthRecordList.getLoanListSize(); k++) {
                    ArrayList<Loan> loanRecords = currentMonthRecordList.getLoanRecords();
                    Loan currentLoan = loanRecords.get(k);
                    String name = currentLoan.getName();
                    double amount = currentLoan.getAmount();
                    String date = currentLoan.getDate();
                    fileWrite.println("add -l n/" + name + " a/" + amount + " d/" + date);
                    fileWrite.flush();
                }
            }
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToCsvFile() {

    }
}
