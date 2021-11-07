package seedu.entry;

import seedu.commands.currency.CurrencyType;

import java.time.LocalDate;


public abstract class Entry {
    protected String description;
    protected double value;
    protected LocalDate date;
    protected static final String DATE_FORMAT = "dd/MM/yyyy";
    protected CurrencyType originalCurrency;
    protected double originalValue;

    public String getDescription() {
        return this.description;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double newValue) {
        this.value = newValue;
    }

    public LocalDate getDate() {
        return this.date;
    }
    
    public abstract Enum getCategory();

    public CurrencyType getOriginalCurrency() {
        return this.originalCurrency;
    }

    public double getOriginalValue() {
        return this.originalValue;
    }

    public void setCurrentDetails(double amount, CurrencyType originalCurrency) {
        this.originalCurrency = originalCurrency;
        this.originalValue = amount;
    }

    public abstract String toString();
}
