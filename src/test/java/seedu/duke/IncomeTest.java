package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {

    @Test
    public void getDescription_stringDescription_returnsDescription() {
        Income i1 = new Income("Jan Allowance", 480.00, IncomeCategory.ALLOWANCE);
        assertEquals("Jan Allowance", i1.getDescription());
    }

    @Test
    public void getValue_valueInputOfTypeDouble_returnsValueInput() {
        Income i1 = new Income("Jan Allowance", 480.00, IncomeCategory.ALLOWANCE);
        assertEquals(480.00, i1.getValue());
    }
}
