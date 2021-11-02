package seedu.command;

import seedu.unimods.UniMods;
import seedu.user.Profile;

public class TranscriptCommand extends Command {

    public static final String commandSyntax = "transcript";
    public static final String commandAction = "Creates and displays an unofficial transcript";

    public TranscriptCommand() {

    }

    public void execute() {
        Profile currentProfile = UniMods.getProfileInUse();
        currentProfile.showTranscript();
    }
}
