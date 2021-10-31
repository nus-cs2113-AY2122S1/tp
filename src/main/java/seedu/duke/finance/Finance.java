package seedu.duke.finance;

public class Finance {
    private String date;
    private String account;

    public Finance(String date, String account) {
        this.date = date;
        this.account = account;
    }

    @Override
    public String toString() {
        return date + " $" + account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAccount() {
        return Integer.parseInt(account);
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
