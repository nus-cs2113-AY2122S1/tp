package seedu.duke.data;

import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Expenditure;
import seedu.duke.data.records.Record;

import java.time.LocalDate;
import java.util.ArrayList;

public class RecordList {
    public static int numberOfRecords = 0;
    private ArrayList<Record> allRecords;

    public RecordList() {
        allRecords = new ArrayList<>();
    }

    public void addBudget(String description, double spendingLimit, int month) {
        allRecords.add(new Budget(description, spendingLimit, month));
        numberOfRecords += 1;
    }

    public void addExpenditure(String description, double spending, LocalDate date) {
        allRecords.add(new Expenditure(description, spending, date));
        numberOfRecords += 1;
    }

    public void deleteBudget(int index) {
        allRecords.remove(index);
    }


    public void deleteExpenditure(int index) {
        allRecords.remove(index);

    }

    public RecordList getExpenditureList(int startMonth, int endMonth) {
        RecordList allExpenditure = null;
        for (Record a:allRecords) {
            if (a.getType() == "Expenditure" && a.getMonth() <= endMonth && a.getMonth() >= startMonth) {
                allExpenditure.addExpenditure(a.getDescription(), a.getAmount(), a.getDate());
            }
        }
        return allExpenditure;
    }

    public int getSize() {
        return allRecords.size();
    }

    public Record getRecord(int index) {
        return allRecords.get(index);
    }

}