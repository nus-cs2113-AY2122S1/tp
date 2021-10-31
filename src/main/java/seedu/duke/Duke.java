package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.mainlists.EventCatalog;
import seedu.duke.items.Task;
import seedu.duke.items.mainlists.MemberRoster;
import seedu.duke.parser.Parser;
import seedu.duke.storage.StorageFile;

import java.util.ArrayList;

public class Duke {

    public static EventCatalog eventCatalog = EventCatalog.getInstance();
    public static MemberRoster memberRoster = MemberRoster.getInstance();
    /*TODO: Delete the ArrayList of tasks below once the project has been restructured to fully utilize the new
     *  ArrayList of Task objects within each Event*/
    public static ArrayList<Task> taskList = new ArrayList<>();
    private static final StorageFile storage = new StorageFile();

    public static void main(String[] args) {

        Ui.printGreetingMessage();
        storage.load(memberRoster, eventCatalog);
        runSlam();
        storage.save(memberRoster, eventCatalog);
    }

    protected static void runSlam() {

        String userInput;
        Command command;
        CommandResult feedback;

        do {
            try {
                userInput = Ui.readInput();
                Ui.printLineBreak();
                command = Parser.parseCommand(userInput);
                feedback = command.execute();
                System.out.println(feedback.feedbackToUser);
                Ui.printLineBreak();
            } catch (NullPointerException | NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("Please key in the commands accurately, use help to view the guide");
                Ui.printLineBreak();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printLineBreak();
            } catch (InvalidBudgetException | NoCommandAttributesException e) {
                System.out.println(e.getMessage());
            }
        } while (ByeCommand.isRunning);

    }
}
