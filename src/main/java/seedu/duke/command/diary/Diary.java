package seedu.duke.command.diary;

import java.time.LocalDate;

public class Diary {
    private LocalDate date;
    private String message;

    public Diary() {
        this.date = LocalDate.now();
        this.message = null;
    }

    public void write(String message) {
        this.message += message;
    }

    public String getContent() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }
}
