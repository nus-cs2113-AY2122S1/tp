package medbot;

import medbot.list.MedicalStaffList;
import medbot.list.PatientList;

public class Scheduler {
    private final PatientList patientList = new PatientList();
    private final MedicalStaffList medicalStaffList = new MedicalStaffList();

    public PatientList getPatientList() {
        return patientList;
    }

    public MedicalStaffList getMedicalStaffList() {
        return medicalStaffList;
    }
}
