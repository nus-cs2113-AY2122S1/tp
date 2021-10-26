package seedu.parser;

import org.junit.jupiter.api.Test;

import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.MissingArgEditException;
import seedu.exception.MissingDetailException;
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
