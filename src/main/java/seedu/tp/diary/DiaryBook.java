package seedu.tp.diary;

import seedu.tp.command.flags.DiaryFlag;
import seedu.tp.exception.MissingDiaryMessageException;
import seedu.tp.exception.MissingDiaryFieldException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.exception.MissingUserTimeException;
import seedu.tp.parser.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;

public class DiaryBook {
    private static ArrayList<Diary> diaryBook = new ArrayList<>(100);
    private static LocalDate latestDate;

    public static String writeToDiary(String message) {
        LocalDate currentDate = LocalDate.now();
        if (latestDate == null) {
            diaryBook.add(new Diary(message, currentDate));
        } else if (latestDate.isBefore(currentDate)) {
            if (diaryBook.size() == 100) {
                diaryBook.remove(0);
            }
            diaryBook.add(new Diary(message, currentDate));
            latestDate = currentDate;
        } else if (latestDate.isEqual(currentDate)) {
            int lastIndex = diaryBook.size() - 1;
            diaryBook.get(lastIndex).write(message);
        }
        return "The following content: \n" + message
                + "\n" + "added to today's diary.";
    }

    public static void addToDiary(String message, LocalDate date) {
        diaryBook.add(new Diary(message, date));
        latestDate = date;
    }

    public static String readDiary(LocalDate date) {
        for (Diary diary : diaryBook) {
            if (date.isEqual(diary.getDate())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return "This is your diary on "
                        + date.format(formatter) + " :\n"
                        + diary.getContent();
            }
        }
        return "You do not have any diary for this date.";
    }

    public static String deleteDiary(LocalDate date) {
        for (Diary diary : diaryBook) {
            if (date.isEqual(diary.getDate())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return "The following diary deleted. \n"
                        + date.format(formatter) + ":\n"
                        + diary.getContent();
            }
        }
        return "You do not have any diary on this date.";
    }

    public static String processCommand(Map<String, String> commandArguments) throws MissingDiaryMessageException,
            ParseDateFailedException, MissingUserTimeException, MissingDiaryFieldException, DateTimeParseException {
        if (commandArguments.containsKey(DiaryFlag.MESSAGE)) {
            if (commandArguments.get(DiaryFlag.MESSAGE) != null) {
                String userMessage = commandArguments.get(DiaryFlag.MESSAGE);
                return writeToDiary(userMessage);
            } else {
                throw new MissingDiaryMessageException();
            }
        }
        if (commandArguments.containsKey(DiaryFlag.DELETE_DATE)) {
            if (commandArguments.get(DiaryFlag.DELETE_DATE) != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate userDate = LocalDate.parse(commandArguments.get(DiaryFlag.DELETE_DATE), formatter);
                return deleteDiary(userDate);
            } else {
                throw new MissingUserTimeException();
            }
        }
        if (commandArguments.containsKey(DiaryFlag.READ_DATE)) {
            if (commandArguments.get(DiaryFlag.READ_DATE) != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate userDate = LocalDate.parse(commandArguments.get(DiaryFlag.DELETE_DATE), formatter);
                return readDiary(userDate);
            } else {
                throw new MissingUserTimeException();
            }
        }
        throw new MissingDiaryFieldException();
    }

    public static String convertToStorage() {
        String content = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Diary diary : diaryBook) {
            content += "date: " + diary.getDate().format(formatter) + System.lineSeparator();
            content += diary.getContent();
        }
        return content;
    }
}
