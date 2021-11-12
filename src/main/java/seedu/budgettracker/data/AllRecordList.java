package seedu.budgettracker.data;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.data.records.exceptions.EmptyRecordException;
import seedu.budgettracker.storage.textfiletools.WriteToTextFile;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Stores 12 instances of (Integer month, RecordList monthRecordList) inside a Hashtable,
 * where month ranges from 1 to 12.
 */
public class AllRecordList {
    public static final String MESSAGE_N0_BUDGET = "Your budget has not yet been set!"
            + "Use 'add -b' or see help for more info";
    public static final LocalDate DATE_EMPTY = LocalDate.of(9898, 1, 1);
    public static String storageDirectory;
    private final Hashtable<Integer, RecordList> allRecordList;
    protected int year;

    /**
     * Constructor that creates 12 RecordLists upon initialization.
     */
    public AllRecordList() {
        year = LocalDate.now().getYear();
        allRecordList = new Hashtable<>();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }
    }


    public void statIntro(AllRecordList recordList) {
        TextUi.statsIntro(recordList);
    }

    private void saveToStorage(String storageDirectory) {
        WriteToTextFile textFileWriter = new WriteToTextFile();
        textFileWriter.reloadArrayToStorage(allRecordList, storageDirectory);
    }


    /**
     * Returns the year the user is inserting records into.
     *
     * @return year in which records belong to
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year which User is adding records to.
     *
     * @param year year in which records will be added to
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Add a budget record into a month RecordList.
     *
     * @param spendingLimit Spending limit set for the month
     * @param month month which budget is being set for
     * @param isLoadingStorage indicate if this command is called during setup or runtime
     */
    public void addBudget(double spendingLimit, int month, boolean isLoadingStorage) throws DuplicateBudgetException {
        assert spendingLimit > 0 : "Amount should be greater than 0";
        allRecordList.get(month).addBudget(spendingLimit);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
    }

    /**
     * Add an expenditure record into a month RecordList.
     *
     * @param description      description of the expenditure
     * @param amount           amount spent
     * @param isLoadingStorage indicate if this command is called during setup or runtime
     * @param category category which expenditure belongs to
     */
    public Expenditure addExpenditure(String description, double amount, LocalDate date, Category category,
                                      boolean isLoadingStorage) {
        assert amount > 0 : "Amount should be greater than 0";
        int month = date.getMonthValue();
        Expenditure addedExpenditure = allRecordList.get(month)
                .addExpenditure(description, amount, date, category);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
        return addedExpenditure;
    }

    /**
     * Adds a loan record into the record list.
     *
     * @param name the name of the borrower
     * @param amount how much the borrower owes you
     * @param date the date of the loan
     * @param isLoadingStorage whether this method is being run at setup or during runtime
     */
    public void addLoan(String name, double amount, LocalDate date, boolean isLoadingStorage) {
        int month = date.getMonthValue();
        allRecordList.get(month).addLoan(name, amount, date);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
    }

    /**
     * Edits the budget amount previously set by the user.
     *
     * @param month the corresponding month of the selected budget
     * @param amount the new amount to be set
     * @return the edited Budget record
     */
    public Budget editBudget(int month, double amount) throws EmptyRecordException {
        Budget targetBudget = allRecordList.get(month).getBudget();
        if (allRecordList.get(month).hasBudget()) {
            targetBudget.setAmount(amount);
            saveToStorage(storageDirectory);
        } else {
            throw new EmptyRecordException(MESSAGE_N0_BUDGET);
        }
        return targetBudget;
    }

    /**
     * Edits the expenditure previously set by the user.
     *
     * @param month the corresponding month of the selected expenditure
     * @param index the index of the target expenditure
     * @param amount the amount to be set, if any
     * @param description the description to be set, if any
     * @param date the date to be set, if any
     * @param category the category to be set, if any
     * @return the expenditure after editing
     */
    public Expenditure editExpenditure(int month, int index, double amount,
                                       String description, LocalDate date, Category category) {
        Expenditure targetExpenditure = allRecordList.get(month).getExpenditure(index);

        setExpValues(amount, description, date, category, targetExpenditure);
        saveToStorage(storageDirectory);
        return targetExpenditure;
    }

    private void setExpValues(double amount, String description, LocalDate date,
                                        Category category, Expenditure targetExpenditure) {
        if (amount != 0.00) {
            targetExpenditure.setAmount(amount);
        }
        if (!description.equals("")) {
            targetExpenditure.setDescription(description);
        }
        if (!date.equals(DATE_EMPTY)) {
            targetExpenditure.setDate(date);
        }
        if (!category.equals(Category.INVALID)) {
            targetExpenditure.setCategory(category);
        }
    }

    /**
     * Edits the loan previously set by the user.
     *
     * @param month the corresponding month of the selected expenditure
     * @param index the index of the target expenditure
     * @param amount the amount to be set, if any
     * @param name the name to be set, if any
     * @param date the date to be set, if any
     * @return the loan after editing
     */
    public Loan editLoan(int month, int index, double amount, String name, LocalDate date) {
        Loan targetLoan = allRecordList.get(month).getLoan(index);
        setLoanValues(amount, name, date, targetLoan);
        saveToStorage(storageDirectory);
        return targetLoan;
    }

    private void setLoanValues(double amount, String name, LocalDate date, Loan targetLoan) {
        if (amount != 0.00) {
            targetLoan.setAmount(amount);
        }
        if (!name.equals("")) {
            targetLoan.setName(name);
        }
        if (!date.equals(DATE_EMPTY)) {
            targetLoan.setDate(date);
        }
    }

    public void clearAll() {
        allRecordList.clear();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }
    }

    /**
     * Deletes the budget set specified by the month.
     *
     * @param month the target budget's month
     */
    //@@author EdisonZhong17
    public void deleteBudget(int month) {
        allRecordList.get(month).deleteBudget();
        saveToStorage(storageDirectory);
    }

    /**
     * Deletes the expenditure specified by the month and index.
     *
     * @param index the target expenditure's index
     * @param month the target expenditure's month
     */
    //@@author EdisonZhong17
    public void deleteExpenditure(int index, int month) {
        allRecordList.get(month).deleteExpenditure(index);
        saveToStorage(storageDirectory);
    }

    /**
     * Deletes the loan specified by the month and index.
     *
     * @param index the target loan's index
     * @param month the target loan's month
     */
    //@@author EdisonZhong17
    public void deleteLoan(int index, int month) {
        allRecordList.get(month).deleteLoan(index);
        saveToStorage(storageDirectory);
    }
    //@@author

    public RecordList getMonthRecord(int month) {
        return allRecordList.get(month);
    }

    public ArrayList<Expenditure> getExpenditureRecords(int month) {
        return allRecordList.get(month).getExpenditureRecords();
    }

    public ArrayList<Loan> getLoanRecords(int month) {
        return allRecordList.get(month).getLoanRecords();
    }

    public Budget getBudget(int month) {
        return allRecordList.get(month).getBudget();
    }

    public int getExpenditureListSize(int month) {
        return allRecordList.get(month).getExpenditureListSize();
    }

    public int getLoanListSize(int month) {
        return allRecordList.get(month).getLoanListSize();
    }

    public Expenditure getExpenditure(int index, int month) {
        return allRecordList.get(month).getExpenditure(index);
    }

    public Loan getLoan(int index, int month) {
        return allRecordList.get(month).getLoan(index);
    }

    public int getMonthNumberOfExpenditures(int month) {
        return allRecordList.get(month).getNumberOfExpenditures();
    }

    /**
     * Gets the total amount spent from expenditures of a month.
     *
     * @param month month to get the total amount spent
     * @return double total amount spent during the specified month
     */
    public double getTotalAmountSpent(int month) {
        return allRecordList.get(month).getTotalAmountSpentOnExpenditures();
    }

    /**
     * Get the amount spent on a specified category during the specified month.
     *
     * @param month month to get the amount spent
     * @param categoryString name of the category specified
     * @return double Amount spent on the specified category in the specified month
     */
    public double getCategorySpending(int month, String categoryString) {
        return allRecordList.get(month).getCategorySpending(categoryString);
    }
}
