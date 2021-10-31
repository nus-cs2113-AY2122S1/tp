//@@author peng-217

package note;

import java.util.ArrayList;

import exceptions.InvalidNoteException;
import storage.Storage;
import scene.SceneList;
import ui.Ui;

public class NoteList {
    private ArrayList<Note> notes;
    private final Ui ui;
    private static int defaultTitleCounter = 1;
    private static final String INVALID_NOTE_MESSAGE = "The index you entered is not valid!"
            + "Please check again.";

    public NoteList(Ui ui) {
        this.ui = ui;
        //storage = new Storage();
        notes = new ArrayList<>();
        Storage.openNoteFromFile(this);
    }

    public int getSize() {
        return notes.size();
    }

    public ArrayList<Note> searchNotesUsingSceneIndex(int searchSceneIndex,NoteList notes) {
        ArrayList<Note> result = new ArrayList<>();
        for (int i = 0; i < notes.getSize(); i++) {
            if (notes.getIndexNote(i).getNoteSceneIndex() == searchSceneIndex) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public ArrayList<Note> searchNoteUsingTitle(String keyword,NoteList notes) {
        String[] words = stringSplitter(keyword);
        ArrayList<Note> result = new ArrayList<>();
        for (int i = 0; i < notes.getSize(); i++) {
            boolean titleNotContains = false;
            for (int j = 0; j < words.length; j++) {
                if (!notes.getIndexNote(i).getNoteTitle().contains(words[j])) {
                    titleNotContains = true;
                }
            }
            if (!titleNotContains) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    public static String[] stringSplitter(String keywords) {
        String[] words = keywords.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase();
        }
        return words;
    }

    public Note getIndexNote(int index) {
        return notes.get(index);
    }

    public void createNote(Note newNote) {
        notes.add(newNote);
        Storage.saveNote(this);
        ui.printSaveNoteMessage();
    }

    public void createNoteFromFile(Note newNote, int inputSceneIndex) {
        notes.add(newNote);
        Storage.saveNote(this);
    }

    public void deleteNote(int index) {
        Note noteToDelete = notes.get(index);
        notes.remove(index);
        Storage.saveNote(this);
        ui.printDeleteNoteMessage();
    }


    public void deleteAllNotes() {
        notes.removeAll(notes);
        Storage.saveNote(this);
        ui.printDeleteAllNoteMessage();
    }

    public void processNote(SceneList sceneList, String userChoice) throws InvalidNoteException {
        switch (userChoice) {
        case "1" :
            createNoteProcess(sceneList);
            break;
        case "2" :
            openNoteProcess();
            break;
        case "3" :
            deleteNoteProcess();
            break;
        default :
            throw new InvalidNoteException(INVALID_NOTE_MESSAGE);
        }
    }

    public void createNoteProcess(SceneList sceneList) {
        ui.printNoteTitleInstructions();
        String transientTitle = ui.readUserInput();
        String noteTitle;
        if (!transientTitle.equals(" ")) {
            noteTitle = transientTitle;
        } else {
            noteTitle = "DEFAULT(" + (defaultTitleCounter++) + ")";
        }
        ui.printNoteTextInstructions();
        String noteContent = ui.readUserInput();
        Note newNote = new Note(noteContent, noteTitle, sceneList.getCurrentSceneIndex());
        createNote(newNote);
    }

    public void openNoteProcess() {
        boolean checkExistance = ui.printOpenNoteMessage(this);
        if (checkExistance) {
            String userInput = ui.readUserInput();
            if (userInput.contains("1")) {
                while (!userInput.equals("")) {
                    ui.printNoteSearchInstructions();
                    userInput = ui.readUserInput();
                    try {
                        selectSearchMethod(userInput);
                        break;
                    } catch (InvalidNoteException e) {
                        ui.printNoteErrorMessage(INVALID_NOTE_MESSAGE);
                    }
                }
            } else if (userInput.contains("2")) {
                while (!userInput.equals("")) {
                    try {
                        openNoteDirectly();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        ui.printNoteMissingError(notes.size());
                    }
                }
            }
        }
    }

    public void selectSearchMethod(String userInput) throws InvalidNoteException {
        if (userInput.contains("1")) {
            keywordSearch();
        } else if (userInput.contains("2")) {
            indexSearch();
        } else {
            throw new InvalidNoteException(INVALID_NOTE_MESSAGE);
        }
    }

    public void keywordSearch() {
        ui.printNoteSearchKeyWordInstructions();
        String keywords = ui.readUserInput();
        //System.out.println(keywords);
        ui.printSelectedNote(this.searchNoteUsingTitle(keywords, this));
    }

    public void indexSearch() {
        ui.printNoteSearchSceneIndexInstructions();
        int sceneIndex = Integer.parseInt(ui.readUserInput());
        ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
    }

    public void openNoteDirectly() throws IndexOutOfBoundsException  {
        ui.printNoteOpenInstructions();
        //here the index is not scene index, it is the index in the list
        int inputOrderIndex = Integer.parseInt(ui.readUserInput());
        if (inputOrderIndex > notes.size()) {
            throw new IndexOutOfBoundsException(INVALID_NOTE_MESSAGE);
        }
        ui.printExistingNotes(this, inputOrderIndex);
    }

    public void deleteNoteProcess() {
        ui.printNoteListStarter();
        ui.printAllNotes(this);
        ui.printNoteDeleteInstructions();
        String userInput = ui.readUserInput();
        if (userInput.equals("all")) {
            deleteAllNotes();
        } else {
            int deletedNoteIndex = Integer.parseInt(userInput) - 1;
            this.deleteNote(deletedNoteIndex);
        }
    }
}
