package seedu.budgettracker.data.records;

import java.time.LocalDate;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;


public class Loan extends Record {
    protected String debtorName;
    protected LocalDate date;
    protected LocalDate dueDate;
    private static final int TWO_WEEKS = 14;
    private static final int HALF_MONTH = 15;
    private static final int TWO_DECIMAL = 10;
    private static final int DECEMBER = 12;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Loan(String debtorName, double amount, LocalDate date) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.debtorName = debtorName;
    }

    //@@author EdisonZhong17
    public String getDueDateString() {
        String[] dayString = date.toString().split("-", 3);
        String dueDateString;
        String dueDateMonthString;
        int dueDateYear = Integer.parseInt(dayString[0]);
        int dueDateMonth = Integer.parseInt(dayString[1]);
        int dueDateDay = Integer.parseInt(dayString[2]);
        if (dueDateMonth != DECEMBER) {
            if (dueDateDay > HALF_MONTH) {
                dueDateMonth += 1;
                dueDateDay -= HALF_MONTH;
            } else {
                dueDateDay += TWO_WEEKS;
            }
        } else {
            if (dueDateDay > HALF_MONTH) {
                dueDateMonth = 1;
                dueDateYear += 1;
                dueDateDay -= HALF_MONTH;
            } else {
                dueDateDay += TWO_WEEKS;
            }
        }

        if (dueDateMonth < TWO_DECIMAL) {
            dueDateMonthString = "0" + dueDateMonth;
            dueDateString = dueDateYear + "-" + dueDateMonthString + "-" + dueDateDay;
        } else {
            dueDateString = dueDateYear + "-" + dueDateMonth + "-" + dueDateDay;
        }

        return dueDateString;

    }

    //@@author EdisonZhong17
    public int getDueDateMonth() {
        String[] d = dueDate.toString().split("-", 3);
        return Integer.parseInt(d[1]);
    }

    //@@author EdisonZhong17
    public int getDueDateDay() {
        String[] d = dueDate.toString().split("-", 3);
        return Integer.parseInt(d[2]);
    }
    //@@author

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

    //@@author EdisonZhong17
    public void setDueDate() {
        String dueDateString = getDueDateString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDateString, formatter);
    }

    //@@author EdisonZhong17
    public LocalDate getDueDate() {
        return dueDate;
    }
    //@@author

    public String toString() {
        return String.format("%-20.20s  %-20.20s %-20.20s",
                this.debtorName, " | $" + df.format(this.amount), " | " + this.date.toString());
    }
}
