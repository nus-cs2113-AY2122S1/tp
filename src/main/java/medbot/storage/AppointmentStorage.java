package medbot.storage;

import medbot.Appointment;
import medbot.exceptions.MedBotException;
import medbot.list.ListItem;
import medbot.list.ListItemType;
import medbot.utilities.Pair;

import java.util.ArrayList;

import static medbot.parser.ParserUtils.updateAppointmentInformation;

public class AppointmentStorage extends Storage {
    private static final String APPOINTMENT_DATA_PATH = "MedbotData/appointment.txt";

    public AppointmentStorage() throws MedBotException {
        super(APPOINTMENT_DATA_PATH);
    }

    @Override
    protected ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException {
        Pair<Integer, ArrayList<String>> personDetails = parseStorageLine(storageLine);
        if (personDetails == null) {
            return null;
        }
        int personId = personDetails.first;
        ArrayList<String> prefixPlusAppointmentParameters = personDetails.second;

        Appointment appointment = new Appointment();

        appointment.setId(personId);
        for (String prefixPlusAppointmentParameter : prefixPlusAppointmentParameters) {
            //updatePersonalInformation does error-checking of person details and updates patient info
            updateAppointmentInformation(appointment, prefixPlusAppointmentParameter);
        }

        return appointment;
    }

    protected Pair<Integer, ArrayList<String>> parseStorageLine(String storageLine) {
        if (storageLine.isBlank()) {
            return null;
        }

        String[] appointmentParameters = splitStorageLine(storageLine);
        String[] parameterPrefixes = {"d/", "p/", "s/"};

        ArrayList<String> prefixPlusAppointmentParameters = new ArrayList<>();

        Integer appointmentId = Integer.parseInt(appointmentParameters[0]);

        for (int i = 0; i < parameterPrefixes.length; i++) {
            // i + 1, since appointmentParameters[0] is the appointmentId
            if (isStorageParameterNull(appointmentParameters[i + 1])) {
                continue;
            }
            // i + 1, since appointmentParameters[0] is the appointmentId
            String prefixPlusAppointmentParameter = parameterPrefixes[i] + appointmentParameters[i + 1];
            prefixPlusAppointmentParameters.add(prefixPlusAppointmentParameter);
        }

        assert appointmentParameters.length == parameterPrefixes.length + 1;

        return new Pair<>(appointmentId, prefixPlusAppointmentParameters);
    }
}
