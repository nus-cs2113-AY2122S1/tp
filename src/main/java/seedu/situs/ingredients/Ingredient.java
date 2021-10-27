package seedu.situs.ingredients;

import seedu.situs.localtime.CurrentDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an Ingredient.
 */
public class Ingredient {
    protected String name;
    protected Double amount;
    protected LocalDate expiry;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public Ingredient(String name, double amount, LocalDate expiry) {
        this.name = name;
        this.amount = amount;
        this.expiry = expiry;
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public Double getAmount() {
        return amount;
    }


    public LocalDate getExpiry() {
        return expiry;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "Amount Left: " + String.format("%.1f", getAmount()) + " kg | Expiry Date: "
                 + Ingredient.dateToString(getExpiry());
    }

    /**
     * Reads in expiry as string from user input, converts to LocalDate variable.
     *
     * @param dateString Date as string from user input
     * @return Date as LocalDate
     */
    public static LocalDate stringToDate(String dateString) {
        return LocalDate.parse(dateString, dateFormat);
    }

    /**
     * Prints date in dd (name of month) yyyy (e.g. 21 NOVEMBER 2021).
     *
     * @param date Date to be printed
     * @return Date in string format (dd/mm/yyyy)
     */
    public static String dateToString(LocalDate date) {
        /*Month month = date.getMonth();
        return date.getDayOfMonth() + " " + month.toString() + " " + date.getYear();*/
        return date.format(dateFormat);
    }

    /**
     * Calculates the number of calendar days from current date.
     *
     * @param expiryDate Expiry date
     * @return number of days between current date and expiry date
     */
    public static long daysFromCurrentDate(LocalDate expiryDate) {
        return ChronoUnit.DAYS.between(CurrentDate.getCurrentDate(), expiryDate);
    }

}
