package seplanner.commands;

import seplanner.storage.Storage;

public abstract class Command {
    protected Storage storage;

    public Command() {
        this.storage = new Storage();
    }
}