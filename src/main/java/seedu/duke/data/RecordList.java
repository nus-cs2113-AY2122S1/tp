package seedu.duke.data;


import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Expenditure;
import seedu.duke.storage.ExpenditureStorage;

<<<<<<< HEAD
//import java.time.LocalDate;
import java.time.LocalDate;
=======
>>>>>>> f005599d27bbe7f2d1f7fb66e0cd5172e44fa03f
import java.util.ArrayList;

public class RecordList {
    public static int numberOfRecords;
    private static int month;
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

    public void addBudget(double spendingLimit) {
        budget.clearAmount();
        budget.setAmount(spendingLimit);
        assert budget.getAmount() == spendingLimit;
        if (!hasBudget) {
            hasBudget = true;
        }
    }

    /**
     * Adds an expenditure record into the RecordList.
     *
<<<<<<< HEAD
     * @param description description of the expenditure
     * @param amount amount spent
     * @param date date on which the expenditure took place
=======
     * @param description      description of the expenditure
     * @param amount           amount spent
>>>>>>> f005599d27bbe7f2d1f7fb66e0cd5172e44fa03f
     * @param isLoadingStorage indicate if this command is called during loading or runtime
     */
    public void addExpenditure(String description, double amount, LocalDate date, boolean isLoadingStorage) {
        expenditureRecords.add(new Expenditure(description, amount, date));
        numberOfRecords += 1;
        if (!isLoadingStorage) {
            ExpenditureStorage storeCurrentExpenditure = new ExpenditureStorage();
            storeCurrentExpenditure.saveNewlyAddedExpenditure(description, amount, date);
        }
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
