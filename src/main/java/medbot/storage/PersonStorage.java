package medbot.storage;

import medbot.exceptions.MedBotException;
import medbot.list.ListItem;
import medbot.list.ListItemType;
import medbot.person.Patient;
import medbot.person.Person;
import medbot.person.Staff;
import medbot.utilities.Pair;

import java.util.ArrayList;

import static medbot.parser.ParserUtils.updatePersonalInformation;

public abstract class PersonStorage extends Storage {

    private static final String[] parameterPrefixes = {"i/", "n/", "p/", "e/", "a/"};

    /**
     * Generic constructor for PatientStorage and StaffStorage.
     *
     * @param dataPath is the path of the storage file
     * @throws MedBotException if storage file cannot be created and does not exist
     */
    public PersonStorage(String dataPath) throws MedBotException {
        super(dataPath);
    }

    /**
     * Create a ListItem object from the given parameters/details.
     *
     * @param storageLine  a line in storage file
     * @param listItemType enum of ListItem type
     * @return a ListItem object with the given parameters. return null if patientDetails == null
     * @throws MedBotException if fail to create the object
     */
    @Override
    protected ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException {

        Pair<Integer, ArrayList<String>> personDetails = parseStorageLine(storageLine, parameterPrefixes);
        if (personDetails == null) {
            return null;
        }
        int personId = personDetails.first;
        ArrayList<String> prefixPlusPersonParameters = personDetails.second;

        Person person;
        if (listItemType == ListItemType.PATIENT) {
            person = new Patient();
        } else {
            person = new Staff();
        }

        person.setId(personId);
        for (String prefixPlusPersonParameter : prefixPlusPersonParameters) {
            //updatePersonalInformation does error-checking of person details and updates patient info
            updatePersonalInformation(person, prefixPlusPersonParameter);
        }

        return person;
    }


}
