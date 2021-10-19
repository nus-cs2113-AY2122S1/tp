package medbot.list;

import medbot.Parser;
import medbot.exceptions.MedBotException;
import medbot.person.Person;
import medbot.utilities.ViewType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonList {
    private static final String ENDLINE = System.lineSeparator();

    private HashMap<Integer, Person> persons = new HashMap<>();
    private int lastId = 1;

    public int size() {
        return persons.size();
    }

    public int addPerson(Person person) {
        int personId = generatePersonId();
        person.setPersonId(personId);
        persons.put(personId, person);
        return personId;
    }

    public String getPersonInfo(int personId) throws MedBotException {
        if (!persons.containsKey(personId)) {
            throw new MedBotException(getNoPersonIdErrorMessage(personId));
        }
        return persons.get(personId).toString();
    }

    private int generatePersonId() {
        while (persons.containsKey(lastId)) {
            lastId++;
        }
        return lastId;
    }

    /**
     * Replaces all values of the Person data that is non-null in the new inputted data.
     *
     * @param oldPersonData the old Person data in the system
     * @param newPersonData the new Person data inputted by the user
     */
    private void mergeEditPersonData(Person oldPersonData, Person newPersonData) {
        if (newPersonData.getName() != null) {
            oldPersonData.setName(newPersonData.getName());
        }
        if (newPersonData.getIcNumber() != null) {
            oldPersonData.setIcNumber(newPersonData.getIcNumber());
        }
        if (newPersonData.getEmailAddress() != null) {
            oldPersonData.setEmailAddress(newPersonData.getEmailAddress());
        }
        if (newPersonData.getPhoneNumber() != null) {
            oldPersonData.setPhoneNumber(newPersonData.getPhoneNumber());
        }
        if (newPersonData.getResidentialAddress() != null) {
            oldPersonData.setResidentialAddress(newPersonData.getResidentialAddress());
        }
    }

    /**
     * Edits the specified fields on Person information with new values from the user.
     *
     * @param personId      The Person with information to change
     * @param newPersonData the new Person data to change to (except the null fields)
     * @throws MedBotException when the Person ID cannot be found
     */
    public void editPerson(int personId, Person newPersonData) throws MedBotException {
        assert (personId > 0);
        mergeEditPersonData(persons.get(personId), newPersonData);
    }

    /**
     * Deletes the specified Person.
     *
     * @param personId The Person to delete.
     * @throws MedBotException when the Person ID cannot be found.
     */
    public void deletePerson(int personId) throws MedBotException {
        if (!persons.containsKey(personId)) {
            throw new MedBotException(getNoPersonIdErrorMessage(personId));
        }
        assert (personId > 0);
        persons.remove(personId);
    }

    /**
     * Filters persons in the list based on specified attributes.
     *
     * @param parameters The attributes to filter persons.
     * @return The filtered persons.
     */
    public List<String> findPersons(String[] parameters) {
        List<String> filteredPersons = new ArrayList<>();
        for (int key : persons.keySet()) {
            Person currentPerson = persons.get(key);
            if (currentPerson.containsAllParameters(parameters)) {
                filteredPersons.add(currentPerson.getInfoInTableFormat());
            }
        }

        return filteredPersons;
    }

    /**
     * Lists all current persons.
     *
     * @return String displays the list of all current persons
     */
    public String listPersons() {
        String output = "";

        for (int key : persons.keySet()) {
            output += persons.get(key).getInfoInTableFormat() + ENDLINE;
        }

        return output;
    }

    public String getNoPersonIdErrorMessage(int personId) {
        if (Parser.getViewType() == ViewType.PATIENT_INFO) {
            return "No patient with ID " + personId + " found." + ENDLINE;
        }
        return "No person with ID " + personId + " found." + ENDLINE;
    }

    /**
     * Get storageString for all persons.
     *
     * @return storageString of all persons
     */
    public String getStorageString() {
        String output = "";
        for (int key : persons.keySet()) {
            Person person = persons.get(key);
            String personStorageString = person.getStorageString();
            output += personStorageString + ENDLINE;
        }
        return output;
    }

    /**
     * Adds a person to persons hashmap.
     *
     * @param person an instance of Person
     */
    public void addPersonFromStorage(Person person) {
        int personId = person.getPersonId();
        persons.put(personId, person);
    }

    /**
     * Set lastId to a new number.
     *
     * @param newLastId lastId to be set to this
     */
    public void setLastId(int newLastId) {
        lastId = newLastId;
    }
}
