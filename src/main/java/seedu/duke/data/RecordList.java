package seedu.duke.data;


import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Expenditure;
import seedu.duke.storage.Storage;


import java.time.LocalDate;

import java.util.ArrayList;

public class RecordList {
    public static int numberOfRecords;
    private final Budget budget;
    private final ArrayList<Expenditure> expenditureRecords;
    private boolean hasBudget;

    /**
     * Constructor for MonthlyRecordList for v1.0
     * Kept for normal operation, should be removed by v2.0
     */
    public RecordList() {
        numberOfRecords = 0;
        budget = new Budget(0.00, LocalDate.now().getMonthValue());
        hasBudget = false;
        expenditureRecords = new ArrayList<>();
    }

    public void addBudget(double spendingLimit, int month, boolean isLoadingStorage) {
        budget.clearAmount();
        budget.setAmount(spendingLimit);
        assert budget.getAmount() == spendingLimit;
        if (!hasBudget) {
            hasBudget = true;
        }
        /*
        if (!isLoadingStorage) {
            Storage storeCurrentBudget = new Storage();
            storeCurrentBudget.saveNewlyAddedBudget(spendingLimit, month);
        }
        */
    }

    /**
     * Adds an expenditure record into the RecordList.
     *
     * @param description description of the expenditure
     * @param amount amount spent
     * @param date date on which the expenditure took place
     * @param isLoadingStorage indicate if this command is called during loading or runtime
     */
    public void addExpenditure(String description, double amount, LocalDate date, boolean isLoadingStorage) {
        expenditureRecords.add(new Expenditure(description, amount, date));
        numberOfRecords += 1;
        /*
        if (!isLoadingStorage) {
            Storage storeCurrentExpenditure = new Storage();
            storeCurrentExpenditure.saveNewlyAddedExpenditure(description, amount, date);
        }
        */
        assert getExpenditureListSize() == numberOfRecords;
    }

    public void deleteBudget() {
        budget.clearAmount();
        assert budget.getAmount() == 0.00;
        hasBudget = false;
    }

    public void deleteExpenditure(int index) {
        expenditureRecords.remove(index - 1);
        numberOfRecords -= 1;
        assert getExpenditureListSize() == numberOfRecords;
    }

    public ArrayList<Expenditure> getExpenditureRecords() {
        return expenditureRecords;
    }

    public Budget getBudget() {
        return budget;
    }

    public int getExpenditureListSize() {
        return expenditureRecords.size();
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
    public Expenditure getExpenditure(int index) {
        return expenditureRecords.get(index);
    }

    public int getSize() {
        return expenditureRecords.size();
    }

    public void printRecord(int i) {
    }

    public void addBudgetList(String description, double spendingLimit, int month) {
    }
}
