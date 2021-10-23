package seedu.duke.items;

import seedu.duke.items.characteristics.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Task extends Item {

    public static final String TASK_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public ArrayList<Member> memberList = new ArrayList<>();
    private Event event;

    public Task(String title, String description, LocalDateTime deadline) {
        super("task", title, description, deadline);
    }

    public Task(String title, String description, LocalDateTime deadline, ArrayList<Member> memberList, Event event) {
        super("task", title, description, deadline);
        this.memberList = memberList;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s (by: %s)", this.getStatusIcon(), this.getTitle(), this.getStringDateTime());
    }
}
