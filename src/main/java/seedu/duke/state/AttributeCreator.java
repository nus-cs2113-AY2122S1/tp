package seedu.duke.state;

import seedu.duke.logic.Statistics;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AttributeCreator {


    public static final int STATUS = 0;
    protected Ui ui;
    protected static Logger logger = Logger.getLogger(AttributeCreator.class.getName());

    AttributeCreator(Ui ui) {
        this.ui = ui;
    }

    // Followed the format of Bye command to standardise bye message.
    private static final String MESSAGE_SUCCESS = "Exiting Fitbot...." + Ui.LS
            + "Bye! Hope to see you again soon!!";

    protected boolean isBye(String userInput) {
        logger.log(Level.FINE, String.valueOf(userInput.toLowerCase().equals("bye")));
        return userInput.toLowerCase().equals("bye");
    }

    protected void exit() {
        logger.log(Level.FINE, "exiting....");
        ui.formatMessageFramedWithDivider(MESSAGE_SUCCESS);
        System.exit(STATUS);
    }

    protected void confirmInputBye(String userInput) {
        if (isBye(userInput.trim())) {
            exit();
        }
    }

}
