//@@author peng-217

package storage;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;
import exceptions.NoteCorruptedFileException;
import note.NoteList;
import note.Note;

import java.io.File;
import java.io.IOException;


public class GameNoteFileManager {

    private static final String NOTE_CORRUPTED_MESSAGE = "The note data file is corrupted!"
            + " A new note data file will be created. ";
    private static GameFileManager noteFile;

    public GameNoteFileManager() {
        try {
            new File("data").mkdir();
            new File("notes.txt").createNewFile();
            noteFile = new GameFileManager("notes.txt");
        } catch (DukeFileNotFoundException | DukeCorruptedFileException | IOException e) {
            forceClearNote();
        }
    }

    /**
     * Saves all the exiting note into a local data file.
     *
     * @param notes The note list to be saved.
     */
    public void saveNote(NoteList notes) {
        File saveDirectory =  new File("data");
        saveDirectory.mkdir();
        try {
            new File(saveDirectory,"notes.txt").createNewFile();
            for (int i = 0; i < notes.getSize(); i++) {
                String titleToWrite = notes.getIndexNote(i).getNoteTitle();
                String contentToWrite = notes.getIndexNote(i).getNoteContent();
                StringBuilder lines = new StringBuilder();
                lines.append("scene ").append(notes.getIndexNote(i).getNoteSceneIndex()).append("\n");
                lines.append(titleToWrite).append("\n");
                lines.append(contentToWrite).append("\n");
                lines.append("End of this note.").append("\n");
                noteFile.writeFile(lines.toString());
            }
        } catch (IOException | DukeFileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes all the locally saved notes.
     *
     * @param notes The note list that going to be initialized.
     * @throws NoteCorruptedFileException If there is corruption in note data file.
     */
    public void openNoteFromFile(NoteList notes) throws NoteCorruptedFileException {
        new File("data").mkdir();
        try {
            new File("notes.txt").createNewFile();
            GameFileScanner lines = new GameFileScanner(noteFile);

            while (lines.hasNext()) {
                //String nextLine = scanNote.nextLine();
                String nextLine = lines.nextLine();
                if (!nextLine.startsWith("scene")) {
                    throw new NoteCorruptedFileException(NOTE_CORRUPTED_MESSAGE);
                }
                int sceneIndex = Integer.parseInt(nextLine.substring(6));
                String title = lines.nextLine();
                String content = new String("");
                String contentPart = lines.nextLine();
                //int emptyLineCounter = 0;
                boolean missEndFlag = false;
                while (!contentPart.equals("End of this note.")) {
                    content += contentPart;
                    contentPart = lines.nextLine();
                    if (contentPart.equals("")) {
                        missEndFlag = true;
                        break;
                    }
                }
                if (missEndFlag) {
                    throw new NoteCorruptedFileException(NOTE_CORRUPTED_MESSAGE);
                }
                Note savedNote = new Note(content, title, sceneIndex);
                notes.createNoteFromFile(savedNote, sceneIndex);
            }
        } catch (IOException | DukeFileNotFoundException | DukeCorruptedFileException e) {
            throw new NoteCorruptedFileException("cannot open note from file");
        }
    }


    /**
     * Clears all locally saved notes if corruption is detected.
     */
    public void forceClearNote() {
        try {
            new File("data").mkdir();
            new File("notes.txt").createNewFile();
            noteFile.writeFile("");
        } catch (DukeFileNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
