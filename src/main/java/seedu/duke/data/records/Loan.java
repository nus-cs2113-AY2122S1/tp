package seedu.duke.data.records;

import java.time.LocalDate;

public class Loan extends Record {
    protected String debtorName;
    protected LocalDate date;

    public Loan(String debtorName, double amount, LocalDate date) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.debtorName = debtorName;
    }

    public String getName() {
        return debtorName;
    }

    public void setName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toString() {
        return String.format("%-20.20s  %-20.20s %-20.20s",
                this.debtorName, " | $" + this.amount, " | " + this.date.toString());
    }
}
