package cooper.command;

import cooper.exceptions.InvalidAccessException;
import cooper.resources.ResourcesManager;
import cooper.storage.StorageManager;
import cooper.verification.SignInDetails;

public class BsCommand extends Command {

    public BsCommand() {
        super();
    }

    @Override
    public void execute(SignInDetails signInDetails, ResourcesManager resourcesManager,
                        StorageManager storageManager) throws InvalidAccessException {

    }
}
