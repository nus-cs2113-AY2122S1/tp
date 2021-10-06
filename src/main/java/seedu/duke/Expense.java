package seedu.duke;

import java.util.ArrayList;

public class Expense {
    private String location;
    private ArrayList<Person> listOfPersons;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addPerson(Person personToAdd) {
        listOfPersons.add(personToAdd);
    }

}
