//@@author peng-217

package note;

import ui.Ui;
import note.NoteList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteTest {

    @Test
    public void getNoteContent() {
        Note testingNoteContent = new Note("Test note","DEFAULT",1);
        assertEquals("Test note",testingNoteContent.getNoteContent());
    }

    @Test
    public void getNoteSceneIndex() {
        Note testingNoteIndex = new Note("Test note","DEFAULT",2);
        assertEquals(2,testingNoteIndex.getNoteSceneIndex());
    }

    @Test
    public void getNoteTitle() {
        Note testingNoteTitle = new Note("Test note", "TEST TITLE", 3);
        assertEquals("TEST TITLE", testingNoteTitle.getNoteTitle());
    }
}