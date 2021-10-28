package seedu.budgettracker.data;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.data.records.Record;

import java.time.LocalDate;
import java.util.ArrayList;

public class RecordList {
    public static int numberOfRecords;
    private final Budget budget;
    private final ArrayList<Expenditure> expenditureRecords;
    private final ArrayList<Loan> loanRecords;
    private boolean hasBudget;

    /**
     * Constructor for MonthlyRecordList.
     */
    public RecordList(int month) {
        numberOfRecords = 0;
        budget = new Budget(0.00, month);
        hasBudget = false;
        expenditureRecords = new ArrayList<>();
        loanRecords = new ArrayList<>();
    }

    public void addBudget(double spendingLimit, boolean isLoadingStorage) {
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
     * @param category category which the expenditure falls under
     * @param isLoadingStorage indicate if this command is called during loading or runtime
     * @return Expenditure which was added.
     */
    public Expenditure addExpenditure(String description, double amount, LocalDate date, Category category,
                               boolean isLoadingStorage) {
        Expenditure expenditureToAdd = new Expenditure(description, amount, date, category);
        expenditureRecords.add(expenditureToAdd);
        numberOfRecords += 1;
        return expenditureToAdd;
        /*
        if (!isLoadingStorage) {
            Storage storeCurrentExpenditure = new Storage();
            storeCurrentExpenditure.saveNewlyAddedExpenditure(description, amount, date);
        }
        */
        //assert getExpenditureListSize() == numberOfRecords;
    }

    public void addLoan(String name, double amount, LocalDate date, boolean isLoadingStorage) {
        loanRecords.add(new Loan(name, amount, date));
        numberOfRecords += 1;
        //assert getLoanListSize() == numberOfRecords;
    }

    public void deleteBudget() {
        budget.clearAmount();
        assert budget.getAmount() == 0.00;
        hasBudget = false;
    }

    public void deleteExpenditure(int index) {
        expenditureRecords.remove(index - 1);
        numberOfRecords -= 1;
        //assert getExpenditureListSize() == numberOfRecords;
    }

    public void deleteLoan(int index) {
        loanRecords.remove(index - 1);
        numberOfRecords -= 1;
        //assert getLoanListSize() == numberOfRecords;
    }

    public ArrayList<Expenditure> getExpenditureRecords() {
        return expenditureRecords;
    }

    public ArrayList<Loan> getLoanRecords() {
        return loanRecords;
    }

    public Budget getBudget() {
        return budget;
    }

    public int getExpenditureListSize() {
        return expenditureRecords.size();
    }

    public int getLoanListSize() {
        return loanRecords.size();
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

    public Loan getLoan(int index) {
        return loanRecords.get(index);
    }

    public boolean checkOverspending() {
        double totalSpending = getTotalAmountSpent();

        return (totalSpending > budget.getAmount() && budget.getAmount() > 0);
    }

    public double getTotalAmountSpent() {
        double totalSpending = 0.0;
        for (Record record: expenditureRecords) {
            totalSpending += record.getAmount();
        }
        return totalSpending;
    }

    public double getCategorySpending(String categoryString) {
        double categorySpending = 0.0;
        for (Expenditure expenditure: expenditureRecords) {
            String expenditureCategory = expenditure.getCategory();
            if (expenditureCategory.equals(categoryString)) {
                categorySpending += expenditure.getAmount();
            }
        }
        return categorySpending;
    }

    public int getSize() {
        return expenditureRecords.size();
    }

}
