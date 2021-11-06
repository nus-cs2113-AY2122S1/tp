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

    public void setEvent(Event event) {
        this.event = event;
    }

    public void addMember(Member member) {
        memberList.add(member);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s (by: %s)", this.getStatusIcon(), this.getTitle(), this.getStringDateTime());
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public Member getFromMemberList(int index) {
        return memberList.get(index);
    }

    public String getMemberListAsString() {
        StringBuilder members = new StringBuilder();
        int counter = 1;
        for (Member member : memberList) {
            members.append(counter).append(" ");
            members.append(member).append("\n");
            counter++;
        }
        return members.toString();
    }
}
