package seedu.duke.common;

import seedu.duke.hospital.Doctor;
import seedu.duke.hospital.Hospital;

import static seedu.duke.common.Messages.TO_STRING_MESSAGE_HIGH_RISK_ELDERLY;

public class HighRiskElderly extends Elderly implements Hospitalisable {

    private Hospital hospital;
    private String conditions;
    private String notesOnCare;
    private Doctor doctor;

    public HighRiskElderly(String username, String name, Hospital hospital, String conditions,
                           String notesOnCare,
                           Doctor doctor) {
        super(username, name);
        this.hospital = hospital;
        this.conditions = conditions;
        this.notesOnCare = notesOnCare;
        this.doctor = doctor;
    }

    @Override
    public Hospital getHospital() {
        return hospital;
    }

    @Override
    public String getConditions() {
        return conditions;
    }

    @Override
    public String getNotesOnCare() {
        return notesOnCare;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public String toString() {
        String hospital = String.format("[*] Hospital Details :\n%s\n", getHospital());
        String conditions = String.format("[*] Conditions of elderly %s : %s\n", getUsername(), getConditions());
        String notesOnCare = String.format("[*] Notes on care for %s : %s\n", getUsername(), getNotesOnCare());
        String doctor = String.format("[*] Doctor for %s :\n%s\n", getUsername(), getDoctor());
        return String.format(TO_STRING_MESSAGE_HIGH_RISK_ELDERLY,
                super.toString(), hospital, conditions, doctor, notesOnCare);
    }
}
