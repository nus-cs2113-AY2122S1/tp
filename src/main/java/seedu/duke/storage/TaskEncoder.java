package seedu.duke.storage;

import seedu.duke.Duke;
import seedu.duke.items.mainlists.EventCatalog;
import seedu.duke.parser.Parser;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskEncoder {

    protected static List<String> encodeTasksList(ArrayList<Task> tasksToSave) {
        List<String> encodedTasks = new ArrayList<>();
        tasksToSave.forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }

    private static String encodeTaskToString(Task task) {
        return "t | "
                + task.getTitle()
                + " | "
                + task.getStatusIcon()
                + " | "
                + task.getDescription()
                + " | "
                + Parser.convertDateTimeForSaving(task.getDateTime())
                + " |"
                + encodeMemberListToString(task)
                + " | "
                + Duke.eventCatalog.indexOf(task.getEvent())
                + " | "
                + task.getEvent().getTitle();
    }

    private static String encodeMemberListToString(Task task) {
        StringBuilder encodedMemberList = new StringBuilder();
        task.memberList.forEach(member -> encodedMemberList.append(" ")
                .append(MemberEncoder.encodeMemberNameToString(member)));
        return encodedMemberList.toString();
    }
}
