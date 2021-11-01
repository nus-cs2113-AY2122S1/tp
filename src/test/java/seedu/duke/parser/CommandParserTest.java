package seedu.duke.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandParserTest {

    private static Map<String, String> commandOptions = new HashMap<>();

    private final String commandArguments = "cs2113 tp project --type deadline --start 22-09-2021"
            + " --end 29-09-2021 --recur weekly --priority 2";

    private final String expectedSplit = "start = 22-09-2021\n"
            + "recur = weekly\n"
            + "end = 29-09-2021\n"
            + "type = deadline\n"
            + "priority = 2\n"
            + "mainArgument = cs2113 tp project\n";

    //@@author APZH
    // Used to debug and check the whether the user command mapping of flag->value works
    public static String printCommandOptions(Map<String, String> commandOptions) {

        String flagsToArguments = "";

        for (String flag : commandOptions.keySet()) {
            flagsToArguments += flag + " = " + commandOptions.get(flag) + "\n";
        }

        System.out.println(flagsToArguments);

        return flagsToArguments;
    }

    @Test
    @DisplayName("When we check the command is able to split all fields properly")
    void parse_commandArguments_checkIfSplitCorrectly() {
        commandOptions = CommandParser.getCommandOptions(commandArguments);

        assertEquals(expectedSplit, printCommandOptions(commandOptions));
    }
}