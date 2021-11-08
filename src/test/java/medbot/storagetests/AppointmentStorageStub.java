package medbot.storagetests;

import medbot.exceptions.MedBotException;
import medbot.storage.AppointmentStorage;

public class AppointmentStorageStub extends AppointmentStorage {

    public AppointmentStorageStub(String testDataPath) throws MedBotException {
        super(testDataPath);
    }
}
