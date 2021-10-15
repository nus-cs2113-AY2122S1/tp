package seedu.duke.data.records;

public abstract class Record {
    protected double amount;
    //protected int month;

    public Record(double amount) {
        this.amount = amount;
        //this.month = month;
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

    public int getMonth() {
        return 0;
    }

    //public abstract LocalDate getDate();
}
