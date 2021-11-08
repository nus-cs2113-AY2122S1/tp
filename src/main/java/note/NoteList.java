//@@author peng-217

package note;

import java.util.ArrayList;

import exceptions.NoteCorruptedFileException;
import exceptions.InvalidNoteException;
import parser.Parser;
import storage.GameNoteFileManager;
import scene.SceneList;
import ui.Ui;

public class NoteList {
    private final ArrayList<Note> notes;
    private final Ui ui;
    private static GameNoteFileManager noteFile;
    private static int defaultTitleCounter = 1;
    private static final String INVALID_NOTE_INDEX_MESSAGE = "The index you entered is not valid! "
            + "Please check again.";
    private static final String INVALID_NOTE_COMMAND_MESSAGE = "The command you entered is not valid! "
            + "Please check again.";
    private static final String REINPUT_MESSAGE = "Please type in the command:";
    private static final String INVALID_NOTE_SEARCH_MESSAGE = "Please input a valid search choice!";
    private static final String NOTE_CORRUPTED_MESSAGE = "The corrupted file has been removed! "
            + "The new file has been created!";


    public NoteList(Ui ui) {
        this.ui = ui;
        //storage = new Storage();
        noteFile = new GameNoteFileManager();
        notes = new ArrayList<>();
        try {
            noteFile.openNoteFromFile(this);
        } catch (NoteCorruptedFileException e) {
            noteFile.forceClearNote();
            ui.printNoteErrorMessage(NOTE_CORRUPTED_MESSAGE);
        }
    }

    /**
     * Returns the size of note list.
     *
     * @return Size of note list.
     */
    public int getSize() {
        return notes.size();
    }

    /**
     * Returns an arraylist contains searched notes with given scene index.
     *
     * @param searchSceneIndex User input scene index.
     * @param notes Note list.
     * @return An arraylist contains all the corresponding notes.
     */
    public ArrayList<Note> searchNotesUsingSceneIndex(int searchSceneIndex,NoteList notes) {
        ArrayList<Note> result = new ArrayList<>();
        for (int i = 0; i < notes.getSize(); i++) {
            if (notes.getIndexNote(i).getNoteSceneIndex() == searchSceneIndex) {
                result.add(notes.getIndexNote(i));
            }
        }
        return result;
    }

    /**
     * Returns an arraylist contains searched notes with given title keywords.
     *
     * @param keyword User input keywords.
     * @param notes Note list.
     * @return An arraylist contains all the corresponding notes.
     */
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

    /**
     * Returns the string array contains splited keywords.
     *
     * @param keywords User input keywords.
     * @return A string array that contains all the keywords.
     */
    public static String[] stringSplitter(String keywords) {
        String[] words = keywords.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase();
        }
        return words;
    }

    /**
     * Returns the note with given index.
     *
     * @param index User input index.
     * @return Note with given index.
     */
    public Note getIndexNote(int index) {
        return notes.get(index);
    }

    /**
     * Creates a new note and add it into note list.
     *
     * @param newNote The new note to be created.
     */
    public void createNote(Note newNote) {
        notes.add(newNote);
        noteFile.saveNote(this);
        ui.printSaveNoteMessage();
    }

    /**
     * Initializes all saved note from local note data file.
     *
     * @param newNote The note from the local note data file to be initialized.
     * @param inputSceneIndex Scene index that stored in data file.
     */
    public void createNoteFromFile(Note newNote, int inputSceneIndex) {
        notes.add(newNote);
        noteFile.saveNote(this);
    }

    /**
     * Deletes a note with given index.
     *
     * @param index The index of the note that to be deleted.
     */
    public void deleteNote(int index) {
        notes.remove(index);
        noteFile.saveNote(this);
        ui.printDeleteNoteMessage();
    }

    /**
     * Deletes all notes.
     */
    public void deleteAllNotes() {
        notes.removeAll(notes);
        noteFile.forceClearNote();
        ui.printDeleteAllNoteMessage();
    }

    /**
     * Performs all note-related functions.
     *
     * @param sceneList The scene list used for creating note.
     * @param userChoice The desired function by user.
     * @throws InvalidNoteException If user's input is invalid.
     */
    public void processNote(SceneList sceneList, String userChoice) throws InvalidNoteException {
        if (!userChoice.equals("/quit")) {
            switch (userChoice) {
            case "1":
                createNoteProcess(sceneList);
                break;
            case "2":
                openNoteProcess();
                break;
            case "3":
                deleteNoteProcess();
                break;
            default:
                throw new InvalidNoteException(INVALID_NOTE_INDEX_MESSAGE);
            }
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Creates a new note.
     *
     * @param sceneList Scene list for creating note.
     */
    public void createNoteProcess(SceneList sceneList) {
        boolean quitNote = false;
        boolean validNoteTitle = true;
        boolean validNoteContent = true;
        ui.printNoteTitleInstructions();
        String transientTitle = ui.readUserInput();
        String noteTitle = "";
        if ((!transientTitle.equals("") && !transientTitle.equals("\r")) && !transientTitle.equals("/quit")
            && !transientTitle.equals("End of this note.") && !transientTitle.startsWith("scene")) {
            noteTitle = transientTitle;
        } else if (transientTitle.equals("/quit")) {
            quitNote = true;
            ui.printQuitNoteProcess();
        } else if (transientTitle.equals("End of this note.") || transientTitle.startsWith("scene")) {
            validNoteTitle = false;
            ui.printInvalidNoteTitle();
        } else if (transientTitle.equals("") || transientTitle.equals("\r")) {
            noteTitle = "DEFAULT(" + (defaultTitleCounter++) + ")";
        }
        if (!quitNote && validNoteTitle) {
            ui.printNoteTextInstructions();
            String noteContent = ui.readUserInput();
            if (!noteContent.contains("/quit") && !noteContent.equals("End of this note.")
                    && !noteContent.startsWith("scene")) {
                Note newNote = new Note(noteContent, noteTitle, sceneList.getCurrentSceneIndex());
                createNote(newNote);
            } else if (noteContent.equals("End of this note.") || noteContent.startsWith("scene")) {
                ui.printInvalidNoteContent();
            } else {
                ui.printQuitNoteProcess();
            }
        }
    }

    /**
     * Opens a note.
     *
     * @throws InvalidNoteException If user's input command is invalid.
     */
    public void openNoteProcess() throws InvalidNoteException {
        boolean quitNote = false;
        boolean checkExistence = ui.printOpenNoteMessage(this);
        if (checkExistence) {
            String userInput = ui.readUserInput();
            if (userInput.equals("/quit")) {
                quitNote = true;
                ui.printQuitNoteProcess();
            }
            if (!quitNote) {
                while (!userInput.equals("")) {
                    if (!(userInput.startsWith("search") || userInput.startsWith("open"))) {
                        ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                        ui.printReinputMessageOpenOption();
                        userInput = ui.readUserInput();
                    } else {
                        break;
                    }
                }
                String[] userInputInArray = Parser.parseOpenNoteCommand(userInput.trim());
                if (userInputInArray[0].equals("search") && userInputInArray.length > 1) {
                    selectSearchMethod(userInputInArray);
                } else if (userInputInArray[0].equals("search")) {
                    while (!userInput.equals("")) {
                        ui.printNoteSearchInstructions();
                        userInput = ui.readUserInput();
                        try {
                            selectSearchMethod(userInput);
                            break;
                        } catch (InvalidNoteException e1) {
                            ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                        } catch (NumberFormatException e2) {
                            ui.printNoteErrorMessage(INVALID_NOTE_INDEX_MESSAGE);
                        }
                    }
                } else if (userInput.startsWith("open") && userInputInArray.length == 2) {
                    try {
                        openNoteDirectly(userInputInArray[1]);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printNoteMissingError(notes.size());
                    } catch (NumberFormatException e2) {
                        ui.printNoteErrorMessage(INVALID_NOTE_INDEX_MESSAGE);
                    }
                } else if (userInputInArray[0].equals("open") && userInputInArray.length == 1) {
                    while (!userInput.equals("")) {
                        try {
                            openNoteDirectly();
                            break;
                        } catch (IndexOutOfBoundsException e1) {
                            ui.printNoteMissingError(notes.size());
                        } catch (NumberFormatException e2) {
                            ui.printNoteErrorMessage(INVALID_NOTE_INDEX_MESSAGE);
                        }
                    }
                } else if (userInputInArray[0].equals("/quit")) {
                    ui.printQuitNoteProcess();
                } else {
                    throw new InvalidNoteException(INVALID_NOTE_INDEX_MESSAGE);
                }
            }
        }
    }

    /**
     * Lets user select searching method.
     *
     * @param userInput The searching method that user inputs.
     * @throws InvalidNoteException If user's input command is invalid.
     */
    public void selectSearchMethod(String userInput) throws InvalidNoteException {
        boolean quitNote = false;
        if (userInput.equals("/quit")) {
            quitNote = true;
            ui.printQuitNoteProcess();
        }
        while (!userInput.equals("") && quitNote == false) {
            if (!userInput.equals("keyword") && !userInput.equals("index") && !userInput.equals("/quit")) {
                ui.printNoteErrorMessage(INVALID_NOTE_COMMAND_MESSAGE);
                ui.printReinputMessageSearchMethod();
                userInput = ui.readUserInput();
            } else if (userInput.equals("/quit")) {
                quitNote = true;
                break;
            } else {
                break;
            }
        }

        if (quitNote == false) {
            if (userInput.equals("keyword")) {
                keywordSearch();
            } else if (userInput.equals("index")) {
                indexSearch();
            } else {
                throw new InvalidNoteException(INVALID_NOTE_COMMAND_MESSAGE);
            }
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Lets user select searching method.
     *
     * @param userInputInArray The searching method and corresponding user input keywords/scene index.
     * @throws InvalidNoteException If user input is invalid.
     * @throws NumberFormatException If user input is invalid.
     */
    public void selectSearchMethod(String[] userInputInArray)
            throws InvalidNoteException, NumberFormatException {
        if (userInputInArray[1].equals("keyword")) {
            if (userInputInArray.length == 2) {
                keywordSearch();
            } else {
                keywordSearch(userInputInArray[2]);
            }
        } else if (userInputInArray[1].contains("index")) {
            if (userInputInArray.length == 2) {
                indexSearch();
            } else {
                indexSearch(userInputInArray[2]);
            }
        } else {
            throw new InvalidNoteException(INVALID_NOTE_SEARCH_MESSAGE);
        }
    }

    /**
     * Starts the keyword search process.
     *
     * @param userInput User input keywords.
     */
    public void keywordSearch(String userInput) {
        if (!userInput.equals("/quit")) {
            ui.printSelectedNote(this.searchNoteUsingTitle(userInput, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Starts the keyword search process.
     */
    public void keywordSearch() {
        ui.printNoteSearchKeyWordInstructions();
        String keywords = ui.readUserInput();
        if (!keywords.equals("/quit")) {
            ui.printSelectedNote(this.searchNoteUsingTitle(keywords, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Starts the index search process.
     *
     * @param userInput User input scene index.
     * @throws NumberFormatException If user input index is invalid.
     */
    public void indexSearch(String userInput) throws NumberFormatException {
        ui.printNoteSearchSceneIndexInstructions();
        if (!userInput.equals("/quit")) {
            int sceneIndex = Integer.parseInt(userInput);
            ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Starts the index search process.
     *
     * @throws NumberFormatException If user input index is invalid.
     */
    public void indexSearch() throws NumberFormatException {
        ui.printNoteSearchSceneIndexInstructions();
        String userInput = ui.readUserInput();
        if (!userInput.equals("/quit")) {
            int sceneIndex = Integer.parseInt(userInput);
            ui.printSelectedNote(this.searchNotesUsingSceneIndex(sceneIndex, this));
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Opens the note with given index.
     *
     * @param index User input index.
     * @throws IndexOutOfBoundsException If user input index is bigger than the size of note list.
     * @throws NumberFormatException If user input is invalid.
     */
    public void openNoteDirectly(String index) throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteOpenInstructions();
        // here the index is not scene index, it is the index in the list
        if (!index.equals("/quit")) {
            int inputOrderIndex = Integer.parseInt(index);
            if (inputOrderIndex > notes.size()) {
                throw new IndexOutOfBoundsException(INVALID_NOTE_INDEX_MESSAGE);
            }
            ui.printExistingNotes(this, inputOrderIndex);
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Opens the note with given index.
     *
     * @throws IndexOutOfBoundsException If user input index is bigger than the size of note list.
     * @throws NumberFormatException If user input is invalid.
     */
    public void openNoteDirectly() throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteOpenInstructions();
        String userInput = ui.readUserInput();
        //here the index is not scene index, it is the index in the list
        if (!userInput.equals("/quit")) {
            int inputOrderIndex = Integer.parseInt(userInput);
            if (inputOrderIndex > notes.size()) {
                throw new IndexOutOfBoundsException(INVALID_NOTE_INDEX_MESSAGE);
            }
            ui.printExistingNotes(this, inputOrderIndex);
        } else {
            ui.printQuitNoteProcess();
        }
    }

    /**
     * Delete note.
     *
     * @throws IndexOutOfBoundsException If user input index is bigger than the size of note list.
     * @throws NumberFormatException If user input is invalid.
     */
    public void deleteNoteProcess() throws IndexOutOfBoundsException, NumberFormatException {
        ui.printNoteListStarter();
        ui.printAllNotes(this);
        ui.printNoteDeleteInstructions();
        String userInput = ui.readUserInput();
        if (userInput.equals("all")) {
            deleteAllNotes();
        } else if (userInput.equals("/quit")) {
            ui.printQuitNoteProcess();
        } else {
            int deletedNoteIndex = Integer.parseInt(userInput) - 1;
            this.deleteNote(deletedNoteIndex);
        }
    }
}
