package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author thaddyz
class PeopleManagerTest {
    static PeopleManager peopleManager;

    /**
     * Adds 3 people into the peopleManager Class.
     * This method will only run once for this test class.
     */
    @BeforeAll
    public static void setUp() throws LotsException {
        peopleManager = new PeopleManager();
        String[] names = {"Markus", "Adam", "Andrew"};
        PeopleManager.clearListOfPeople();

        for (int i = 0; i < 3; i++) {
            Person person = new Person(names[i]);
            peopleManager.addPerson(person);
        }
    }



    @Test
    public void getSize_listNotEmpty_normalResponse() {
        assertEquals(3, peopleManager.getSize());
    }

    @Test
    public void deletePerson_InvalidPersonIndex_expectException() {
        assertThrows(LotsException.class, () -> {
            peopleManager.deletePerson(8);
        });
    }

    @Test
    public void isEmpty_listOfPeopleNotEmpty_falseReturned() {
        assertEquals(false, peopleManager.isEmpty());
    }

}
