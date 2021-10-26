package seedu.parser;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import seedu.command.AddContactCommand;
import seedu.command.Command;
import seedu.command.DeleteContactCommand;
import seedu.command.EditContactCommand;
import seedu.command.FailedCommand;
import seedu.command.InvalidDetailCommand;
import seedu.command.ViewContactCommand;
import seedu.contact.ContactList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainParserTest {
    private MainParser mainParser;
    private String testUserInput;
    private int testIndex; // For commands that require index

    @BeforeEach
    public void setUp() {
        mainParser = new MainParser();
    }

    /**
     * Returns type of Command the parseCommand returns. This is required because most of the MainParser's
     * internal parsing methods are private.
     *
     * @param userInput    Expected user input
     * @param commandClass Expected Command class/type returned
     * @param <T>          Type of Command expected
     * @return Command Returns a Command for verification
     */
    @SuppressWarnings("unchecked")
    private <T extends Command> T getParsedCommand(String userInput, Class<T> commandClass) {
        // This is because user input is normally trimmed by TextUi
        String trimmedUserInput = userInput.trim();
        final Command result = mainParser.parseCommand(trimmedUserInput);
        assertTrue(commandClass.isInstance(result));
        return (T) result;
    }

    @Test
    public void parseDeleteCommand_validIndex_expectDeleteContactIndexMatch() {
        testIndex = 1;
        testUserInput = "rm " + testIndex;
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex, testResultCommand.getContactIndex());
    }

    @Test
    public void parseDeleteCommand_validDeleteAllIndex_expectDeleteAddIdMatch() {
        String testIndex = "all";
        int testId = -2;
        testUserInput = "rm " + testIndex;
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testId, testResultCommand.getContactIndex());
    }

    @Test
    public void parseDeleteCommand_multipleIndexes_expectDeleteContactIndexMatch() {
        testIndex = 1;
        testUserInput = String.format("rm %d %d", testIndex, 2);
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex, testResultCommand.getContactIndex());
    }

    @Test
    public void parseDeleteCommand_validIndexWithSpaces_expectDeleteContactIndexMatch() {
        testIndex = 1;
        testUserInput = "rm    " + testIndex;
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex, testResultCommand.getContactIndex());
    }

    @Test
    public void parseDeleteCommand_missingIndex_expectFailedCommandType() {
        testUserInput = "rm";
        FailedCommandType expectedFailedCommandType = FailedCommandType.MISSING_INDEX;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }

    @Test
    public void parseDeleteCommand_invalidNumber_expectFailedCommandType() {
        testUserInput = "rm abc";
        FailedCommandType expectedFailedCommandType = FailedCommandType.MISSING_INDEX;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }

    //@@author mayankp291
    @Test
    public void parseDeleteCommand_outOfRangeIndex_expectException() {
        ContactList contactList = new ContactList();
        testUserInput = "rm 3";
        // adding 1 input contact into contactList
        String addContactInput = "add -n wangyih -g ongzai";
        AddContactCommand addContactCommand = getParsedCommand(addContactInput, AddContactCommand.class);
        addContactCommand.setContactList(contactList);
        addContactCommand.execute();
        // trying to delete the contact with index 3 when there is only 1 contact (with index 0) in the contact list
        DeleteContactCommand deleteContactCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        deleteContactCommand.setContactList(contactList);
        int deletedIndex = deleteContactCommand.getContactIndex();
        assertThrows(IndexOutOfBoundsException.class, () -> contactList.deleteContact(deletedIndex));
    }

    //@@author mayankp291
    @Test
    public void parseAddCommand_validInputsWithIrregularSpaces_expectAddContactCommand() {
        testUserInput = "         add -n   andre -g  ng-andre  -l linkedin -tw twit -te teles123 -e andre@twix.com";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        String[] details = {"andre", "ng-andre", "linkedin", "teles123", "twit", "andre@twix.com"};
        AddContactCommand expectedCommand = new AddContactCommand(details);
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
        assertEquals(expectedCommand.getLinkedin(), actualCommand.getLinkedin());
        assertEquals(expectedCommand.getTelegram(), actualCommand.getTelegram());
        assertEquals(expectedCommand.getTwitter(), actualCommand.getTwitter());
        assertEquals(expectedCommand.getEmail(), actualCommand.getEmail());
    }

    //@@author mayankp291
    @Test
    public void parseAddCommand_validInputsWithExtraCharacters_expectAddContactCommand() {
        testUserInput = "         add   1231267azldasd -n   marcus    -g  marcus-bory  ";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        String[] details = {"marcus", "marcus-bory", null, null, null, null};
        AddContactCommand expectedCommand = new AddContactCommand(details);
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    //@@author
    @Test
    public void parseAddCommand_validInputs_expectAddContactCommand() {
        testUserInput = "add -n andre -g ng-andre -l linkedin -tw twit -te teles123 -e andre@twix.com";
        final AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        String[] details = {"andre", "ng-andre", "linkedin", "teles123", "twit", "andre@twix.com"};
        final AddContactCommand expectedCommand = new AddContactCommand(details);
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
        assertEquals(expectedCommand.getLinkedin(), actualCommand.getLinkedin());
        assertEquals(expectedCommand.getTelegram(), actualCommand.getTelegram());
        assertEquals(expectedCommand.getTwitter(), actualCommand.getTwitter());
        assertEquals(expectedCommand.getEmail(), actualCommand.getEmail());
    }

    //@@author mayankp291
    @Test
    public void parseAddCommand_validInputsWithPartialFlags_expectAddContactCommand() {
        testUserInput = "         add -n   ashraf  -g fdada-121   -tw 66123ada -e ash@yahoo.com  ";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        String[] details = {"ashraf", "fdada-121", null, null, "66123ada", "ash@yahoo.com"};
        AddContactCommand expectedCommand = new AddContactCommand(details);
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
        assertEquals(expectedCommand.getTwitter(), actualCommand.getTwitter());
        assertEquals(expectedCommand.getEmail(), actualCommand.getEmail());
    }

    @Test
    public void parseAddCommand_invalidNameWithNumber_expectInvalidDetailCommand() {
        testUserInput = "add -n 123456";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_NAME;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_invalidGithubUsername_expectInvalidDetailCommand() {
        testUserInput = "add -n marcus -g git@hub//";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_GITHUB_USERNAME;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_invalidLinkedin_expectInvalidDetailCommand() {
        testUserInput = "add -n marcus -l linked@in/#";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_LINKEDIN;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_invalidTwitter_expectInvalidDetailCommand() {
        testUserInput = "add -n marcus -tw b#b#@@";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_TWITTER;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_invalidTelegram_expectInvalidDetailCommand() {
        testUserInput = "add -n marcus -te $$5ts@!";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_TELEGRAM;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_invalidEmail_expectInvalidDetailCommand() {
        testUserInput = "add -n marcus -e marcus";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_MAIL;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_forbiddenGithub_expectInvalidDetailCommand() {
        testUserInput = "add -n marcus -g null";
        FailedCommandType expectedFailedCommandType = FailedCommandType.FORBIDDEN_DETAIL;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    @Test
    public void parseAddCommand_forbiddenName_expectInvalidDetailCommand() {
        testUserInput = "add -n null -g legit";
        FailedCommandType expectedFailedCommandType = FailedCommandType.FORBIDDEN_DETAIL;
        final InvalidDetailCommand actualCommand = getParsedCommand(testUserInput, InvalidDetailCommand.class);
        assertEquals(expectedFailedCommandType, actualCommand.getType());
    }

    //@@author mayankp291
    @Test
    public void parseViewCommand_validIndex_expectViewContactIndexMatch() {
        final int testIndex = 1;
        final String testUserInput = "view " + testIndex;
        final ViewContactCommand testResultCommand = getParsedCommand(testUserInput, ViewContactCommand.class);
        assertEquals(testIndex,testResultCommand.getIndex());
    }

    //@@author
    @Test
    public void parseViewCommand_multipleIndexes_expectViewContactIndexMatch() {
        int testIndex = 1;
        testUserInput = String.format("view %d %d", testIndex, 2);
        final ViewContactCommand testResultCommand = getParsedCommand(testUserInput, ViewContactCommand.class);
        assertEquals(testIndex,testResultCommand.getIndex());
    }

    //@@author mayankp291
    @Test
    public void parseViewCommand_invalidIndex_expectException() {
        testIndex = 99999;
        final ViewContactCommand testResultCommand = new ViewContactCommand(testIndex);
        assertThrows(NullPointerException.class, testResultCommand::execute);
    }

    //@@author
    @Test
    public void parseViewCommand_validIndexWithSpaces_expectViewContactIndexMatch() {
        testIndex = 1;
        testUserInput = "view    " + testIndex;
        final ViewContactCommand testResultCommand = getParsedCommand(testUserInput, ViewContactCommand.class);
        assertEquals(testIndex, testResultCommand.getIndex());
    }

    @Test
    public void parseViewCommand_missingIndex_expectFailedCommandType() {
        testUserInput = "view";
        FailedCommandType expectedFailedCommandType = FailedCommandType.MISSING_INDEX;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }


    @Test
    public void parseViewCommand_invalidInput_expectFailedCommandType() {
        testUserInput = "view abc";
        FailedCommandType expectedFailedCommandType = FailedCommandType.MISSING_INDEX;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }


    @Test
    public void parseAddCommand_missingName_expectFailedCommand() {
        testUserInput = " add -g      github ";
        final FailedCommand actualCommand = getParsedCommand(testUserInput, FailedCommand.class);
        final FailedCommand expectedCommand = new FailedCommand(FailedCommandType.MISSING_ARGS_ADD);
        assertEquals(expectedCommand.getType(), actualCommand.getType());
    }
}

