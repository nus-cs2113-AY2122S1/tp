package seedu.duke.commands;


import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import java.util.ArrayList;

import static seedu.duke.parser.ItemType.EVENT;
import static seedu.duke.parser.ItemType.TASK;
import static seedu.duke.parser.ItemType.MEMBER;

public class DeleteCommand extends Command {

    protected ItemType itemType;
    private int eventIndex;
    private int taskIndex;
    private int memberIndex;
    private boolean canDeleteMember;

    public DeleteCommand(ItemType itemType, int index) {
        this.itemType = itemType;
        assert itemType == EVENT | itemType == TASK | itemType == MEMBER;
        if (itemType.equals(EVENT)) {
            eventIndex = index;
        } else if (itemType.equals(TASK)) {
            taskIndex = index;
        } else {
            memberIndex = index;
        }
    }

    public CommandResult execute() {
        String deletedItem;

        switch (itemType) {
        case EVENT:
            deletedItem = deleteEvent(eventIndex);
            return new CommandResult(Ui.getEventDeletionMessage(deletedItem));
        case TASK:
            deletedItem = deleteTask(taskIndex);
            return new CommandResult(Ui.getTaskDeletionMessage(deletedItem));
        case MEMBER:
            deletedItem = deleteMember(memberIndex);
            return new CommandResult(Ui.getMemberDeletionMessage(deletedItem));
        default:
            return new CommandResult("Something went wrong.");
        }
    }

    private static String deleteEvent(int index) {
        Event event = Duke.eventCatalog.get(index);
        String eventTitle = event.getTitle();
        Parser.updateIndexOfLastSelectedEvent(index);
        for (int i = 0; i < event.getTaskList().size(); i++) {
            deleteTask(i);
        }
        Duke.eventCatalog.remove(index);
        Parser.updateIndexToNoEventSelected();
        return eventTitle;
    }

    private static String deleteTask(int index) {
        int lastEventIndex = Parser.getIndexOfLastSelectedEvent();
        Event parentEvent = Duke.eventCatalog.get(lastEventIndex);
        String parentEventTitle = parentEvent.getTitle();
        Task taskToDelete = Duke.eventCatalog.get(lastEventIndex).getFromTaskList(index);
        String taskTitle = taskToDelete.getTitle();

        ArrayList<Member> memberList = taskToDelete.getMemberList();
        deleteTaskFromMemberTaskList(memberList, taskTitle, parentEventTitle);
        Duke.eventCatalog.get(lastEventIndex).getTaskList().remove(index);
        return taskTitle;
    }

    private static String deleteMember(int index){
        Member memberToDelete =  Duke.memberRoster.get(index);
        String memberName = memberToDelete.getName();

        ArrayList<String> tasksContainingOnlyMemberToDelete = new ArrayList<>();
        StringBuilder details = new StringBuilder();
        for (int i = 0; i < Duke.eventCatalog.size(); i++) {
            for (Task task: Duke.eventCatalog.get(i).getTaskList()) {
                for (Member member: task.getMemberList()) {
                    if (member.getName().equalsIgnoreCase(memberName)) {
                        details.append("Event #").append(i).append(": ").append(task.getTitle());
                        details.append(System.lineSeparator());
                    }
                }
                tasksContainingOnlyMemberToDelete.add(details.toString());
            }
        }

        if (tasksContainingOnlyMemberToDelete.isEmpty()) {
            for (int i = 0; i < Duke.eventCatalog.size(); i++) {
                for (Task task: Duke.eventCatalog.get(i).getTaskList()) {
                    int counter = 0;
                    for (Member member: task.getMemberList()) {
                        if (member.getName().equalsIgnoreCase(memberName)) {
                            task.memberList.remove(counter);
                        }
                        counter++;
                    }
                }
            }
            for (int i = 0; i < Duke.memberRoster.size(); i++) {
                if (Duke.memberRoster.get(i).getName().equalsIgnoreCase(memberName)) {
                    Duke.memberRoster.remove(i);
                    break;
                }
            }
        } else {
            return "Please assign more members to these tasks:" + System.lineSeparator()
                    + tasksContainingOnlyMemberToDelete;
        }
        return memberName;
    }

    private static void deleteTaskFromMemberTaskList(ArrayList<Member> memberList, String taskTitle, String parentEventTitle) {
        for (Member member: memberList) {
            for (int i = 0; i < member.getAssignedTasks().size(); i++) {
                Task task = member.getAssignedTasks().get(i);
                if (canDeleteTask(task, taskTitle, parentEventTitle)) {
                    member.deleteTask(i);
                }
            }
        }
    }

    private static boolean canDeleteTask(Task task, String taskTitle, String parentEventTitle) {
        return task.getTitle().equals(taskTitle) && task.getEvent().getTitle().equals(parentEventTitle);
    }
}

