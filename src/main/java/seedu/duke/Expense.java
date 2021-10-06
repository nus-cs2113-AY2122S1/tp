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

    public void addPersons(ArrayList<Person> newListOfPersons) {
        listOfPersons.clear();
        Person personToAdd;
        for (int i = 0; i < newListOfPersons.size(); i++) {
            personToAdd = newListOfPersons.get(i);
            listOfPersons.add(personToAdd);
        }
    }

}
