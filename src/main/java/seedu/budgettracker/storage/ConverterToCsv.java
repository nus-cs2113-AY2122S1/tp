package seedu.budgettracker.storage;

import seedu.budgettracker.data.AllRecordList;
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

public class ConverterToCsv {
    public void convertToCsvFile(AllRecordList monthlyRecordList, String csvDirectory) {
        try {
            File inFile = new File(csvDirectory);

            boolean isDeleted = inFile.delete();

            PrintWriter fileWrite = new PrintWriter(new FileWriter(csvDirectory));

            for (int i = 1; i <= 12; i++) {
                RecordList currentMonthRecordList = monthlyRecordList.getMonthRecord(i);
                fileWrite.println("BUDGET,MONTH");
                fileWrite.println(currentMonthRecordList.getBudget().getAmount() + "," + i);
                fileWrite.flush();

                fileWrite.println("DESCRIPTION,AMOUNT,DATE,CATEGORY");
                for (int j = 0; j < currentMonthRecordList.getExpenditureListSize(); j++) {
                    ArrayList<Expenditure> expenditureRecords = currentMonthRecordList.getExpenditureRecords();
                    Expenditure currentExpenditure = expenditureRecords.get(j);
                    String description = currentExpenditure.getDescription();
                    double amount = currentExpenditure.getAmount();
                    String date = currentExpenditure.getDate();
                    String category = currentExpenditure.getCategory();
                    fileWrite.println(description + "," + amount + "," + date + "," + category);
                    fileWrite.flush();
                }

                for (int k = 0; k < currentMonthRecordList.getLoanListSize(); k++) {
                    ArrayList<Loan> loanRecords = currentMonthRecordList.getLoanRecords();
                    Loan currentLoan = loanRecords.get(k);
                    String name = currentLoan.getName();
                    double amount = currentLoan.getAmount();
                    String date = currentLoan.getDate();
                    fileWrite.println(name + "," + amount + "," + date + "," + "LOAN");
                    fileWrite.flush();
                }
            }
            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
