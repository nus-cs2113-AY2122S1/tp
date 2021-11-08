package seedu.duke.common;

import seedu.duke.hospital.Hospital;

public interface Hospitalisable {
    Hospital getHospital();

    String getConditions();

    String getNotesOnCare();
}
