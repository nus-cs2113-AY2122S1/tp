package medbot.Storage;

import medbot.exceptions.MedBotException;
import medbot.person.Patient;
import medbot.person.Person;
import medbot.person.Staff;

public class StaffStorage extends PersonStorage {

    private static final String STAFF_DATA_PATH = "storage/staff.txt";

    public StaffStorage() throws MedBotException {
        super(STAFF_DATA_PATH);
    }

}
