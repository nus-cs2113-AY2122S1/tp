package seedu.duke;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.ClickfitMessages.HELP_COMMANDS;

//@@author EdwardZYWang
public class UserHelp {
    private static Logger UserHelp_LOGGER = Logger.getLogger("UiLogger");

    public UserHelp() {
        this.UserHelp_LOGGER.setLevel(Level.SEVERE);
    }

    /**
     * Takes in user input to decide whether to print out help commands.
     *
     * @param input user's input
     */
    public static void generateUserHelpParameters(String input) {
        assert !Objects.equals(input, "");

        UserHelp_LOGGER.log(Level.INFO, "print out Duke commands");
        getCommands();
    }

    public static void getCommands() {
        System.out.println(HELP_COMMANDS);
    }



}
