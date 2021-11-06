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

public class WriteToTextFile {
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
                fileWrite.println("add -b a/" + currentMonthRecordList.getBudget().getAmount() + " m/" + i);
                fileWrite.flush();
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
}
