package seedu.utility;

import seedu.entry.Entry;

import java.util.ArrayList;

public class FinancialTracker {
    private ArrayList<Entry> financialEntries;

    public FinancialTracker() {
        this.financialEntries = new ArrayList<>();
    }

    public int size() {
        return financialEntries.size();
    }

    public boolean isEmpty() {
        return financialEntries.isEmpty();
    }

    public void addEntry(Entry entry) {
        //TODO
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

    public ArrayList<Entry> getTotalExpense() {
        return null;
    }

    public ArrayList<Entry> getTotalIncome() {
        //TODO
        return null;
    }
}
