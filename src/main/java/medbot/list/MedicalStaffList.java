package medbot.list;

public class MedicalStaffList extends PersonList {

    private String getNoStaffIdErrorMessage(int patientId) {
        return "No Staff with ID " + super.getNoPersonIdErrorMessage(patientId);
    }
}
