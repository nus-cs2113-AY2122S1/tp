package seedu.duke.data.records;

public class Expenditure extends Record {
    public static String description;
    public static double spending;
    public int month;

    public Expenditure(String description, double spending, int month) {
        super(spending);
        this.description = description;
        this.month = month;
    }

    public void printExpenditure() {
        System.out.println(this.description + " $" + this.spending);
    }
}
