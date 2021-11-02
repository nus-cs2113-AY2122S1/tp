package medbot.list;

import static medbot.ui.Ui.END_LINE;

public class PatientList extends PersonList {

    @Override
    protected String getPersonNotFoundErrorMessage(int patientId) {
        return "No Patient with ID " + patientId + " found." + END_LINE;
    }

    @Override
    protected String getAlreadyArchivedErrorMessage(int patientId) {
        return "The patient with ID " + patientId + " is already archived." + END_LINE;
    }

    @Override
    protected String getAlreadyUnarchivedErrorMessage(int patientId) {
        return "The patient with ID " + patientId + " is already unarchived." + END_LINE;
    }

    @Override
    protected String getIcIsDuplicate(String icNumber) {
        return "The patient with IC" + icNumber + " is already in the record." + END_LINE;
    }
}