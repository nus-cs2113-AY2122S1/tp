package seedu.duke.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private static HashMap<String, String> commandOptions = new HashMap<>();

    private final String commandArguments = "cs2113 tp project --type deadline --start 22-09-2021"
            + " --end 29-09-2021 --recur weekly --priority 2";

    private final String expectedSplit = "--type = deadline\n"
            + "--recur = weekly\n"
            + "--end = 29-09-2021\n"
            + "--priority = 2\n"
            + "mainArgument = cs2113 tp project\n"
            + "--start = 22-09-2021\n";

    @Test
    @DisplayName("When we check the command is able to split all fields properly")
    void parse_commandArguments_checkIfSplitCorrectly() {
        commandOptions = CommandParser.getCommandOptions(commandArguments);

        assertEquals(expectedSplit, CommandParser.printCommandOptions(commandOptions));
    }
}