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

    public ArrayList<Object> listExpense() {
        return null;
    }

    public ArrayList<Object> listIncome() {
        //TODO
        return null;
    }

    public Entry getEntry(int EntryIndex) {
        return this.financialEntries.get(EntryIndex);
    }

    public ArrayList<Entry> getEntries() {
        return this.financialEntries;
    }

    public ArrayList<Object> getTotalExpense() {
        return null;
    }

    public ArrayList<Object> getTotalIncome() {
        //TODO
        return null;
    }
}
