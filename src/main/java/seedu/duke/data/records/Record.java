package seedu.duke.data.records;

public class Record {
    protected double amount;
    protected int month;

    public Record(double amount, int month) {
        this.amount = amount;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return "Record";
    }

    public String getDescription() {
        return null;
    }
}
