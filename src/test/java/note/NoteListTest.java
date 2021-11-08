//@@author peng-217

package note;

import ui.Ui;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoteListTest {

    @Test
    public void getSize() {
        Ui ui = new Ui();
        NoteList notes = new NoteList(ui);
        notes.deleteAllNotes();
        int testSize = 6;
        int testScene = 1;
        for (int i = 0; i < testSize; i++) {
            Note testNote = new Note("Test", "DEFAULT " + i, testScene);
            notes.createNote(testNote);
        }
        assertEquals(6,notes.getSize());
    }

    @Test
    public void searchNotesUsingSceneIndex() {
        NoteList notes = new NoteList(new Ui());
        Note testOne = new Note("test one","TEST ONE",1);
        Note testTwo = new Note("test two","TEST TWO",2);
        Note testThree = new Note("test three","TEST THREE",1);
        notes.createNote(testOne);
        notes.createNote(testTwo);
        notes.createNote(testThree);
        ArrayList<Note> actualResult;
        ArrayList<Note> expectedResult = new ArrayList<>();
        actualResult = notes.searchNotesUsingSceneIndex(1,notes);
        expectedResult.add(testOne);
        expectedResult.add(testThree);
        assertEquals(actualResult.get(0).getNoteSceneIndex(),expectedResult.get(0).getNoteSceneIndex());
        assertEquals(actualResult.get(1).getNoteSceneIndex(),expectedResult.get(1).getNoteSceneIndex());
    }

    @Test
    public void searchNoteUsingTitle() {
        NoteList notes = new NoteList(new Ui());
        Note testAlpha = new Note("test alpha","TEST ALPHA",1);
        Note testBeta = new Note("test beta","TEST BETA",2);
        Note testGamma = new Note("test gamma","TEST GAMMA",3);
        Note testAlphaTwo = new Note("test alpha two","TEST ALPHA TWO",2);
        notes.createNote(testAlpha);
        notes.createNote(testBeta);
        notes.createNote(testGamma);
        notes.createNote(testAlphaTwo);
        ArrayList<Note> actualResult;
        ArrayList<Note> expectedResult = new ArrayList<>();
        actualResult = notes.searchNoteUsingTitle("ALPHA",notes);
        expectedResult.add(testAlpha);
        expectedResult.add(testAlphaTwo);
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void stringSpliter() {
        NoteList notes = new NoteList(new Ui());
        String testString = "THIS IS A TEST OF DUKE";
        String[] expectedResult = {"THIS","IS","A","TEST","OF","DUKE"};
        String[] underTest = notes.stringSplitter(testString);
        //assertTrue(expectedResult.equals(underTest));
        assertEquals("THIS",underTest[0]);
        assertEquals("IS",underTest[1]);
        assertEquals("A",underTest[2]);
        assertEquals("TEST",underTest[3]);
        assertEquals("OF",underTest[4]);
        assertEquals("DUKE",underTest[5]);
    }

    @Test
    public void getIndexNote() {
        NoteList notes = new NoteList(new Ui());
        notes.deleteAllNotes();
        Note testOne = new Note("test 1","TEST 1",1);
        Note testTwo = new Note("test 2","TEST 2",2);
        notes.createNote(testOne);
        notes.createNote(testTwo);
        System.out.println(notes.getIndexNote(0).getNoteSceneIndex());
        assertTrue(notes.getIndexNote(0).getNoteSceneIndex() == 1);
        assertTrue(notes.getIndexNote(1).getNoteSceneIndex() == 2);
    }
}