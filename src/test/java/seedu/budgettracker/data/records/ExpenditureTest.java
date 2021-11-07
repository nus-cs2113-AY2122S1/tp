package seedu.budgettracker.data.records;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.testutil.ExpenditureBuilder;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ExpenditureTest {

    public static final String DIFFERENT_DESCRIPTION = "Different Test Expenditure";
    public static final int DIFFERENT_AMOUNT = 50;
    public static final LocalDate DIFFERENT_DATE = LocalDate.now().plusDays(1);
    public static final Category DIFFERENT_CATEGORY = Category.TECH;
    public static final String TOO_LONG_DESCRIPTION = "123456789012345678901234567890";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static final int INDEX_OF_EXPENDITURE = 0;
    private static final int DISPLAY_INDEX = 1;
    public static final int TRUNCATION_INDEX = 25;
    private static final String ELLIPSES = "...";



    @Test
    public void toStringWithIndex_tooLongDescription_truncatedDescription() {
        AllRecordList testRecordList = new AllRecordList();
        Expenditure testExpenditure = new ExpenditureBuilder().build();

        Expenditure tooLongDescriptionExpenditure = new ExpenditureBuilder()
                .withDescription(TOO_LONG_DESCRIPTION).build();

        String expectedDescriptionToPrint = TOO_LONG_DESCRIPTION.substring(0, TRUNCATION_INDEX) + ELLIPSES;

        String expectedExpenditureString = String.format("%-30.30s %-20.20s %-20.20s %-20.20s",
                DISPLAY_INDEX + "." + expectedDescriptionToPrint,
                "| $" + df.format(tooLongDescriptionExpenditure.amount),
                "| " + tooLongDescriptionExpenditure.getDateString(),
                "| " + tooLongDescriptionExpenditure.getCategoryString());
        assertEquals(tooLongDescriptionExpenditure.toString(INDEX_OF_EXPENDITURE), expectedExpenditureString);
    }

    @Test
    public void equals() {
        Expenditure testExpenditure = new ExpenditureBuilder().build();

        // same object -> returns true
        assertEquals(testExpenditure, testExpenditure);

        //null -> returns false
        assertNotEquals(null, testExpenditure);

        Expenditure differentTestExpenditure = new ExpenditureBuilder().withDescription(DIFFERENT_DESCRIPTION)
                .withAmount(DIFFERENT_AMOUNT)
                .withDate(DIFFERENT_DATE)
                .withCategory(DIFFERENT_CATEGORY).build();

        //different expenditures -> returns false
        assertNotEquals(testExpenditure, differentTestExpenditure);

        //different amount -> returns false
        Expenditure editedExpenditure = new ExpenditureBuilder(testExpenditure)
                .withAmount(DIFFERENT_AMOUNT).build();
        assertNotEquals(testExpenditure, editedExpenditure);

        //different description -> returns false
        editedExpenditure.setDescription(DIFFERENT_DESCRIPTION);
        assertNotEquals(testExpenditure, editedExpenditure);

        //different date -> returns false
        editedExpenditure.setDate(DIFFERENT_DATE);
        assertNotEquals(testExpenditure, editedExpenditure);

        //different category -> returns false
        editedExpenditure = new ExpenditureBuilder(testExpenditure)
                .withCategory(DIFFERENT_CATEGORY).build();
        assertNotEquals(testExpenditure, editedExpenditure);
    }
}

