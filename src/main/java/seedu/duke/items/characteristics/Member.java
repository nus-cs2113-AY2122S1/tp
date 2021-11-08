package seedu.duke.items.characteristics;

import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.Comparator;

public class Member {

    private String name;
    private final ArrayList<Task> assignedTasks = new ArrayList<>();
    public static final String MEMBER_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";


    public Member(String name) {
        this.name = name;
    }

    public static Comparator<Member> NameComparator = (member1, member2) -> {
        String name1 = member1.getName().toUpperCase();
        String name2 = member2.getName().toUpperCase();

        return name1.compareTo(name2);
    };

    public void addTask(Task task) {
        assignedTasks.add(task);
    }

    public void deleteTask(int index) {
        assignedTasks.remove(index);
    }

    public String getName() {
        return name;
    }

    // @@author alwinangys
    public String getTasks() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < assignedTasks.size(); i++) {
            Task task = assignedTasks.get(i);
            tasks.append(task);
            tasks.append("\n\tUnder the event: " + task.getEvent().getTitle());
            if (i < assignedTasks.size() - 1) {
                tasks.append("\n");
            }
        }

        return tasks.toString();
    }
    // @@author alwinangys

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void addToAssignedTasks(Task task) {
        assignedTasks.add(task);
    }

    public void sortTasks() {
        assignedTasks.sort(Task.DateTimeComparator);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
