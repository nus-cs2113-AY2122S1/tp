package seedu.command;

import seedu.duke.Duke;
import seedu.user.Profile;

public class TranscriptCommand extends Command {

    public TranscriptCommand() {

    }

    public void execute() {
        Profile currentProfile = Duke.getProfileInUse();
        currentProfile.showTranscript();
    }
}
