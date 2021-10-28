package seedu.budgettracker.data.records;

import java.time.LocalDate;
import java.lang.String;
import java.time.format.DateTimeFormatter;

public class Loan extends Record {
    protected String debtorName;
    protected LocalDate date;
    protected LocalDate dueDate;

    public Loan(String debtorName, double amount, LocalDate date) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.debtorName = debtorName;
    }

    public String getDueDateString() {
        String[] dayString = date.toString().split("-", 3);
        String dueDateString;
        String dueDateMonthString;
        int dueDateYear = Integer.parseInt(dayString[0]);
        int dueDateMonth = Integer.parseInt(dayString[1]);
        int dueDateDay = Integer.parseInt(dayString[2]);
        if (dueDateMonth != 12) {
            if (dueDateDay > 15) {
                dueDateMonth += 1;
                dueDateDay -= 15;
            } else {
                dueDateDay += 14;
            }
        } else {
            if (dueDateDay > 15) {
                dueDateMonth = 1;
                dueDateYear += 1;
                dueDateDay -= 15;
            } else {
                dueDateDay += 14;
            }
        }

        if (dueDateMonth < 10) {
            dueDateMonthString = "0" + dueDateMonth;
            dueDateString = dueDateYear + "-" + dueDateMonthString + "-" + dueDateDay;
        } else {
            dueDateString = dueDateYear + "-" + dueDateMonth + "-" + dueDateDay;
        }

        return dueDateString;

    }

    public int getDueDateMonth() {
        String[] d = dueDate.toString().split("-", 3);
        return Integer.parseInt(d[1]);
    }

    public int getDueDateDay() {
        String[] d = dueDate.toString().split("-", 3);
        return Integer.parseInt(d[2]);
    }

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

    public void setDueDate() {
        String dueDateString = getDueDateString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDateString, formatter);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String toString() {
        return String.format("%-20.20s  %-20.20s %-20.20s",
                this.debtorName, " | $" + this.amount, " | " + this.date.toString());
    }
}