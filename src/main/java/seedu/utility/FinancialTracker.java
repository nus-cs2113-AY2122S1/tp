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
        financialEntries.add(entry);
        //TODO
    }

    public void deleteEntry(int entryIndex) {
        //TODO
    }

    public ArrayList<Entry> listExpense() {
        //TODO
        return null;
    }

    public ArrayList<Entry> listIncome() {
        //TODO
        return null;
    }

    public Entry getTotalExpense() {
        //TODO
        return null;
    }

    public Entry getTotalIncome() {
        //TODO
        return null;
    }
}
