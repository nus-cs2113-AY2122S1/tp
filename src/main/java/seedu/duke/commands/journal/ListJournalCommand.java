package seedu.duke.commands.journal;

//@@author SvethaMahadevan

import seedu.duke.commands.Command;
import seedu.duke.journal.Entry;
import seedu.duke.journal.Notebook;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ListJournalCommand extends Command {

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public ListJournalCommand() {
        helpMessage = "Listing notebooks with entries";
        syntax = "journal list";
    }

    /**
     * Allows for the listing of notebooks and entries.
     *
     * @param ui allows for printing of a message to indicate that the note has been added
     * @param storage to print from storage
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ArrayList<Notebook> notes = storage.collectionOfNotebooks.getNotesArrayList();
        ArrayList<Entry> entries = storage.collectionOfEntries.getEntriesArrayList();
        int notebookIndex = 1;
        int entryIndex = 1;
        ui.printLine();
        for (Notebook note : notes) {
            ui.printMessage(notebookIndex + ". " + "The notebook " + note.getNoteName()
                   + "(tag: " + note.getTag().trim() + ")" + " contains:");
            notebookIndex += 1;
            for (Entry entry : entries) {
                if (entry.getEntryNoteName().equals(note.getNoteName())) {
                    ui.printMessage("\t" + entryIndex + ". " + entry.getNameOfJournalEntry());
                    entryIndex += 1;
                }
            }
            entryIndex = 1;
        }
        ui.printLine();
    }
}