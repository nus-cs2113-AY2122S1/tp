package seedu.budgettracker.storage.textfiletools;

//@@author YEOWEIHNGWHYELAB

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

//@@author yeoweihngwhyelab
public class WriteToTextFile {
    //@@author yeoweihngwhyelab
    public void reloadArrayToStorage(Hashtable<Integer, RecordList> monthlyRecordList, String storageDirectory) {
        try {
            File inFile = new File(storageDirectory);

            if (!inFile.isFile()) {
                TextUi.showInvalidCommandMessage(MESSAGE_FILE_NOT_EXIST);
                return;
            }

            String line = "";
            PrintWriter fileWrite = new PrintWriter(new FileWriter(storageDirectory));

            reloadingRecordList(monthlyRecordList, fileWrite);
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@@author yeoweihngwhyelab
    private void reloadingRecordList(Hashtable<Integer, RecordList> monthlyRecordList, PrintWriter fileWrite) {
        for (int i = 1; i <= 12; i++) {
            RecordList currentMonthRecordList = monthlyRecordList.get(i);
            fileWrite.println("add -b a/" + currentMonthRecordList.getBudget().getAmount() + " m/" + i);
            fileWrite.flush();
            reloadExpenditureRecordList(fileWrite, currentMonthRecordList);

            reloadLoanRecordList(fileWrite, currentMonthRecordList);
        }
    }

    //@@author yeoweihngwhyelab
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

    //@@author yeoweihngwhyelab
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
