package medbot.storage;

import medbot.Appointment;
import medbot.exceptions.MedBotException;
import medbot.list.ListItem;
import medbot.list.ListItemType;
import medbot.utilities.Pair;

import java.util.ArrayList;

import static medbot.parser.ParserUtils.updateAppointmentInformation;

public class AppointmentStorage extends Storage {
    private static final String APPOINTMENT_DATA_PATH = "MedBotData/appointment.txt";
    private static final String[] parameterPrefixes = {"d/", "p/", "s/"};

    public AppointmentStorage() throws MedBotException {
        super(APPOINTMENT_DATA_PATH);
    }


    public AppointmentStorage(String testDataPath) throws MedBotException {
        super(testDataPath);
    }

    /**
     * Instantiates a ListItem interfaced object.
     *
     * @param storageLine  a line in storage file
     * @param listItemType enum of ListItem type
     * @return a ListItem interfaced object
     * @throws MedBotException if fail to instantiate a ListItem object
     */
    @Override
    protected ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException {
        Pair<Integer, ArrayList<String>> appointmentDetails = parseStorageLine(storageLine);
        if (appointmentDetails == null) {
            return null;
        }
        int appointmentId = appointmentDetails.first;
        ArrayList<String> prefixPlusAppointmentParameters = appointmentDetails.second;

        Appointment appointment = new Appointment();

        appointment.setId(appointmentId);
        for (String prefixPlusAppointmentParameter : prefixPlusAppointmentParameters) {
            updateAppointmentInformation(appointment, prefixPlusAppointmentParameter);
        }

        return appointment;
    }

    /**
     * Parse a line from the storage file by splitting its constituent parts.
     *
     * @param storageLine a line from the storage file
     * @return listItem details, consisting of person ID and other parameters
     */
    protected Pair<Integer, ArrayList<String>> parseStorageLine(String storageLine) {
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
        assert listItemParameters.length == parameterPrefixes.length + 1;

        return new Pair<>(listItemId, prefixPlusListItemParameters);
    }
}
