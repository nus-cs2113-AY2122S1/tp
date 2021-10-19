package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphCommandTest {

    @Test
    void execute() {
        //Define inputs
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("dish");
        GraphCommand commandToTest = new GraphCommand();
        try{
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals("List is empty, nothing to show", e.getMessage());
        }
        inputs.set(0,"ingr");
        try{
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals("List is empty, nothing to show", e.getMessage());
        }
        inputs.set(0,"not dish or ingr");
        try {
            commandToTest.execute(inputs);
        } catch (FoodoramaException e) {
            assertEquals("Sorry, please input: graph [TYPE]." + System.lineSeparator()
                    + "[TYPE]: dish to list dishes, ingr to list ingredients.", e.getMessage());
        }
    }
}