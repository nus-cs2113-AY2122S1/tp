package medbot;

import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.utilities.ViewType;

public class CommandManager {
    private PatientList patientList;
    private MedicalStaffList staffList;
    private static ViewType viewType;

    public CommandManager(PatientList patientList, MedicalStaffList staffList) {
        this.patientList = patientList;
        this.staffList = staffList;
        viewType = ViewType.PATIENT_INFO;
    }


}
