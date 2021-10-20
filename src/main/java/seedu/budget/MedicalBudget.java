package seedu.budget;

import seedu.entry.ExpenseCategory;

public class MedicalBudget extends Budget {

    public MedicalBudget(double limit) {
        this.category = ExpenseCategory.MEDICAL;
        this.name = ExpenseCategory.MEDICAL.toString();
        this.limit = limit;
    }
}
