package seedu.duke.entries;

import java.time.LocalDate;
import java.util.Objects;

public class RecurringIncome extends RecurringEntry {
    protected IncomeCategory category;

    public RecurringIncome(RecurringIncome income) {
        super(income);
        this.category = income.getCategory();
        this.type = Type.Income;
    }

    public RecurringIncome(String name, LocalDate date, Double amount, IncomeCategory category,
                           Interval interval, LocalDate endDate) {
        super(name, date, amount, interval, endDate);
        this.category = category;
        this.type = Type.Income;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public void setCategory(IncomeCategory category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return getType() + " | " + getCategoryIndented() + " | " + getDate() + " | "
                + getNameIndented() + " |-$" + String.format("%,.2f", getAmount()) + " | " + getInterval().label;
    }

    //@@author nipafx-reusedS
    //Reused from https://www.sitepoint.com/implement-javas-equals-method-correctly/
    //with minor modifications
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }

        RecurringIncome recurringIncome = (RecurringIncome) object;
        boolean isNameEqual = Objects.equals(getName(), recurringIncome.getName());
        boolean isDateEqual = Objects.equals(getDate(), recurringIncome.getDate());
        boolean isAmountEqual = Objects.equals(getAmount(), recurringIncome.getAmount());
        boolean isCategoryEqual = Objects.equals(getCategory(), recurringIncome.getCategory());
        boolean isIntervalEqual = Objects.equals(getInterval(), recurringIncome.getInterval());
        boolean isEndDateEqaul = Objects.equals(getEndDate(), recurringIncome.getEndDate());
        return isNameEqual && isDateEqual && isAmountEqual && isCategoryEqual && isIntervalEqual && isEndDateEqaul;
    }
}
