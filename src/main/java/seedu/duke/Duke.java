package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.storage.StorageFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Event> eventList = new ArrayList<>();
    public static ArrayList<Task> taskList = new ArrayList<>();
    private static final StorageFile storage = new StorageFile();

    public static void main(String[] args) {
        Ui.printGreetingMessage();
        try {
            storage.load(eventList, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Oooh a new user!");
        }
        runSlam();
        storage.save(eventList, taskList);
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
