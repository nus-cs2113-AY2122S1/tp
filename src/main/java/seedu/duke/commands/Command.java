package seedu.duke.commands;

import seedu.duke.storage.Storage;

public abstract class Command {
    protected Storage storage;

    public Command() {
        this.storage = new Storage();
    }
}