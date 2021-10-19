package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.storage.StorageFile;

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Event> eventCatalog = new ArrayList<>();
    /*TODO: Delete the ArrayList of tasks below once the project has been restructured to fully utilize the new
    *  ArrayList of Task objects within each Event*/
    public static ArrayList<Task> taskList = new ArrayList<>();
    private static final StorageFile storage = new StorageFile();

    public static void main(String[] args) {

        Ui.printGreetingMessage();
        storage.load(eventCatalog);
        runSlam();
        storage.save(eventCatalog);
    }

    protected static void runSlam() {
        String userInput;
        Command command;
        CommandResult feedback;

        do {
            userInput = Ui.readInput();
            Ui.printLineBreak();
            command = Parser.parseCommand(userInput);
            feedback = command.execute();
            System.out.println(feedback.feedbackToUser);
            Ui.printLineBreak();
        } while (ByeCommand.isRunning);
    }
}
