package seedu.tp.diary;

import java.time.LocalDate;

public class Diary {
    private LocalDate date;
    private String message = "";

    public Diary(String message, LocalDate date) {
        this.date = date;
        this.message = message;
    }

    public void write(String message) {
        this.message += message + System.lineSeparator();
    }

    public String getContent() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }
}
