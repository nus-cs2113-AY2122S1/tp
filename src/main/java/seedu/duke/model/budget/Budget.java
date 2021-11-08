package seedu.duke.model.budget;

import seedu.duke.model.entries.Entry;
import seedu.duke.model.entries.ExpenseCategory;
import seedu.duke.model.entries.Type;

import java.time.LocalDate;
import java.util.ArrayList;

//@@author irvinseet
public abstract class Budget {
    protected ExpenseCategory category;
    protected String name;
    protected double limit;

    public ExpenseCategory getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public void setLimit(double amount) {
        this.limit = amount;
    }

    public double getLimit() {
        return this.limit;
    }

    public double getMonthlySpending(ArrayList<Entry> entries, ArrayList<Entry> recurringEntries) {
        double amount = 0;
        for (Entry entry : entries) {
            if (entry.getType() == Type.Expense
                    && (entry.getCategory() == this.category)
                    && (entry.getDate().getMonth() == LocalDate.now().getMonth())
                    && entry.getDate().getYear() == LocalDate.now().getYear()) {
                amount += entry.getAmount();
            }
        }
        for (Entry entry : recurringEntries) {
            if (entry.getType() == Type.Expense && (entry.getCategory() == this.category)) {
                String interval = entry.getInterval().toString();
                if (interval.equals("MONTH")
                        && tookPlaceThisMonth(entry)) {
                    amount += entry.getAmount();
                }
                if (interval.equals("YEAR")
                        && entry.getDate().getMonth() == LocalDate.now().getMonth()
                        && tookPlaceThisMonth(entry)) {
                    amount += entry.getAmount();
                }
            }
        }
        return amount;
    }

    private boolean tookPlaceThisMonth(Entry entry) {
        return (entry.getDate().isBefore(LocalDate.now().plusMonths(1).withDayOfMonth(1)))
                && entry.getEndDate().isAfter(LocalDate.now().withDayOfMonth(1));
    }
}