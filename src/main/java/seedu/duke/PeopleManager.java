package seedu.duke;

import seedu.duke.exceptions.LotsException;

import java.util.LinkedList;

public class PeopleManager {

    private static LinkedList<Person> listOfPeople = new LinkedList<Person>();

    public PeopleManager() {
    }

    /**
     * Returns the person object given its index.
     *
     * @param personIndex The index of the person to retrieve.
     * @return Returns a Person object of a particular index.
     */
    public Person getPerson(int personIndex) {
        return listOfPeople.get(personIndex);
    }

    /**
     * Adds a person object to the list of people.
     *
     * @param person Person to be added to the list.
     */
    public void addPerson(Person person) {
        listOfPeople.add(person);
    }

    /**
     * Remove a particular person given its index.
     *
     * @param personIndex Index of the person to be removed.
     */
    public void deletePerson(int personIndex) throws LotsException {
        try {
            listOfPeople.remove(personIndex);
        } catch (Exception e) {
            throw new LotsException(e.getMessage());
        }
    }

    /**
     * Returns the name of the person object given the index.
     *
     * @param personIndex The index of the person to retrieve.
     * @return Returns the String name of a particular index.
     */
    public String getName(int personIndex) {
        return listOfPeople.get(personIndex).personName;
    }

    /**
     * Returns the number of people in the linked list.
     *
     * @return Returns the length of the linked list.
     */
    public int getSize() {
        return listOfPeople.size();
    }

    public boolean isEmpty() {
        return listOfPeople.isEmpty();
    }

    /**
     * Get total number of orders for everyone.
     * @return total number of orders for everyone.
     */
    public int getTotalNumberOfOrdersEveryone() {
        int totalQuantity = 0;
        for (int i = 0; i < listOfPeople.size(); i++) {
            totalQuantity = totalQuantity + listOfPeople.get(i).getTotalNumberOfOrders();
        }
        return totalQuantity;
    }

    /**
     * Clears the arraylist of people
     * stored. Mainly used before each test method.
     */
    public static void clearListOfPeople() {
        listOfPeople.clear();
    }

    public LinkedList<Person> getEntireListOfPeople() {
        return listOfPeople;
    }


}
