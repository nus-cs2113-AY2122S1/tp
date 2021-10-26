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

    /**
     * Instantiates a ListItem object.
     *
     * @param storageLine  a line in storage file
     * @param listItemType enum of ListItem type
     * @return a ListItem object
     * @throws MedBotException if fail to instantiate a ListItem object
     */
    @Override
    protected ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException {
        Pair<Integer, ArrayList<String>> appointmentDetails = parseStorageLine(storageLine, parameterPrefixes);
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

}
