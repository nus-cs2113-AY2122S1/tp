package seedu.duke.commands;


import seedu.duke.Duke;
import seedu.duke.Ui;
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
    private static boolean canDeleteMember;
    private static boolean isDeleteAll = false;

    public DeleteCommand() {
        isDeleteAll = true;
    }

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
        if (isDeleteAll) {
            deleteEverything();
            return new CommandResult("I have deleted everything!");
        }
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
            if (canDeleteMember) {
                return new CommandResult(Ui.getMemberDeletionMessage(deletedItem));
            }
            return new CommandResult(Ui.getUnableToDeleteMemberMessage(deletedItem));
        default:
            return new CommandResult("Something went wrong.");
        }
    }

    private static String deleteEvent(int index) {
        Event event = Duke.eventCatalog.get(index);
        Parser.updateIndexOfLastSelectedEvent(index);
        for (int i = 0; i < event.getTaskList().size(); i++) {
            deleteTask(i);
        }
        Duke.eventCatalog.remove(index);
        Parser.updateIndexToNoEventSelected();
        return event.getTitle();
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

    private static String deleteMember(int index) {
        canDeleteMember = false;
        Member memberToDelete =  Duke.memberRoster.get(index);
        String memberName = memberToDelete.getName();

        ArrayList<String> tasksContainingOnlyMemberToDelete = new ArrayList<>();

        for (int i = 0; i < Duke.eventCatalog.size(); i++) {
            String eventName = Duke.eventCatalog.get(i).getTitle();
            for (Task task: Duke.eventCatalog.get(i).getTaskList()) {
                int taskListSize = task.memberList.size();
                for (Member member: task.getMemberList()) {
                    if (member.getName().equalsIgnoreCase(memberName) && taskListSize == 1) {
                        String details = "Event: " + eventName + "; Task: " + task.getTitle();
                        tasksContainingOnlyMemberToDelete.add(details);
                    }
                }
            }
        }
        if (tasksContainingOnlyMemberToDelete.isEmpty()) {
            for (int i = 0; i < Duke.eventCatalog.size(); i++) {
                for (Task task: Duke.eventCatalog.get(i).getTaskList()) {
                    int counter = 0;
                    for (Member member: task.getMemberList()) {
                        if (member.getName().equalsIgnoreCase(memberName)) {
                            task.memberList.remove(counter);
                            break;
                        }
                        counter++;
                    }
                }
            }
            deleteMemberFromRoster(memberName);
            canDeleteMember = true;
            return memberName;
        }
        return tasksContainingOnlyMemberToDelete.toString();
    }


    private static void deleteTaskFromMemberTaskList(ArrayList<Member> memberList, String taskTitle,
                                                     String parentEventTitle) {
        for (Member member: memberList) {
            for (int i = 0; i < member.getAssignedTasks().size(); i++) {
                Task task = member.getAssignedTasks().get(i);
                if (canDeleteTask(task, taskTitle, parentEventTitle)) {
                    member.deleteTask(i);
                    i--;
                }
            }
        }
    }

    private static boolean canDeleteTask(Task task, String taskTitle, String parentEventTitle) {
        return task.getTitle().equals(taskTitle) && task.getEvent().getTitle().equals(parentEventTitle);
    }

    private static void deleteMemberFromRoster(String memberName) {
        for (int i = 0; i < Duke.memberRoster.size(); i++) {
            if (Duke.memberRoster.get(i).getName().equalsIgnoreCase(memberName)) {
                Duke.memberRoster.remove(i);
                return;
            }
        }
    }

    private static void deleteEverything() {
        Duke.eventCatalog.clear();
        Duke.memberRoster.clear();
    }
}

