package seedu.foodorama.command;

import org.junit.jupiter.api.Test;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    @Test
    void execute() throws FoodoramaException {
        ArrayList<String> inputParams = new ArrayList<>();
        Command testHelpCommand = new HelpCommand();
        ByteArrayOutputStream helpOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(helpOutput));

        inputParams.add("help");
        testHelpCommand.execute(inputParams);
        String actualOut = helpOutput.toString().trim();
        String helpMsg = Ui.getHelpMsg();

        assertEquals(actualOut, helpMsg);
    }
}