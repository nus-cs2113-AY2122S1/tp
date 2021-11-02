package medbot.storage;

import medbot.exceptions.MedBotException;
import medbot.list.ListItem;
import medbot.list.ListItemType;
import medbot.person.Patient;
import medbot.person.Person;
import medbot.person.Staff;
import medbot.utilities.Triple;

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

        Triple<Integer, Boolean, ArrayList<String>> personDetails = parseStorageLine(storageLine);
        if (personDetails == null) {
            return null;
        }
        Person person;
        switch (listItemType) {
        case PATIENT:
            person = new Patient();
            break;
        case STAFF:
            person = new Staff();
            break;
        default:
            throw new MedBotException("Invalid listItemType");
        }

        int personId = personDetails.first;
        boolean isArchived = personDetails.second;
        ArrayList<String> prefixPlusPersonParameters = personDetails.third;

        person.setId(personId);
        if (isArchived) {
            person.archive();
        }

        for (String prefixPlusPersonParameter : prefixPlusPersonParameters) {
            //updatePersonalInformation does error-checking of person details and updates patient info
            updatePersonalInformation(person, prefixPlusPersonParameter);
        }

        return person;
    }

    /**
     * Parse a line from the storage file by splitting its constituent parts.
     *
     * @param storageLine a line from the storage file
     * @return listItem details, consisting of person ID and other parameters
     */
    protected Triple<Integer, Boolean, ArrayList<String>> parseStorageLine(String storageLine)
            throws MedBotException {
        if (storageLine.isBlank()) {
            return null;
        }

        String[] listItemParameters = splitStorageLine(storageLine);
        ArrayList<String> prefixPlusListItemParameters = new ArrayList<>();
        Integer listItemId = Integer.parseInt(listItemParameters[0]);

        for (int i = 0; i < parameterPrefixes.length; i++) {
            // i + 1, since listItemParameters[0] is the listItemId
            if (isStorageParameterNull(listItemParameters[i + 1])) {
                continue;
            }
            // i + 1, since listItemParameters[0] is the listItemId
            String prefixPlusListItemParameter = parameterPrefixes[i] + listItemParameters[i + 1];
            prefixPlusListItemParameters.add(prefixPlusListItemParameter);
        }

        int listItemParametersLastIndex = listItemParameters.length - 1;
        String isArchivedParameter = listItemParameters[listItemParametersLastIndex];
        boolean isArchived = convertStorageArchiveParameterToBoolean(isArchivedParameter);

        return new Triple<>(listItemId, isArchived, prefixPlusListItemParameters);
    }


    protected static boolean convertStorageArchiveParameterToBoolean(String storageArchiveParameter)
            throws MedBotException {
        if (storageArchiveParameter.equals("U")) {
            return false;
        } else if (storageArchiveParameter.equals("A")) {
            return true;
        } else {
            throw new MedBotException("Invalid storage archive parameter");
        }
    }
}
