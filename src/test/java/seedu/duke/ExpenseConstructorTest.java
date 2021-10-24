//package seedu.duke;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ExpenseConstructorTest {
//    /**
//     * Create a valid seedu.duke.models.Expense object.
//     *
//     * @result getters for seedu.duke.models.Expense object should tally.
//     */
//    @Test
//    void createExpense_allFieldsValid_success() {
//        String name = "Samurai Burger";
//        String date = "2021-10-12";
//        String amount = "7.50";
//        String catNum = "0";
//        CategoryList.initialiseCategories();
//        seedu.duke.models.Expense expense = new seedu.duke.models.Expense(name, date, amount, catNum);
//        assertEquals("Food | 2021-10-12 | Samurai Burger | $7.50", expense.toString());
//    }
//}
