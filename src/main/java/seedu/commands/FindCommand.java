package seedu.commands;

import seedu.entry.*;
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
    
    public void execute(FinancialTracker finances, Ui ui) {
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
            } else if (valueAsString.contains(keyword)) {
                filteredEntries.add(entry);
            } else {
                Enum filterCategory;
                switch (keyword) {
                case "FOOD":
                    filterCategory = ExpenseCategory.FOOD;
                    break;
                case "TRANSPORT":
                    filterCategory = ExpenseCategory.TRANSPORT;
                    break;
                case "MEDICAL":
                    filterCategory = ExpenseCategory.MEDICAL;
                    break;
                case "BILLS":
                    filterCategory = ExpenseCategory.BILLS;
                    break;
                case "ENTERTAINMENT":
                    filterCategory = ExpenseCategory.ENTERTAINMENT;
                    break;
                case "SALARY":
                    filterCategory = IncomeCategory.SALARY;
                    break;
                case "ALLOWANCE":
                    filterCategory = IncomeCategory.ALLOWANCE;
                    break;
                case "ADHOC":
                    filterCategory = IncomeCategory.ADHOC;
                    break;
                default:
                    filterCategory = ExpenseCategory.NULL;
                }
                if (entry.getCategory().equals(filterCategory)) {
                    filteredEntries.add(entry);
                }
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