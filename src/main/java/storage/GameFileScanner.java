package storage;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;

public class GameFileScanner {
    GameFileManager file;
    String lines;

    public GameFileScanner(GameFileManager file)
            throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.file = file;
        lines = file.readFile();
    }

    public boolean hasNext() {
        return lines.contains("\n");
    }

    public String nextLine() {
        String nextLine = lines.substring(0, lines.indexOf("\n"));
        lines = lines.substring(lines.indexOf("\n") + 1);
        return nextLine;
    }
}
