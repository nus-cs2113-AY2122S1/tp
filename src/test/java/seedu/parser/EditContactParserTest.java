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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class EditContactParserTest {
    private final EditContactParser editContactParser = new EditContactParser();

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

    //@@author mayankp291
    @Test
    void parseEditCommand_inputAllFields_expectOutput() throws MissingDetailException,
            MissingArgEditException, InvalidFlagException, ForbiddenDetailException, InvalidTelegramUsernameException,
            InvalidNameException, InvalidLinkedinUsernameException, InvalidGithubUsernameException,
            InvalidTwitterUsernameException, InvalidEmailException {
        String[] expectedResult = {"akshay", "topcoder", "akshay123", "akshay123", "akshay123", "akshay@gmail.com"};
        String testInput = "edit 1 -n akshay -g topcoder -l akshay123 -te akshay123 -tw akshay123 -e akshay@gmail.com";
        String[] actualResult = editContactParser.parseContactDetails(testInput);
        assertArrayEquals(expectedResult, actualResult);
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidFlag_expectInvalidFlagException() {
        String testInput = "edit 1 -name akshaygivemarksplsss";
        assertThrows(InvalidFlagException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_missingFlags_expectMissingArgEditException() {
        String testInput = "edit 2";
        assertThrows(MissingArgEditException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_noFlags_expectMissingArgEditException() {
        String testInput = "edit";
        assertThrows(MissingArgEditException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_missingDescription_expectMissingDetailException() {
        String testInput = "edit 1 -n";
        assertThrows(MissingDetailException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidTwitterLength_exceptInvalidTwitterUsernameException() {
        String testInput = "edit 1 -tw aksha237861238129389asdas12";
        assertThrows(InvalidTwitterUsernameException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidTwitterCharacters_exceptInvalidTwitterUsernameException() {
        String testInput = "edit 1 -tw aks-haAaa";
        assertThrows(InvalidTwitterUsernameException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidTelegramLength_exceptInvalidTelegramUsernameException() {
        String testInput = "edit 1 -te absd";
        assertThrows(InvalidTelegramUsernameException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidTelegramCharacters_exceptInvalidTelegramUsernameException() {
        String testInput = "edit 1 -te JKasn- kasd";
        assertThrows(InvalidTelegramUsernameException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidGithubCharacters_exceptInvalidGithubUsernameException() {
        String testInput = "edit 1 -g akshay@git";
        assertThrows(InvalidGithubUsernameException.class, () -> editContactParser.parseContactDetails(testInput));
    }

    //@@author mayankp291
    @Test
    void parseEditCommand_invalidGithubLength_exceptInvalidGithubUsernameException() {
        String testInput = "edit 1 -g akshaynarayanismazingprofbutgithubusernameistoolongsoexpectexception";
        assertThrows(InvalidGithubUsernameException.class, () -> editContactParser.parseContactDetails(testInput));
    }

}
