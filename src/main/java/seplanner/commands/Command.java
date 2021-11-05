package seplanner.commands;

import seplanner.storage.Storage;

/**
 * An abstract command class to import the storage.
*/
public abstract class Command {
    protected Storage storage;

    public Command() {
        this.storage = new Storage();
    }
}
