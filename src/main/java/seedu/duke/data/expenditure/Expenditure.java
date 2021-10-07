package seedu.duke.data.expenditure;

public class Expenditure {
    public static String description;
    public static double spending;

    public Expenditure(String description, double spending) {
        this.description = description;
        this.spending = spending;
    }

    public void printExpenditure() {
        System.out.println(this.description + " $" + this.spending);
    }
}
