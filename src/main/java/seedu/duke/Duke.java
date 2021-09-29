package seedu.duke;

import seedu.command.Command;
import seedu.module.ModList;
import seedu.parser.CommandParser;
import seedu.parser.NusModsParser;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;

public class Duke {
    private static String path = "data/Modules.json";
    public static CommandParser commandParser = new CommandParser();
    public ModList modList;
    public ModStorage modStorage;

    public static void main(String[] args) throws IOException, InterruptedException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        new Duke().setup();
    }

    public void setup() throws IOException, InterruptedException {
        this.modList = new ModList();
        this.modStorage = new ModStorage(path);
        TextUi.printWelcomeMessage();
        NusModsParser.loadSave(modList);
        run();
    }

    public void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand());
            executeCommand(command);
        } while (!command.isExit());
    }

    private void executeCommand(Command command) {
        command.setData(modList);
        command.execute();
    }
}
