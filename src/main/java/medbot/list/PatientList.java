package medbot.list;

import static medbot.ui.Ui.END_LINE;

public class PatientList extends PersonList {

    @Override
    protected String getPersonNotFoundErrorMessage(int patientId) {
        return "No Patient with ID " + patientId + " found." + END_LINE;
    }

    @Override
    protected String getAlreadyHiddenErrorMessage(int patientId) {
        return "The patient with ID " + patientId + " is already hidden." + END_LINE;
    }

    @Override
    protected String getAlreadyShownErrorMessage(int patientId) {
        return "The patient with ID " + patientId + " is already not-hidden." + END_LINE;
    }

    @Override
    protected String getIcIsDuplicate(String icNumber) {
        return "The patient with IC" + icNumber + " is already in the record." + END_LINE;
    }
}