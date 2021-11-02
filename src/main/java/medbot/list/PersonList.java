package medbot.list;

import medbot.Appointment;
import medbot.exceptions.MedBotException;
import medbot.person.Person;
import medbot.utilities.FilterType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static medbot.ui.Ui.END_LINE;


public abstract class PersonList {

    //Sorted to ensure that persons will always be printed in ascending order of ID when storage is manipulated
    private final SortedMap<Integer, Person> persons = new TreeMap<>();
    private int lastId = 1;

    /**
     * Adds the given person into the person list, allocates an id to the person and returns the id value.
     *
     * @param person Person to be added into the list
     * @return personId that was allocated to the person
     */
    public int addPerson(Person person) {
        int personId = person.getId();

        //if person not created from storage data
        if (personId <= 0) {
            personId = generatePersonId();
            person.setId(personId);
        }
        persons.put(personId, person);
        return personId;
    }

    /**
     * Generates a non-random but unique id to be allocated to a person.
     *
     * @return a unique id to be allocated to a person
     */
    private int generatePersonId() {
        while (persons.containsKey(lastId)) {
            lastId++;
        }

        return lastId;
    }

    /**
     * Returns a String containing the information of the person with the specified personId.
     *
     * @param personId the id of the person to search for
     * @return a String containing the person's information
     * @throws MedBotException if there is no person with that id
     */
    public String getPersonInfo(int personId) throws MedBotException {
        checkPersonExists(personId);
        return persons.get(personId).toString();
    }

    /**
     * Returns a String containing the information of the person with the specified personId.
     *
     * @param personId the id of the person to search for
     * @return a String containing the person's information
     * @throws MedBotException if there is no person with that id
     */
    public String getPersonName(int personId) throws MedBotException {
        checkPersonExists(personId);
        return persons.get(personId).getName();
    }

    /**
     * Edits the specified fields on Person information with new values from the user.
     *
     * @param personId      The Person with information to change
     * @param newPersonData the new Person data to change to (except the null fields)
     * @throws MedBotException when the Person ID cannot be found
     */
    public void editPerson(int personId, Person newPersonData) throws MedBotException {
        checkPersonExists(personId);
        assert (personId > 0);
        mergeEditPersonData(persons.get(personId), newPersonData);
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
     * Deletes the specified Person.
     *
     * @param personId The Person to delete.
     * @throws MedBotException when the Person ID cannot be found.
     */
    public void deletePerson(int personId) throws MedBotException {
        checkPersonExists(personId);
        assert (personId > 0);
        persons.remove(personId);
    }

    /**
     * Filters persons in the list based on specified attributes.
     *
     * @param parameters The attributes to filter persons.
     * @return The filtered persons.
     */
    public List<String> findPersons(String[] parameters) throws MedBotException {
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
     * Returns a String that contains information of all hiddren or not-hidden persons.
     *
     * @param getHiddenPersons The boolean to indicate whether to get hidden or not-hidden persons.
     * @return String that contains information of all hidden or not-hidden persons
     */
    public String listPersons(boolean getHiddenPersons) {
        String output = "";

        for (int key : persons.keySet()) {
            boolean isPersonHidden = persons.get(key).isHidden();

            if (isPersonHidden == getHiddenPersons) {
                output += persons.get(key).getInfoInTableFormat() + END_LINE;
            }
        }

        return output;
    }

    /**
     * Hide a person in the list.
     *
     * @param personId the id of the person to hide.
     * @throws MedBotException when the person have already been hidden.
     */
    public void hidePerson(int personId) throws MedBotException {
        checkPersonExists(personId);
        boolean isPersonHidden = persons.get(personId).isHidden();

        if (isPersonHidden) {
            throw new MedBotException(getAlreadyHiddenErrorMessage(personId));
        }
        persons.get(personId).hide();
    }

    /**
     * Shows a hidden person in the list.
     *
     * @param personId the id of the person to show.
     * @throws MedBotException when the person is not hidden.
     */
    public void showPerson(int personId) throws MedBotException {
        checkPersonExists(personId);
        boolean isPersonHidden = persons.get(personId).isHidden();

        if (!isPersonHidden) {
            throw new MedBotException(getAlreadyShownErrorMessage(personId));
        }
        persons.get(personId).show();
    }

    /**
     * For the person with the specified personId, returns the appointmentId of the appointment at the specified
     * time code, or -1 if there is none.
     *
     * @param personId     the id of the person to search for
     * @param dateTimeCode the dateTimeCode to search for
     * @return the appointmentId of the appointment with that dateTimeCode, or -1 if there is none
     * @throws MedBotException if there is no person with the specified personId
     */
    public int getAppointmentId(int personId, int dateTimeCode) throws MedBotException {
        checkPersonExists(personId);
        return persons.get(personId).getAppointmentId(dateTimeCode);
    }

    /**
     * For the person with the specified personId, returns an LinkedList of the appointmentId of all appointments.
     *
     * @param personId the id of the person to search for
     * @return LinkedList of the appointmentId of all appointments for the specified person
     * @throws MedBotException if there is no person with the specified personId
     */
    public LinkedList<Integer> getAllAppointmentIds(int personId) throws MedBotException {
        checkPersonExists(personId);
        return persons.get(personId).getAllAppointmentIds();
    }

    /**
     * For the person with the specified personId, adds the appointment into their appointment list.
     *
     * @param personId    the id of the person to whom the appointment will be added
     * @param appointment the appointment to be added
     * @throws MedBotException if there is no person with the specified personId
     */
    public void addAppointment(int personId, Appointment appointment) throws MedBotException {
        checkPersonExists(personId);
        persons.get(personId).addAppointment(appointment);
    }

    /**
     * For the person with the specified personId, deletes the appointment with the specified dateTimeCode.
     *
     * @param personId     the id of the person whose appointment will be deleted
     * @param dateTimeCode the dateTimeCode of the appointment to be deleted
     * @throws MedBotException if there is no person with the specified personId, or if there is no appointment at
     *                         the specified dateTimeCode for that person
     */
    public void deleteAppointment(int personId, int dateTimeCode) throws MedBotException {
        checkPersonExists(personId);
        persons.get(personId).deleteAppointment(dateTimeCode);
    }

    /**
     * Returns a String containing the information of the appointments of the person with the specified personId.
     *
     * @param personId the id of the person whose appointments will be listed
     * @return a String containing the information of the person's appointments
     * @throws MedBotException if there is no person with the specified personId
     */
    public List<Integer> listAppointments(int personId, FilterType filterType, int dateTimeCode)
            throws MedBotException {
        checkPersonExists(personId);
        return persons.get(personId).listAppointments(filterType, dateTimeCode);
    }

    /**
     * Checks if a person with the specified id is present in the list.
     *
     * @param personId the id of the person to search for
     * @throws MedBotException if there is no person with the specified personId
     */
    private void checkPersonExists(int personId) throws MedBotException {
        if (!persons.containsKey(personId)) {
            throw new MedBotException(getPersonNotFoundErrorMessage(personId));
        }
    }

    /**
     * Generates the exception message for MedBotExceptions when no person with the specified id is found.
     *
     * <p>Is overrode by subclasses
     *
     * @param personId id of the person who was not found
     * @return exception message when no person with the specified id is found
     */
    protected abstract String getPersonNotFoundErrorMessage(int personId);

    /**
     * Generates the exception message for MedBotExceptions when the person is already hidden.
     *
     * <p>Is overrode by subclasses
     *
     * @param personId id of the person who is already hidden
     * @return exception message when the person is already hidden
     */
    protected abstract String getAlreadyHiddenErrorMessage(int personId);

    /**
     * Generates the exception message for MedBotExceptions when the person is already shown.
     *
     * <p>Is overrode by subclasses
     *
     * @param personId id of the person who is already shown
     * @return exception message when the person is already shown
     */
    protected abstract String getAlreadyShownErrorMessage(int personId);


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
            output += personStorageString + END_LINE;
        }
        return output;
    }

    /**
     * Set lastId to newLastId.
     *
     * @param newLastId lastId to be set to this
     */
    public void setLastId(int newLastId) {
        lastId = newLastId;
    }

    public int getLastId() {
        return lastId;
    }

}
