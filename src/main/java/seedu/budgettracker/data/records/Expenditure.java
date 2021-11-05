package seedu.budgettracker.data.records;

import java.time.LocalDate;

import static java.lang.Math.ceil;

public class Expenditure extends Record {
    private static final int DISPLAY_INDEX_OFFSET = 1;
    private static final int ellipsesCharactersNeeded = 3;
    protected String description;
    protected LocalDate date;
    protected Category category;

    public Expenditure(String description, double amount, LocalDate date, Category category) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Gets the date on which the expenditure was made.
     *
     * @return String date on which expenditure was made
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Gets category which expenditure falls under.
     *
     * @return String category which expenditure belongs to
     */
    public String getCategory() {
        return category.toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category returnCategory() {
        return category;
    }


    public String toString() {
        return this.description + this.amount + this.date + this.category;
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
                "| $" + this.amount,
                "| " + this.date.toString(),
                "| " + this.category.toString());
    }
}
