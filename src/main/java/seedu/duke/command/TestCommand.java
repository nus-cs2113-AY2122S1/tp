package seedu.duke;

import seedu.duke.command.Command;

import java.util.ArrayList;

public class TestCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        System.out.println("This is a test");
    }
}
