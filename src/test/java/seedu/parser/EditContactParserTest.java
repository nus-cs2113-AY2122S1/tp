package seedu.parser;

import org.junit.jupiter.api.Test;

import seedu.exception.*;
import seedu.parser.EditContactParser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditContactParserTest {
    private EditContactParser editContactParser = new EditContactParser();

    @Test
    void parseContactDetails() throws MissingDetailException,
            MissingArgEditException, InvalidFlagException, ForbiddenDetailException, InvalidTelegramUsernameException,
            InvalidNameException, InvalidLinkedinUsernameException, InvalidGithubUsernameException,
            InvalidTwitterUsernameException, InvalidEmailException {
        String[] expectedResult = {null, "github", null, null, null, null};
        String testInput = "edit 1 -g github";
        String[] actualResult = editContactParser.parseContactDetails(testInput);
        assertArrayEquals(expectedResult, actualResult);
    }
}
