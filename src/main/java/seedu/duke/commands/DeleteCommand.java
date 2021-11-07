package seedu.duke.commands;


import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import java.util.ArrayList;

import static seedu.duke.parser.ItemType.EVENT;
import static seedu.duke.parser.ItemType.TASK;

public class DeleteCommand extends Command {

    protected ItemType itemType;
    private int eventIndex;
    private int taskIndex;
    private int memberIndex;

    public DeleteCommand(ItemType itemType, int index) {
        this.itemType = itemType;
        if (itemType.equals(EVENT)) {
            eventIndex = index;
        } else if (itemType.equals(TASK)) {
            taskIndex = index;
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
        default:
            return new CommandResult("Something went wrong.");
        }
    }

    private static String deleteEvent(int index) {
        Event event = Duke.eventCatalog.get(index);
        String eventTitle = event.getTitle();
        Parser.updateIndexOfLastSelectedEvent(index);
        // for each task in the event, use deleteTask to handle deletion of tasks from member's tasklist
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

        // go to taskToDelete
        // obtain that taskToDelete's member list
        // for each member,
            // access the member's task list
            // iterate through the task list,
                // if the task's title matches taskToDelete's title AND the task's event matches
                // taskToDelete's event, delete the task
        // finally, delete the task from the event in eventCatalog
        // return the taskTitle

        ArrayList<Member> memberList = taskToDelete.getMemberList();
        deleteTaskFromMemberTaskList(memberList, taskTitle, parentEventTitle);
        Duke.eventCatalog.get(lastEventIndex).getTaskList().remove(index);
        return taskTitle;
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

