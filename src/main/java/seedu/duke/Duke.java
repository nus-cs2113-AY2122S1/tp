package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.Item;

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Item> itemList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("WASSUP DAWG"); //ui.introMessage();
        Ui.linebreak();
        //ui.loadFile();
        runSlam();
        //ui.saveFile();
        System.out.println("seeya bitches"); //ui.byeMessage();
    }

    protected static void runSlam() {
        String userInput;
        Command command;
        CommandResult feedback;

        do {
            userInput = Ui.readInput();
            Ui.linebreak();
            command = Parser.parseCommand(userInput);
            feedback = command.execute();
            System.out.println(feedback.feedbackToUser);
            Ui.linebreak();
        } while (ByeCommand.isRunning);
    }
}
