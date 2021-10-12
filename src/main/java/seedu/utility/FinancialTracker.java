package seedu.utility;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;

import java.util.ArrayList;

public class FinancialTracker {
    private ArrayList<Entry> financialEntries;

    public FinancialTracker() {
        this.financialEntries = new ArrayList<>();
    }

    public int getSize() {
        return financialEntries.size();
    }

    public boolean isEmpty() {
        return financialEntries.isEmpty();
    }

    public Entry addEntry(Entry entry) {
        financialEntries.add(entry);
        return entry;
    }

    public Entry removeEntry(int itemIndex) {
        Entry deletedEntry = financialEntries.get(itemIndex);
        financialEntries.remove(itemIndex);
        if (deletedEntry instanceof Expense) {
            Expense.decrementSize();
        } else if (deletedEntry instanceof Income) {
            Income.decrementSize();
        }
        return deletedEntry;
    }

    public Entry removeExpenseEntry(int expenseItemNumber) throws ExpenseEntryNotFoundException {
        assert expenseItemNumber - 1 <= Expense.getSize() : "Invalid Delete Number";
        int itemIndex = 0;
        int expenseItemCounter = 0;
        for (Entry entry : this.financialEntries) {
            if (entry instanceof Expense) {
                expenseItemCounter += 1;
            }
            if (expenseItemCounter == expenseItemNumber) {
                return removeEntry(itemIndex);
            }
            itemIndex += 1;
        }
        throw new ExpenseEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
    }

    public Entry removeIncomeEntry(int incomeItemNumber) throws IncomeEntryNotFoundException {
        assert incomeItemNumber - 1 <= Income.getSize() : "Invalid Delete Number";
        int itemIndex = 0;
        int incomeItemCounter = 0;
        for (Entry entry : this.financialEntries) {
            if (entry instanceof Income) {
                incomeItemCounter += 1;
            }
            if (incomeItemCounter == incomeItemNumber) {
                return removeEntry(itemIndex);
            }
            itemIndex += 1;
        }
        throw new IncomeEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
    }


    // Can implement later when needed
    public int adjustItemIndex(int itemIndex) {
        return itemIndex - 1;
    }

    public ArrayList<Entry> listExpense() {
        return null;
    }

    public ArrayList<Entry> listIncome() {
        //TODO
        return null;
    }

    public Entry getEntry(int entryIndex) {
        return this.financialEntries.get(entryIndex);
    }

    public ArrayList<Entry> getEntries() {
        return this.financialEntries;
    }

    public double getTotalExpense() {
        double totalExpense = 0;
        for (Entry entry : this.financialEntries) {
            if (entry instanceof Expense) {
                totalExpense += entry.getValue();
            }
        }
        return totalExpense;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (Entry entry : financialEntries) {
            if (entry instanceof Income) {
                totalIncome += entry.getValue();
            }
        }
        return totalIncome;
    }
}
