package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {

    @Test
    void parse_addCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType("add botuan /cn 9487 /f hgus /a hotel-one /t aus-a", AddCommand.class);

    }

    @Test
    void parse_clearCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType("clear", ClearCommand.class);

    }

    @Test
    void parse_listCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType("list", ListCommand.class);

    }

    @Test
    void parse_cutCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType("cut 0", CutCommand.class);

    }

    @Test
    void parse_byeCommand_correctCommandCreated() throws TourPlannerException {
        parseAndAssertCommandType("bye", ByeCommand.class);

    }

    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) 
        throws TourPlannerException {
        final Command result = Parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

}
