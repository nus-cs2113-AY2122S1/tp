package medbot.list;

import medbot.exceptions.MedBotException;
import medbot.person.Staff;

public class MedicalStaffList extends PersonList {

    private String getNoStaffIdErrorMessage(int patientId) {
        return "No Staff with ID " + super.getNoPersonIdErrorMessage(patientId);
    }
}
