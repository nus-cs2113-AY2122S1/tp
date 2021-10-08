package seedu.duke.data.records;

public class Expenditure extends Record {
    public static String description;
    public static double spending;
    public static int month;

    public Expenditure(String description, double spending, int month) {
        super(spending, month);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public double getAmount() {
        return spending;
    }
    @Override
    public int getMonth() {
        return month;
    }

    public void printExpenditure() {
        System.out.println(this.description + " $" + this.spending + "Month: " + this.month);
    }
    @Override
    public String getType(){
        return "Expenditure";
    }
}
