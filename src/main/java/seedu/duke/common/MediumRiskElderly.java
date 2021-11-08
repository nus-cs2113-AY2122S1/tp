package seedu.duke.common;

import seedu.duke.hospital.Hospital;

import static seedu.duke.common.Messages.TO_STRING_MESSAGE_MEDIUM_RISK_ELDERLY;

public class MediumRiskElderly extends Elderly implements Hospitalisable {

    private Hospital hospital;
    private String conditions;
    private String notesOnCare;

    public MediumRiskElderly(String username, String name, Hospital hospital, String conditions,
                             String notesOnCare) {
        super(username, name);
        this.hospital = hospital;
        this.conditions = conditions;
        this.notesOnCare = notesOnCare;
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

    @Override
    public String toString() {
        String hospital = String.format("Hospital Details :\n%s\n", getHospital());
        String conditions = String.format("Conditions of elderly %s : %s\n", getUsername(), getConditions());
        String notesOnCare = String.format("Notes on care for %s : %s\n", getUsername(), getNotesOnCare());
        return String.format(TO_STRING_MESSAGE_MEDIUM_RISK_ELDERLY,
                super.toString(), hospital, conditions, notesOnCare);
    }

}
