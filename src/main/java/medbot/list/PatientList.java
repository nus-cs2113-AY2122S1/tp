package medbot.list;

import static medbot.ui.Ui.END_LINE;

public class PatientList extends PersonList {

    @Override
    protected String getPersonNotFoundErrorMessage(int patientId) {
        return "No Patient with ID " + patientId + " found." + END_LINE;
    }

}