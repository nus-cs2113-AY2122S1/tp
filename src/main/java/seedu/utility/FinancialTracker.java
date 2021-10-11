package seedu.utility;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;

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

    public void removeEntry(int itemIndex) {
        financialEntries.remove(itemIndex);
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
