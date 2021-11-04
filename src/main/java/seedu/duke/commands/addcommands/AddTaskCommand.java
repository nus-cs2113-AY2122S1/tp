package seedu.duke.commands.addcommands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddTaskCommand extends Command {

    private final String title;
    private final String description;
    private final LocalDateTime dateTime;
    private final int eventIndex;
    private final int[] memberIndexes;

    private final ArrayList<Member> memberList = new ArrayList<>();
    private Event assignedEvent;

    public AddTaskCommand(String title, String description, LocalDateTime dateTime,
                          int eventIndex, int[] memberIndexes) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.eventIndex = eventIndex;
        this.memberIndexes = memberIndexes;
    }

    private void setMemberList() {
        for (int index : memberIndexes) {
            memberList.add(Duke.memberRoster.get(index));
            memberList.sort(Member.NameComparator);
        }
    }

    private void setAssignedEvent() {
        assignedEvent = Duke.eventCatalog.get(eventIndex);
    }

    private void addTaskToEvent(Task task) {
        Duke.eventCatalog.get(eventIndex).addToTaskList(task);
        Duke.eventCatalog.sortCatalog();
    }

    private void addTaskToMembers(Task task) {
        for (int index : memberIndexes) {
            Duke.memberRoster.get(index).addToAssignedTasks(task);
            Duke.memberRoster.get(index).sortTasks();
        }
    }

    public CommandResult execute() {
        setMemberList();
        setAssignedEvent();
        Task task = new Task(title, description, dateTime, memberList, assignedEvent);
        addTaskToEvent(task);
        addTaskToMembers(task);
        return new CommandResult(Ui.getTaskAddedMessage(eventIndex, task));
    }
}
