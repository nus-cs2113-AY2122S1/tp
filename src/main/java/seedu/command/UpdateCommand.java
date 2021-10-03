package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;

import java.io.IOException;

public class UpdateCommand extends Command {

    public UpdateCommand() {
    }

    public void execute() {
        try {
            NusMods.update();
        } catch (IOException | ModStorage.FileErrorException e) {
            e.printStackTrace();
        }
    }
}
