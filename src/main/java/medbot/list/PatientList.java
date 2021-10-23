package medbot.list;

import medbot.exceptions.MedBotException;
import medbot.person.Patient;

import java.util.List;

public class PatientList extends PersonList {

    private String getNoPatientIdErrorMessage(int patientId) {
        return "No Patient with ID " + super.getNoPersonIdErrorMessage(patientId);
    }

}