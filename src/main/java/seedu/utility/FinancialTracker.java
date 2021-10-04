package seedu.utility;

import seedu.entry.Entry;

import java.util.ArrayList;

public class FinancialTracker {
    private ArrayList<Entry> FinancialEntries;

    public FinancialTracker() {
        this.FinancialEntries = new ArrayList<>();
    }

    public int size() {
        return FinancialEntries.size();
    }

    public boolean isEmpty() {
        return FinancialEntries.isEmpty();
    }

    public void addEntry(Entry entry) {
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

    public Entry getTotalExpense(){
        //TODO
        return null;
    }

    public Entry getTotalIncome(){
        //TODO
        return null;
    }
}
