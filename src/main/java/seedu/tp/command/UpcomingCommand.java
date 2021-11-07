package seedu.tp.command;

import seedu.tp.task.RecurrenceEnum;
import seedu.tp.task.Task;
import seedu.tp.task.taskmanager.TaskManager;
import seedu.tp.task.type.Lesson;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class UpcomingCommand extends Command {
    public UpcomingCommand(TaskManager taskManager, Map<String, String> commandOptions) {
        super(taskManager, commandOptions);
    }

    @Override
    protected String getUsage() {
        return null;
    }


    private class SortByTaskHappenTime implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            assert o1.getHappenTime() != null && o2.getHappenTime() != null;
            return o1.getHappenTime().compareTo(o2.getHappenTime());
        }
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        Period distance = Period.ofWeeks(1);
        LocalDateTime end = LocalDateTime.now().plus(distance);
        String message = taskManager.getAllTasksView().stream().filter(task -> {
            RecurrenceEnum re = task.getRecurrence();
            LocalDateTime startTime = task.getHappenTime();
            LocalDateTime next;
            if (startTime == null) {
                return true;
            }
            if (task instanceof Lesson) {
                next = ((Lesson)task).getNextOccurrence();
            } else {
                // check if start time fall in the next distance time
                return end.isAfter(task.getRecurrence().getNextRecurredDate(startTime));
            }
            return end.isAfter(next);
        }).sorted(new SortByTaskHappenTime()).map(Task::getTaskEntryDescription)
                .collect(Collectors.joining(System.lineSeparator()));
        return new CommandResult(message, false);
    }
}
