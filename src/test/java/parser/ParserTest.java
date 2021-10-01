package parser;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void extractCommand_oneSeparator_expectTwoParts(){
        String inputString = "list i/1";
        String[] stringParts = Parser.parseCommand(inputString);
        assertEquals(2, stringParts.length);
    }

    @Test
    public void parseParameters_oneSeparator_expectTwoParts(){
        String inputString = "i/1 n/name";
        HashMap<String, String> parametersValues = Parser.parseParameters(inputString);
        assertEquals(2, parametersValues.keySet().size());
    }
}
