package seedu.budgettracker.data;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.data.records.Record;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;

import java.time.LocalDate;
import java.util.ArrayList;

public class RecordList {
    public static final String MESSAGE_DUPLICATE_BUDGET = "You have already added a budget to this month! "
            + "Use edit to change its value instead.";
    private final Budget budget;
    private final ArrayList<Expenditure> expenditureRecords;
    private final ArrayList<Loan> loanRecords;
    private boolean hasBudget;

    /**
     * Constructor for MonthlyRecordList.
     */
    public RecordList(int month) {
        budget = new Budget(0.00, month);
        hasBudget = false;
        expenditureRecords = new ArrayList<>();
        loanRecords = new ArrayList<>();
    }

    /**
     * Adds a budget to the record list.
     *
     * @param amount the budget's amount
     * @throws DuplicateBudgetException if a budget is already set for the month
     */
    public void addBudget(double amount) throws DuplicateBudgetException {
        if (!hasBudget) {
            budget.setAmount(amount);
            assert budget.getAmount() == amount;
            hasBudget = true;
        } else {
            throw new DuplicateBudgetException(MESSAGE_DUPLICATE_BUDGET);
        }
    }

    /**
     * Adds an expenditure record into the RecordList.
     *
     * @param description description of the expenditure
     * @param amount amount spent
     * @param date date on which the expenditure took place
     * @param category category which the expenditure falls under
     * @return Expenditure which was added.
     */
    public Expenditure addExpenditure(String description, double amount, LocalDate date, Category category) {
        Expenditure expenditureToAdd = new Expenditure(description, amount, date, category);
        expenditureRecords.add(expenditureToAdd);
        return expenditureToAdd;
    }

    /**
     * Adds a loan record into the RecordList.
     *
     * @param name the borrower's name
     * @param amount the amount borrowed
     * @param date date on which the loan took place
     */
    public void addLoan(String name, double amount, LocalDate date) {
        loanRecords.add(new Loan(name, amount, date));
    }

    /**
     * Deletes the budget.
     */
    public void deleteBudget() {
        budget.clearAmount();
        assert budget.getAmount() == 0.00;
        hasBudget = false;
    }

    /**
     * Deletes the specified expenditure.
     *
     * @param index the index of the target expenditure
     */
    public void deleteExpenditure(int index) {
        expenditureRecords.remove(index);
    }

    /**
     * Deletes the specified loan.
     *
     * @param index the index of the target loan
     */
    public void deleteLoan(int index) {
        loanRecords.remove(index);
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

    public Expenditure getExpenditure(int index) {
        return expenditureRecords.get(index);
    }

    public Loan getLoan(int index) {
        return loanRecords.get(index);
    }

    /**
     * Retrieves the hasBudget boolean on whether the record list has a budget.
     * @return a boolean on whether the budget exists
     */
    public boolean hasBudget() {
        return hasBudget;
    }

    /**
     * Returns the total amount spent on expenditures in this month.
     *
     * @return double Amount spent in this month
     */
    public double getTotalAmountSpentOnExpenditures() {
        double totalSpending = 0.0;
        for (Record record: expenditureRecords) {
            totalSpending += record.getAmount();
        }
        return totalSpending;
    }

    public double getCategorySpending(String categoryString) {
        double categorySpending = 0.0;
        for (Expenditure expenditure: expenditureRecords) {
            String expenditureCategory = expenditure.getCategoryString();
            if (expenditureCategory.equals(categoryString)) {
                categorySpending += expenditure.getAmount();
            }
        }
        return categorySpending;
    }

    public int getNumberOfExpenditures() {
        return expenditureRecords.size();
    }

}
