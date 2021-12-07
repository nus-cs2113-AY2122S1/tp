package seedu.duke.parser.journal;

import seedu.duke.exceptions.calendar.IncorrectNumberOfArgumentsException;
import seedu.duke.exceptions.journal.DuplicateNoteException;
import seedu.duke.exceptions.journal.EmptyDeleteNoteException;
import seedu.duke.exceptions.journal.EmptyEntryArgumentsException;
import seedu.duke.exceptions.journal.EmptyEntryNameException;
import seedu.duke.exceptions.journal.EmptyFindTagException;
import seedu.duke.exceptions.journal.EmptyNoteArgumentsException;
import seedu.duke.exceptions.journal.EmptyNoteIndexException;
import seedu.duke.exceptions.journal.EmptyNoteNameException;
import seedu.duke.exceptions.journal.EmptyTagArgumentsException;
import seedu.duke.exceptions.journal.EmptyTagNameException;
import seedu.duke.exceptions.journal.InvalidAddEntryArgumentException;
import seedu.duke.exceptions.journal.InvalidAddTagArgumentException;
import seedu.duke.exceptions.journal.InvalidDeleteEntryArgumentException;
import seedu.duke.exceptions.journal.InvalidDeleteNoteArgumentException;
import seedu.duke.exceptions.journal.NotebookNotFoundForEntry;
import seedu.duke.exceptions.journal.NotebookNotFoundForTagException;
import seedu.duke.journal.Notebook;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.constants.Messages.MODULE_DIVIDER;
import static seedu.duke.constants.Messages.MODULE_DIVIDER_NOT_FOUND;

//@@author SvethaMahadevan
public class ParserJournal {

    /**
     * Returns the name of the notebook.
     *
     * @param input contains notebook information
     * @return noteName a string which contains the name of the notebook
     * @throws DuplicateNoteException if notebook with same name has been added before
     * @throws EmptyNoteNameException if notebook name isn't given after 'n/'
     * @throws EmptyNoteArgumentsException if only "journal notebook" is entered by user without arguments
     */
    public static String parseAddNoteCommand(String input, Storage storage) throws DuplicateNoteException,
            EmptyNoteNameException, EmptyNoteArgumentsException, IncorrectNumberOfArgumentsException {
        if (isValidNotebookCommand(input)) {
            ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
            String noteName = parseNoteName(input, notes);
            return noteName;
        }
        return null;
    }

    /**
     * Parses notebook name and returns name only if adding notebook doesn't result in duplicates.
     *
     * @param input getting userInput regarding notebook
     * @param notes list of notes
     * @return notebook name if it is not duplicate
     * @throws DuplicateNoteException checks for duplicate note
     */
    public static String parseNoteName(String input, ArrayList<Notebook> notes) 
        throws DuplicateNoteException, IncorrectNumberOfArgumentsException {
        if (!input.contains("n/")) {
            throw new IncorrectNumberOfArgumentsException("n/ not found in command!");
        }
        String noteNameDetails = input.trim().split("notebook")[1];
        String noteName = noteNameDetails.split("n/")[1].trim();
        for (Notebook note : notes) {
            if (note.getNoteName().equals(noteName)) {
                throw new DuplicateNoteException();
            }
        }
        return noteName;
    }

    /**
     * To check if notebook command is valid, else false.
     *
     * @param input user input
     * @return true if notebook command is valid, else false
     * @throws EmptyNoteArgumentsException checks if note arguments are empty
     * @throws EmptyNoteNameException      checks if notebook name is not entered
     */
    public static boolean isValidNotebookCommand(String input) throws EmptyNoteArgumentsException,
            EmptyNoteNameException {
        final String[] commandTypeAndParams = Parser.splitCommandAndArgs(input);
        final String commandArgs = commandTypeAndParams[1];
        String[] noteArguments = commandArgs.split(" ");
        if (noteArguments.length == 1) {
            throw new EmptyNoteArgumentsException();
        }
        if (noteArguments.length == 2 && noteArguments[1].equals("n/")) {
            throw new EmptyNoteNameException();
        }
        return true;
    }


    /**
     * Returns the name of the notebook and entry to allow for adding of entry.
     *
     * @param input contains information about entry and the desired notebook from user
     * @return a String array which stores notebook name and entry name
     * @throws EmptyEntryArgumentsException if no arguments found after entry
     * @throws EmptyNoteNameException if no note name found after n/
     * @throws EmptyEntryNameException if no entry name found after e/
     * @throws NotebookNotFoundForEntry if no notebook is found in list for entry
     * @throws InvalidAddEntryArgumentException if arguments for adding entry are invalid.
     */
    public static String[] parseAddEntryCommand(String input, Storage storage) throws EmptyEntryArgumentsException,
            EmptyNoteNameException, EmptyEntryNameException, InvalidAddEntryArgumentException,
            NotebookNotFoundForEntry {
        String[] noteEntryNames = parseNoteEntryName(input);
        String notebookName = noteEntryNames[0];
        String entryName = noteEntryNames[1];
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        int flagNotebook = notes.stream().anyMatch(note -> note.getNoteName().equals(notebookName)) ? 1 : 0;
        if (flagNotebook == 0) {
            throw new NotebookNotFoundForEntry();
        } else {
            return new String[]{notebookName, entryName};
        }
    }

    /**
     * Parses arguments for tagging a notebook.
     *
     * @param input from user
     * @param storage storage object
     * @return a String array which stores notebook index and tag name
     * @throws EmptyTagNameException if there is no tag name given after 't/'
     * @throws EmptyNoteIndexException if there is no note index given after 'n/'
     * @throws EmptyTagArgumentsException in case notebook and tag details aren't in input.
     * @throws NotebookNotFoundForTagException in case notebook for tagging isn't in list.
     * @throws InvalidAddTagArgumentException in case arguments for tagging are invalid.
     */
    public static String[] parseTagNotebookCommand(String input, Storage storage) throws EmptyTagNameException,
            InvalidAddTagArgumentException, EmptyTagArgumentsException,
            NotebookNotFoundForTagException, EmptyNoteIndexException {
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        String[] notebookIndexAndTagName = parseNotebookNameAndTag(input);
        String notebookIndex = notebookIndexAndTagName[0];
        String tagName = notebookIndexAndTagName[1];
        if (Integer.parseInt(notebookIndex) > notes.size() || Integer.parseInt(notebookIndex) < 1) {
            throw new NotebookNotFoundForTagException();
        }
        return new String[]{notebookIndex, tagName};

    }

    /**
     * Returns arguments for deleting entry.
     *
     * @param input from user
     * @param storage storage object
     * @return string array with notebook name and entry name for deleting entry
     * @throws EmptyEntryArgumentsException if no arguments are given for deleting entry.
     * @throws EmptyNoteNameException if no note name is given.
     * @throws EmptyEntryNameException if no entry name is given.
     * @throws NotebookNotFoundForEntry if the notebook isn't in the list
     * @throws InvalidDeleteEntryArgumentException if arguments for deleting entry are invalid.
     */
    public static String[] parseDeleteEntryCommand(String input, Storage storage) throws EmptyEntryArgumentsException,
            EmptyNoteNameException, EmptyEntryNameException, NotebookNotFoundForEntry,
            InvalidDeleteEntryArgumentException {
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        String[] noteEntryNames = parseArgumentsDeleteEntryCommand(input);
        String notebookName = noteEntryNames[0];
        String entryName = noteEntryNames[1];
        int flagNotebook = notes.stream().anyMatch(note -> note.getNoteName().equals(notebookName)) ? 1 : 0;
        if (flagNotebook == 0) {
            throw new NotebookNotFoundForEntry();
        } else {
            return new String[]{notebookName, entryName};
        }
    }

    /**
     * Parsing notebook and entry name.
     *
     * @param input from user
     * @return notebook name and entry name in form of string array
     * @throws EmptyEntryArgumentsException if no arguments found after entry
     * @throws EmptyNoteNameException if no note name found after n/
     * @throws EmptyEntryNameException if no entry name found after e/
     * @throws InvalidAddEntryArgumentException if arguments for adding entry are invalid.
     */
    public static String[] parseNoteEntryName(String input) throws EmptyNoteNameException, EmptyEntryNameException,
            EmptyEntryArgumentsException, InvalidAddEntryArgumentException {
        String noteNameAndEntryNameDetails = input.trim().substring(input.indexOf("entry"));
        if (input.trim().substring(input.indexOf("entry") + 5).trim().isEmpty()) {
            throw new EmptyEntryArgumentsException();
        }
        String noteName = "";
        String entryName = "";
        String noteNameAndEntryNamesWithDividers = "";
        try {
            noteNameAndEntryNamesWithDividers =
                    noteNameAndEntryNameDetails.substring(noteNameAndEntryNameDetails.indexOf("n/")).trim();
        } catch (Exception e) {
            throw new InvalidAddEntryArgumentException();
        }
        try {
            noteName = noteNameAndEntryNamesWithDividers.substring(noteNameAndEntryNamesWithDividers.indexOf("n/") + 2,
                    noteNameAndEntryNamesWithDividers.indexOf("e/")).trim();
        } catch (Exception e) {
            throw new InvalidAddEntryArgumentException();
        }
        try {
            entryName = noteNameAndEntryNamesWithDividers.substring(noteNameAndEntryNamesWithDividers
                    .indexOf("e/") + 2).trim();
        } catch (Exception e1) {
            throw new InvalidAddEntryArgumentException();
        }
        if (entryName.isEmpty() && noteName.isEmpty()) {
            throw new EmptyEntryArgumentsException();
        } else if (entryName.isEmpty()) {
            throw new EmptyEntryNameException();
        } else if (noteName.isEmpty()) {
            throw new EmptyNoteNameException();
        } else {
            return new String[]{noteName, entryName};
        }
    }

    /**
     * Parsing tag for the notebook.
     *
     * @param input from user
     * @return notebook name and tag name in form of String array.
     * @throws EmptyTagNameException if there is no tag name given after 't/'
     * @throws EmptyNoteIndexException if there is no note index given after 'n/'
     * @throws EmptyTagArgumentsException in case notebook and tag details aren't in input.
     * @throws InvalidAddTagArgumentException in case arguments for tagging are invalid.
     */
    public static String[] parseNotebookNameAndTag(String input) throws EmptyTagArgumentsException,
            InvalidAddTagArgumentException, EmptyTagNameException, EmptyNoteIndexException {

        String notebookIndexAndTagNameDetails = input.trim().substring(input.indexOf("tag"));
        if (input.trim().substring(input.indexOf("tag") + 3).trim().isEmpty()) {
            throw new EmptyTagArgumentsException();
        }
        String notebookIndex = "";
        String tagName = "";
        String notebookIndexAndTagName = "";
        try {
            notebookIndexAndTagName =
                    notebookIndexAndTagNameDetails.substring(notebookIndexAndTagNameDetails.indexOf("n/")).trim();
        } catch (Exception e) {
            throw new InvalidAddTagArgumentException();
        }

        try {
            notebookIndex = notebookIndexAndTagName.substring(notebookIndexAndTagName.indexOf("n/") + 2,
                    notebookIndexAndTagName.indexOf("t/")).trim();
        } catch (Exception e) {
            throw new InvalidAddTagArgumentException();
        }

        try {
            tagName = notebookIndexAndTagName.substring(notebookIndexAndTagName.indexOf("t/") + 2).trim();
        } catch (Exception e1) {
            throw new InvalidAddTagArgumentException();
        }

        if (notebookIndex.isEmpty() && tagName.isEmpty()) {
            throw new EmptyTagArgumentsException();
        } else if (tagName.isEmpty()) {
            throw new EmptyTagNameException();
        } else if (notebookIndex.isEmpty()) {
            throw new EmptyNoteIndexException();
        } else {
            return new String[]{notebookIndex, tagName};
        }
    }

    /**
     * Parsing index for notebook to be deleted.
     *
     * @param input from user
     * @return index for notebook to be deleted
     * @throws EmptyDeleteNoteException if no notebook index is given for deletion
     * @throws InvalidDeleteNoteArgumentException if the argument for notebook deletion is invalid
     */
    public static int parseDeleteNoteCommand(String input) throws EmptyDeleteNoteException,
            InvalidDeleteNoteArgumentException {
        String indexOfDeletedNotebook = input.trim().substring(input.indexOf("delete_notebook") + 15).trim();
        if (indexOfDeletedNotebook.isEmpty()) {
            throw new EmptyDeleteNoteException();
        }
        try {
            if (Integer.parseInt(indexOfDeletedNotebook) >= 1) {
                return Integer.parseInt(indexOfDeletedNotebook);
            } else {
                throw new InvalidDeleteNoteArgumentException();
            }
        } catch (Exception e) {
            throw new InvalidDeleteNoteArgumentException();
        }
    }

    /**
     * Parsing index of entry and notebook to delete entry.
     *
     * @param input from user
     * @return index for notebook to be deleted
     * @throws EmptyEntryArgumentsException if no arguments are given for deleting entry.
     * @throws EmptyNoteNameException if no note name is given.
     * @throws EmptyEntryNameException if no entry name is given.
     * @throws InvalidDeleteEntryArgumentException if arguments for deleting entry are invalid.
     *
     */
    public static String[] parseArgumentsDeleteEntryCommand(String input) throws EmptyEntryArgumentsException,
            EmptyEntryNameException, EmptyNoteNameException, InvalidDeleteEntryArgumentException {
        String noteNameAndEntryNameDetails = input.trim().substring(input.indexOf("delete_entry"));
        if (input.trim().substring(input.indexOf("delete_entry") + 12).trim().isEmpty()) {
            throw new EmptyEntryArgumentsException();
        }
        String noteName = "";
        String entryName = "";
        String noteNameAndEntryName = "";
        try {
            noteNameAndEntryName =
                    noteNameAndEntryNameDetails.substring(noteNameAndEntryNameDetails.indexOf("n/")).trim();
        } catch (Exception e) {
            throw new InvalidDeleteEntryArgumentException();
        }

        try {
            noteName = noteNameAndEntryName.substring(noteNameAndEntryName.indexOf("n/") + 2,
                    noteNameAndEntryName.indexOf("e/")).trim();
        } catch (Exception e) {
            throw new InvalidDeleteEntryArgumentException();
        }

        try {
            entryName = noteNameAndEntryName.substring(noteNameAndEntryName.indexOf("e/") + 2).trim();
        } catch (Exception e) {
            throw new InvalidDeleteEntryArgumentException();
        }

        if (noteName.isEmpty() && entryName.isEmpty()) {
            throw new EmptyEntryArgumentsException();
        } else if (entryName.isEmpty()) {
            throw new EmptyEntryNameException();
        } else if (noteName.isEmpty()) {
            throw new EmptyNoteNameException();
        } else {
            return new String[]{noteName, entryName};
        }
    }

    /**
     * Parsing tag for finding notebooks by tag.
     *
     * @param input from user
     * @return tagName
     * @throws EmptyFindTagException if no tag is given for finding.
     */
    public static String parseTagForFinding(String input) throws EmptyFindTagException {
        String tagName = input.trim().substring(input.indexOf("find") + 4).trim();
        if (tagName.isEmpty()) {
            throw new EmptyFindTagException();
        }
        return tagName;
    }
}
