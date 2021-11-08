package seedu.duke.commands.clients;

import seedu.duke.commands.Command;

/**
 * Finds client(s) based on a certain substring.
 */
public class FindClientCommand extends Command {
    private final String substring;

    /**
     * Class constructor for FindClientCommand.
     *
     * @param substring substring used to determine which client(s) to find.
     */
    public FindClientCommand(String substring) {
        this.substring = substring;
    }

    /**
     * Executes the finding of client, as well as finding said client's packages.
     * If there are no found clients, it will be shown to the user.
     */
    public void execute() {
        ui.showFindClient(clients, clientPackages, substring);
    }
}
