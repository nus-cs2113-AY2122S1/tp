package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.mainlists.EventCatalog;
import seedu.duke.items.mainlists.MemberRoster;
import seedu.duke.parser.Parser;
import seedu.duke.storage.StorageFile;

public class Duke {

    public static EventCatalog eventCatalog = EventCatalog.getInstance();
    public static MemberRoster memberRoster = MemberRoster.getInstance();
    private static final StorageFile storage = new StorageFile();

    public static void main(String[] args) {

        Ui.printGreetingMessage();
        storage.loadSaveFile(memberRoster, eventCatalog);
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
                storage.save(memberRoster, eventCatalog);
            } catch (NullPointerException | NumberFormatException | StringIndexOutOfBoundsException e) {
                Ui.printLineBreak();
                System.out.println("Returning to Main Page...");
                Ui.printLineBreak();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printLineBreak();
            } catch (NoCommandAttributesException | InvalidItemTypeException e) {
                System.out.println(e.getMessage());
            }
        } while (ByeCommand.isRunning);

    }
}
