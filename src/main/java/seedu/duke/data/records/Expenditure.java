package seedu.duke.data.records;

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

    public String getDate() {
        return date.toString();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category.toString();
    }

    public String toString() {
        return String.format("%-20.20s  %-20.20s %-20.20s %-20.20s",
                this.description, "| $" + this.amount, "| " + this.date.toString(), "| " + this.category.toString());
    }

    public String toString(int indexOfExpenditure) {
        int displayIndex = indexOfExpenditure + DISPLAY_INDEX_OFFSET;
        int displayIndexCharactersNeeded = (int) ceil((double)displayIndex/10) + 1;
        int numberOfDescriptionColumnCharacters = 30 - displayIndexCharactersNeeded - ellipsesCharactersNeeded;
        String descriptionToPrint = this.description;
        if (this.description.length() > numberOfDescriptionColumnCharacters) {
            descriptionToPrint = this.description.substring(0, numberOfDescriptionColumnCharacters) + "...";
        }
        return String.format("%-30.30s %-20.20s %-20.20s %-20.20s",
                displayIndex + "." + descriptionToPrint, "| $" + this.amount, "| " + this.date.toString(), "| " + this.category.toString());
    }
}
