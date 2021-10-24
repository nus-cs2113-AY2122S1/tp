package medbot.Storage;

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

    public PersonStorage(String dataPath) throws MedBotException {
        super(dataPath);
    }

    /**
     * Create a patient object from the given parameters/details.
     *
     * @return a patient object with the given parameters. return null if patientDetails == null
     * @throws MedBotException if fail to update patient's personal information
     */
    @Override
    protected ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException {

        Pair<Integer, ArrayList<String>> personDetails = parseStorageLine(storageLine);
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


    /**
     * Parse a line from storage/data.txt by splitting its constituent parts.
     *
     * @param storageLine a line from storage/data.txt
     * @return patient details, consisting of patient ID and other parameters
     */
    protected Pair<Integer, ArrayList<String>> parseStorageLine(String storageLine) {
        if (storageLine.isBlank()) {
            return null;
        }

        String[] personParameters = splitStorageLine(storageLine);
        String[] parameterPrefixes = {"i/", "n/", "p/", "e/", "a/"};

        ArrayList<String> prefixPlusPersonParameters = new ArrayList<>();

        Integer patientId = Integer.parseInt(personParameters[0]);

        for (int i = 0; i < parameterPrefixes.length; i++) {
            // i + 1, since personParameters[0] is the patientId
            if (isStorageParameterNull(personParameters[i + 1])) {
                continue;
            }
            // i + 1, since personParameters[0] is the patientId
            String prefixPlusPersonParameter = parameterPrefixes[i] + personParameters[i + 1];
            prefixPlusPersonParameters.add(prefixPlusPersonParameter);
        }

        assert personParameters.length == parameterPrefixes.length + 1;

        return new Pair<>(patientId, prefixPlusPersonParameters);
    }

}
