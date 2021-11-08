package seedu.duke.commands.journal;

//@@author SvethaMahadevan

import seedu.duke.commands.Command;
import seedu.duke.exceptions.journal.EmptyNoteIndexException;
import seedu.duke.exceptions.journal.EmptyTagArgumentsException;
import seedu.duke.exceptions.journal.EmptyTagNameException;
import seedu.duke.exceptions.journal.InvalidAddTagArgumentException;
import seedu.duke.exceptions.journal.InvalidTagNameException;
import seedu.duke.exceptions.journal.NotebookNotFoundForTagException;
import seedu.duke.journal.Notebook;
import seedu.duke.parser.journal.ParserJournal;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TagNotebookCommand extends Command {

    public String userInput;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public TagNotebookCommand() {
        syntax = "journal tag n/ [NOTE_INDEX] t/ [TAG_NAME]";
    }

    /**
     * Constructor for the TagNotebookCommand.
     *
     * @param userInput input from the user.
     */
    public TagNotebookCommand(String userInput) {
        this.userInput = userInput;
        helpMessage = "Tag a notebook from list";
        syntax = "journal tag n/ NOTE_INDEX t/ TAG_NAME";
    }


    /**
     * Tags the notebook.
     *
     * @param ui allows for printing that note is added
     * @param storage to allow for storage of notes
     * @throws EmptyTagNameException if there is no tag name given after 't/'
     * @throws EmptyNoteIndexException if there is no note index given after 'n/'
     * @throws EmptyTagArgumentsException in case notebook and tag details aren't in input.
     * @throws InvalidTagNameException in case tag name is invalid.
     * @throws IOException in case of error when writing to save file.
     * @throws NotebookNotFoundForTagException in case notebook for tagging isn't in list.
     * @throws InvalidAddTagArgumentException in case arguments for tagging are invalid.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws EmptyTagNameException,
            EmptyTagArgumentsException, InvalidTagNameException,
            IOException, NotebookNotFoundForTagException, InvalidAddTagArgumentException, EmptyNoteIndexException {
        String[] tagNameAndNotebookIndex = ParserJournal.parseTagNotebookCommand(userInput, storage);
        String notebookIndex = tagNameAndNotebookIndex[0];
        String tagName = tagNameAndNotebookIndex[1];
        ArrayList<Notebook> notebooks = storage.collectionOfNotebooks.getNotesArrayList();
        Notebook notebookToBeTagged = notebooks.get(Integer.parseInt(notebookIndex) - 1);
        notebookToBeTagged.tag(tagName, storage);
        ui.printTaggedNotebookMessage();
    }
}
