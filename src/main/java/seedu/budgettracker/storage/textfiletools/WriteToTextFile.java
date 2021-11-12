//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.storage.textfiletools;

import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.ui.TextUi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.budgettracker.common.Messages.MESSAGE_FILE_NOT_EXIST;

/**
 * Class responsible for saving the app data into text file.
 */
public class WriteToTextFile {
    /**
     * Save the monthlyRecordList containing all the data from the app to the data text
     * file. It deletes the existing text file first before writing to it.
     *
     * @param monthlyRecordList RecordList containing all the data from the app.
     * @param storageDirectory Directory of the data text file.
     */
    public void reloadArrayToStorage(Hashtable<Integer, RecordList> monthlyRecordList, String storageDirectory) {
        try {
            File inFile = new File(storageDirectory);

            if (!inFile.isFile()) {
                TextUi.showInvalidCommandMessage(MESSAGE_FILE_NOT_EXIST);
                return;
            }

            PrintWriter fileWrite = new PrintWriter(new FileWriter(storageDirectory));

            reloadingRecordList(monthlyRecordList, fileWrite);
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Traverses through the monthlyRecordList 12 times and convert their attribute values
     * into "add" command format.
     *
     * @param monthlyRecordList RecordList containing all the expenditure details.
     * @param fileWrite The PrintWriter that will be used to write into the text
     *                  file in the stated directory.
     */
    private void reloadingRecordList(Hashtable<Integer, RecordList> monthlyRecordList, PrintWriter fileWrite) {
        for (int i = 1; i <= 12; i++) {
            RecordList currentMonthRecordList = monthlyRecordList.get(i);
            fileWrite.println("add -b a/" + currentMonthRecordList.getBudget().getAmount() + " m/" + i);
            fileWrite.flush();

            reloadExpenditureRecordList(fileWrite, currentMonthRecordList);

            reloadLoanRecordList(fileWrite, currentMonthRecordList);
        }
    }

    /**
     * Traverse through a particular month's loan attribute values and convert them
     * into "add" command format then flush into the text file.
     *
     * @param fileWrite The PrintWriter that will be used to write into the text
     *                  file in the stated directory.
     * @param currentMonthRecordList The RecordList containing all the loan details.
     */
    private void reloadLoanRecordList(PrintWriter fileWrite, RecordList currentMonthRecordList) {
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

    /**
     * Traverse through a particular month's expenditure attribute values and convert them
     * into "add" command format then flush into the text file.
     *
     * @param fileWrite The PrintWriter that will be used to write into the text
     *                  file in the stated directory.
     * @param currentMonthRecordList RecordList containing all the expenditure details.
     */
    private void reloadExpenditureRecordList(PrintWriter fileWrite, RecordList currentMonthRecordList) {
        for (int j = 0; j < currentMonthRecordList.getExpenditureListSize(); j++) {
            ArrayList<Expenditure> expenditureRecords = currentMonthRecordList.getExpenditureRecords();
            Expenditure currentExpenditure = expenditureRecords.get(j);
            String description = currentExpenditure.getDescription();
            double amount = currentExpenditure.getAmount();
            String date = currentExpenditure.getDateString();
            String category = currentExpenditure.getCategoryString();
            fileWrite.println("add -e n/" + description + " a/" + amount + " d/" + date + " c/" + category);
            fileWrite.flush();
        }
    }
}
