package seedu.duke;

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

}
