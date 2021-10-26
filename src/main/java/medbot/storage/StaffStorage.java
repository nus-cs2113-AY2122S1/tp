package medbot.storage;

import medbot.exceptions.MedBotException;

public class StaffStorage extends PersonStorage {

    private static final String STAFF_DATA_PATH = "MedBotData/staff.txt";

    public StaffStorage() throws MedBotException {
        super(STAFF_DATA_PATH);
    }

}
