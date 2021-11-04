package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.expense.Expense;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    static Expense exp;

    @BeforeAll
    static void setUp() {
        Person person = new Person("Xi Yuan");
        ArrayList<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(person);
        exp = new Expense(8.0, "food", listOfPersons, "nice Italian restaurant");
    }

    @Test
    void getExpenseSummary() {
        assertEquals(8.00, exp.getAmountSpent());
    }
}
