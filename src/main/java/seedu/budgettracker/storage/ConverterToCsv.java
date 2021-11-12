//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.storage;

import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.ui.TextUi;

import javax.swing.plaf.TextUI;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Converts a particular data base into .csv file.
 */
public class ConverterToCsv {
    /**
     * This method firstly deletes the csv file if it exists and then create a new
     * ".csv" file and loads the current app data into it.
     *
     * @param monthlyRecordList AllRecordList object containing the app data.
     * @param csvDirectory Directory to create the ".csv" file.
     */
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

                writeExpensidureCsv(fileWrite, currentMonthRecordList);
                writeLoanCv(fileWrite, currentMonthRecordList);
            }
            fileWrite.close();

            TextUi.csvStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLoanCv(PrintWriter fileWrite, RecordList currentMonthRecordList) {
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

    private void writeExpensidureCsv(PrintWriter fileWrite, RecordList currentMonthRecordList) {
        for (int j = 0; j < currentMonthRecordList.getExpenditureListSize(); j++) {
            ArrayList<Expenditure> expenditureRecords = currentMonthRecordList.getExpenditureRecords();
            Expenditure currentExpenditure = expenditureRecords.get(j);
            String description = currentExpenditure.getDescription();
            double amount = currentExpenditure.getAmount();
            String date = currentExpenditure.getDateString();
            String category = currentExpenditure.getCategoryString();
            fileWrite.println(description + "," + amount + "," + date + "," + category);
            fileWrite.flush();
        }
    }
}
