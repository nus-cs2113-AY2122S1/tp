package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    static Expense exp;

    @BeforeAll
    static void setUp() {
        Person person = new Person("Xi Yuan", true);
        exp = new Expense(person,8, "for lunch");
    }

    @Test
    void getExpenseSummary() {
        assertEquals(8.00, exp.getExpenseSummary());
    }
}
