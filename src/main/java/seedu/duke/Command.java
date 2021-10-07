package seedu.duke;

public abstract class Command {
  
    public abstract void execute(ClientList client, Ui ui);

    public boolean isExit() {
        return false;
    }
}
