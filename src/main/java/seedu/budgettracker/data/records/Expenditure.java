package seedu.budgettracker.data.records;

import java.time.LocalDate;
import java.text.DecimalFormat;

import static java.lang.Math.ceil;

public class Expenditure extends Record {
    private static final int DISPLAY_INDEX_OFFSET = 1;
    private static final int ellipsesCharactersNeeded = 3;
    protected String description;
    protected LocalDate date;
    protected Category category;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public Expenditure(String description, double amount, LocalDate date, Category category) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the date on which the expenditure was made after casting to string.
     *
     * @return String date on which expenditure was made
     */
    public String getDateString() {
        return date.toString();
    }

    /**
     * Gets category which expenditure falls under.
     *
     * @return Category category which expenditure belongs to
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets category which expenditure falls under as a String.
     *
     * @return String category which expenditure belongs to
     */
    public String getCategoryString() {
        return category.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String toString() {
        return (this.description + df.format(this.amount) + this.date + this.category);
    }

    /**
     * Returns the details of the Expenditure in table row format;
     * description is truncated to fit within the column width.
     *
     * @param indexOfExpenditure The current index of the Expenditure
     * @return String details of the expenditure
     */
    public String toString(int indexOfExpenditure) {
        int displayIndex = indexOfExpenditure + DISPLAY_INDEX_OFFSET;
        int displayIndexCharactersNeeded = (int) ceil((double)displayIndex / 10) + 1;
        int numberOfDescriptionColumnCharacters = 30 - displayIndexCharactersNeeded - ellipsesCharactersNeeded;
        String descriptionToPrint = this.description;
        if (this.description.length() > numberOfDescriptionColumnCharacters) {
            descriptionToPrint = this.description.substring(0, numberOfDescriptionColumnCharacters) + "...";
        }
        return String.format("%-30.30s %-20.20s %-20.20s %-20.20s",
                displayIndex + "." + descriptionToPrint,
                "| $" + df.format(this.amount),
                "| " + this.date.toString(),
                "| " + this.category.toString());
    }
}
