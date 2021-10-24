package medbot.list;

import static medbot.ui.Ui.END_LINE;

public class MedicalStaffList extends PersonList {

    @Override
    protected String getPersonNotFoundErrorMessage(int patientId) {
        return "No Staff with ID " + patientId + " found." + END_LINE;
    }
}
