package seedu.duke.storage;

import seedu.duke.command.Command;
import seedu.duke.parser.CommandParser;
import seedu.duke.task.taskmanager.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static final String LIST_STORAGE_FILE = "./data/task.txt";

    public static void initializeList(TaskManager taskManager) throws FileNotFoundException {
        File f = new File(LIST_STORAGE_FILE);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Command inputCommand = CommandParser.parseCommand(taskManager, s.nextLine());
        }
    }
}
