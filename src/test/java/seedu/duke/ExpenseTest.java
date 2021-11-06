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
        Person person1 = new Person("Xi Yuan");
        Person person2 = new Person("Yi Kai");
        Person person3 = new Person("Qi An");
        Person person4 = new Person("Yuzhao");
        Person person5 = new Person("Joshua");
        ArrayList<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(person1);
        listOfPersons.add(person2);
        listOfPersons.add(person3);
        listOfPersons.add(person4);
        listOfPersons.add(person5);
        exp = new Expense(8.0, "food", listOfPersons, "nice Italian restaurant");
    }

    @Test
    void getExpenseSummary() {
        assertEquals(8.00, exp.getAmountSpent());
    }
}
