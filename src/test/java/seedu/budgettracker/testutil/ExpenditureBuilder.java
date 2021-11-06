package seedu.budgettracker.testutil;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;

import java.time.LocalDate;

public class ExpenditureBuilder {

    public static final String DEFAULT_DESCRIPTION = "Test Expenditure";
    public static final double DEFAULT_AMOUNT = 100.00;
    public static final LocalDate DEFAULT_DATE = LocalDate.now();
    public static final Category DEFAULT_CATEGORY = Category.GENERAL;

    private String description;
    private double amount;
    private LocalDate date;
    private Category category;

    public ExpenditureBuilder() {
        description = DEFAULT_DESCRIPTION;
        date = DEFAULT_DATE;
        category = DEFAULT_CATEGORY;
        amount = DEFAULT_AMOUNT;
    }

    public ExpenditureBuilder(Expenditure expenditureToCopy) {
        description = expenditureToCopy.getDescription();
        date = expenditureToCopy.getDate();
        category = expenditureToCopy.getCategory();
        amount = DEFAULT_AMOUNT;
    }

    public ExpenditureBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ExpenditureBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public ExpenditureBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ExpenditureBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public Expenditure build() {
        return new Expenditure(description, amount, date, category);
    }
}
