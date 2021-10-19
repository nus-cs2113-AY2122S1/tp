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

    public void executeCommand(Ui ui, Storage storage, Command command) throws MedBotException {
        switch (viewType) {
        case SCHEDULER:
            //PlaceholderCode for the switch command to work
            command.execute(patientList,ui);
            break;
        case PATIENT_INFO:
            command.execute(patientList, ui);
            storage.saveData(patientList);
            break;
        case MEDICAL_STAFF_INFO:
            command.execute(staffList, ui);
            //Add a saveData here
            break;
        default:
        }
    }

    public static void setViewType(ViewType viewType) {
        CommandManager.viewType = viewType;
    }

    public static ViewType getViewType() {
        return viewType;
    }

    /**
     * Switches the view type of the parser.
     */
    public static void switchViewType() {
        switch (viewType) {
        case PATIENT_INFO:
            viewType = ViewType.MEDICAL_STAFF_INFO;
            break;
        case MEDICAL_STAFF_INFO:
            viewType = ViewType.SCHEDULER;
            break;
        case SCHEDULER:
            viewType = ViewType.PATIENT_INFO;
            break;
        default:
        }
    }
}
