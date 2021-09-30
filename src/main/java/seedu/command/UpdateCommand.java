package seedu.command;

import seedu.parser.NusModsParser;

import java.io.IOException;

public class UpdateCommand extends Command {

    public UpdateCommand() {
    }

    public void execute() {
        try {
            NusModsParser.update(modList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
