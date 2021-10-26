package seedu.command;

import seedu.unimods.UniMods;
import seedu.user.Profile;

public class TranscriptCommand extends Command {

    public TranscriptCommand() {

    }

    public void execute() {
        Profile currentProfile = UniMods.getProfileInUse();
        currentProfile.showTranscript();
    }
}
