package seedu.duke.data.records;

public class Expenditure extends Record {
    public String description;
    //public static LocalDate date;

    public Expenditure(String description, double amount) {
        super(amount);
        //Expenditure.date = date;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    /*
    Not used.
    public LocalDate getDate() {
        return date;
    }


    @Override
    public int getMonth() {
        return date.getMonthValue();
    }
    */


    public String toString() {
        return ("Description: " + this.description + "\nAmount: $" + this.amount);
    }

    @Override
    public String getType() {
        return "Expenditure";
    }
}
