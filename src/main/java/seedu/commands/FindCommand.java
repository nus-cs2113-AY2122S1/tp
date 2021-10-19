package seedu.commands;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FindCommand extends Command {
    protected String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        ArrayList<Entry> entries = finances.getEntries();
        ArrayList<Entry> filteredEntries = new ArrayList<>();
        
        try {
            filterByDate(entries, filteredEntries);
        } catch (DateTimeParseException e) {
            filterByKeyword(entries, filteredEntries);
        }
        
        ui.listFind(filteredEntries);
    }

    private void filterByKeyword(ArrayList<Entry> entries, ArrayList<Entry> filteredEntries) {
        for (Entry entry: entries) {
            String valueAsString = Double.toString(entry.getValue());
            if (entry.getDescription().contains(keyword)) {
                filteredEntries.add(entry);
            } else if (entry.getCategory().toLowerCase().contains(keyword)) {
                filteredEntries.add(entry);
            } else if (valueAsString.contains(keyword)) {
                filteredEntries.add(entry);
            }
        }
    }

    private void filterByDate(ArrayList<Entry> entries, ArrayList<Entry> filteredEntries) {
        LocalDate localDate = LocalDate.parse(keyword);
        for (Entry entry: entries) {
            if (entry.getDate().isEqual(localDate)) {
                filteredEntries.add(entry);
            }
        }
    }
}