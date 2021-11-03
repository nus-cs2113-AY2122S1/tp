package medbot.storage;

import medbot.exceptions.MedBotException;

public class StaffStorage extends PersonStorage {

    private static final String STAFF_DATA_PATH = "MedBotData/staff.txt";

    /**
     * Instantiate storage at the default path, STAFF_DATA_PATH.
     *
     * @throws MedBotException if unable to create/detect storage file
     */
    public StaffStorage() throws MedBotException {
        super(STAFF_DATA_PATH);
    }

    /**
     * For testing purposes, using a custom data path.
     *
     * @param testDataPath custom data path
     * @throws MedBotException if unable to create/detect storage file
     */
    protected StaffStorage(String testDataPath) throws MedBotException {
        super(testDataPath);
    }

}
