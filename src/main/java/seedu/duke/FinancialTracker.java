package seedu.duke;

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

}
