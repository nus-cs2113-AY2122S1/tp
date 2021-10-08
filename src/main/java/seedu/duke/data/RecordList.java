package seedu.duke.data;

import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Expenditure;
import seedu.duke.data.records.Record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordList {
    public static int numberOfRecords = 0;
    private final List<Record>[] allRecords;

    /**
     * Constructor that creates a List of 13 ArrayLists, with each index representing the months.
     * 0: empty, 1: represents January, 2: represents February etc.
     */
    public RecordList() {
        allRecords = new List[13];
        Arrays.setAll(allRecords, element -> new ArrayList<>());
    }

    public void addBudget(double spendingLimit, int month) {
        allRecords[month].add(new Budget(spendingLimit, month));
        numberOfRecords += 1;
    }

    public void addExpenditure(String description, double spending, LocalDate date) {
        allRecords[date.getMonthValue()].add(new Expenditure(description, spending, date));
        numberOfRecords += 1;
    }

    public void deleteBudget(int month, int index) {
        allRecords[month].remove(index - 1);
        numberOfRecords -= 1;
    }

    public void deleteExpenditure(int month, int index) {
        allRecords[month].remove(index - 1);
        numberOfRecords -= 1;
    }

    public List<Record>[] getAllRecords() {
        return allRecords;
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < 13; i++) {
            size += allRecords[i].size();
        }
        return size;
    }

    //    public RecordList getExpenditureList(int startMonth, int endMonth) {
    //        RecordList allExpenditure = null;
    //        for (Record a : allRecords) {
    //            if (a.getType().equals("Expenditure") && a.getMonth() <= endMonth && a.getMonth() >= startMonth) {
    //                allExpenditure.addExpenditure(a.getDescription(), a.getAmount(), a.getDate());
    //            }
    //        }
    //        return allExpenditure;
    //    }
    public Record getRecord(int month, int index) {
        return allRecords[month].get(index);
    }

    public void printRecord(int i) {
    }

    public void addBudgetList(String description, double spendingLimit, int month) {
    }
}
