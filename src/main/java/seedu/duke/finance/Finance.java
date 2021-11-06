package seedu.duke.finance;

import java.time.LocalDate;

/**
 * Represents a user's account. Contains the date, and the number of the account.
 */
public class Finance {
    private LocalDate date;
    private String account;

    /**
     * Constructs an instance of Finance.
     *
     * @param date Date of the account.
     * @param account Number of the account.
     */
    public Finance(LocalDate date, String account) {
        this.date = date;
        this.account = account;
    }

    @Override
    public String toString() {
        return date + " $" + account;
    }

    /**
     * Gets the date of the account.
     *
     * @return Date of the account in LocalDate format.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the account.
     *
     * @param date Date of the account.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the number of the account.
     *
     * @return Number of the account in Double format.
     */
    public Double getAccount() {
        return Double.parseDouble(account);
    }

    /**
     * Sets the account number.
     *
     * @param account number of the account.
     */
    public void setAccount(String account) {
        this.account = account;
    }
}
