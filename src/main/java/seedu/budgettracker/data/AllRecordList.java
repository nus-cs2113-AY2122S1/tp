package seedu.budgettracker.data;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
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
    public static final String LS = System.lineSeparator();
    private static final String DIVIDER = "========================================================";

    public static String storageDirectory;
    private final Hashtable<Integer, RecordList> allRecordList;
    protected int year;

    /**
     * Constructor that creates 12 RecordLists upon construction.
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

    public int getYear() {
        return year;
    }

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
    public void addBudget(double spendingLimit, int month, boolean isLoadingStorage) {
        allRecordList.get(month).addBudget(spendingLimit, month, isLoadingStorage);
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
        int month = date.getMonthValue();
        Expenditure addedExpenditure = allRecordList.get(month)
                .addExpenditure(description, amount, date, category, isLoadingStorage);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
        return addedExpenditure;
    }

    public void addLoan(String name, double amount, LocalDate date, boolean isLoadingStorage) {
        int month = date.getMonthValue();
        allRecordList.get(month).addLoan(name, amount, date, isLoadingStorage);
        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }
    }

    public Budget editBudget(int month, double amount, boolean isLoadingStorage) {
        Budget targetBudget = allRecordList.get(month).getBudget();
        if (amount != 0.00) {
            targetBudget.setAmount(amount);
        }

        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }

        return targetBudget;
    }

    public Expenditure editExpenditure(int month, int index, double amount,
                                       String description, LocalDate date, boolean isLoadingStorage) {
        Expenditure targetExpenditure = allRecordList.get(month).getExpenditure(index);
        if (amount != 0.00) {
            targetExpenditure.setAmount(amount);
        }
        if (!description.equals("")) {
            targetExpenditure.setDescription(description);
        }
        if (!date.equals(LocalDate.now())) {
            targetExpenditure.setDate(date);
        }

        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }

        return targetExpenditure;
    }

    public Loan editLoan(int month, int index, double amount, String name, LocalDate date, boolean isLoadingStorage) {
        Loan targetLoan = allRecordList.get(month).getLoan(index);
        if (amount != 0.00) {
            targetLoan.setAmount(amount);
        }
        if (!name.equals("")) {
            targetLoan.setName(name);
        }
        if (!date.equals(LocalDate.now())) {
            targetLoan.setDate(date);
        }

        if (!isLoadingStorage) {
            saveToStorage(storageDirectory);
        }

        return targetLoan;
    }

    public void clearAll() {
        allRecordList.clear();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }
    }

    public void deleteBudget(int month) {
        allRecordList.get(month).deleteBudget();
        saveToStorage(storageDirectory);
    }

    public void deleteExpenditure(int index, int month) {
        allRecordList.get(month).deleteExpenditure(index);
        saveToStorage(storageDirectory);
    }

    public void deleteLoan(int index, int month) {
        allRecordList.get(month).deleteLoan(index);
        saveToStorage(storageDirectory);
    }

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

    public boolean checkOverspending(int month) {
        return allRecordList.get(month).checkOverspending();
    }

    public int getMonthListSize(int month) {
        return allRecordList.get(month).getSize();
    }

    /**
     * Get the total amount spent from expenditures of a month.
     *
     * @param month month to get the total amount spent
     * @return double amount spent during the specified month
     */
    public double getTotalAmountSpent(int month) {
        return allRecordList.get(month).getTotalAmountSpent();
    }

    /**
     * Get the amount spent on a specified category during the specified month.
     *
     * @param month month to get the amount spent
     * @param categoryString name of the category specified
     * @return
     */
    public double getCategorySpending(int month, String categoryString) {
        return allRecordList.get(month).getCategorySpending(categoryString);
    }
}
