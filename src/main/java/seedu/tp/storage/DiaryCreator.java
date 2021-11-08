package seedu.tp.storage;

import seedu.tp.diary.DiaryBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DiaryCreator {
    private static final String DIARY_STORAGE_FOLDER = "data/";
    private static final String DIARY_STORAGE_FILE = "data/diary.txt";

    public DiaryCreator() {
        try {
            createFolder();
            createFile();
            readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createFile() {
        File newFile = new File(DIARY_STORAGE_FILE);
        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createFolder() {
        File newFolder = new File(DIARY_STORAGE_FOLDER);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
    }

    public static void storeToFile() throws IOException {
        FileWriter fw = new FileWriter(DIARY_STORAGE_FILE, true);
        fw.write(DiaryBook.convertToStorage());
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(DIARY_STORAGE_FILE);
        fw.write("");
        fw.close();
    }

    public static void readFile() throws FileNotFoundException {
        File f = new File(DIARY_STORAGE_FILE);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String content = "";
            LocalDate date = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(s.nextLine().substring(6), formatter);
            while (s.hasNext()) {
                String message = s.nextLine();
                if (message.startsWith("date: ")) {
                    content += message + System.lineSeparator();
                }
            }
            DiaryBook.addToDiary(content, date);
        }
    }
}
