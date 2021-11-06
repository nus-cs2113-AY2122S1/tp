package seedu.duke.finance;

import java.time.LocalDate;

public class Finance {
    private LocalDate date;
    private String account;

    public Finance(LocalDate date, String account) {
        this.date = date;
        this.account = account;
    }

    @Override
    public String toString() {
        return date + " $" + account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAccount() {
        return Double.parseDouble(account);
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
