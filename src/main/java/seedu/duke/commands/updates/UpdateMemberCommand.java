package seedu.duke.commands.updates;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.items.Task;

public class UpdateMemberCommand extends Command {

    private final int memberToBeAdded;
    private final int memberToBeRemoved;
    private final Task taskToBeUpdated;
    private final String flag;

    public UpdateMemberCommand(String flag, int memberToBeAdded, Task taskToBeUpdated, int memberToBeRemoved) {
        this.memberToBeAdded = memberToBeAdded;
        this.memberToBeRemoved = memberToBeRemoved;
        this.taskToBeUpdated = taskToBeUpdated;
        this.flag = flag;
    }

    public CommandResult execute() throws DukeException, InvalidBudgetException {
        if (flag.equalsIgnoreCase("add")) {
            addMember(memberToBeAdded);
        } else if (flag.equalsIgnoreCase("change")) {
            removeMember(memberToBeRemoved);
            addMember(memberToBeAdded);
        } else if (flag.equalsIgnoreCase("remove")) {
            removeMember(memberToBeRemoved);
        }
        Ui.postUpdateMessage(taskToBeUpdated.getEvent());
        return new CommandResult(Ui.updateExitMessage());
    }

    private void addMember(int memberToBeAdded) {
        Duke.memberRoster.get(memberToBeAdded).addToAssignedTasks(taskToBeUpdated);
        Duke.memberRoster.get(memberToBeAdded).sortTasks();
        taskToBeUpdated.addMember(Duke.memberRoster.get(memberToBeAdded));

    }

    private void removeMember(int memberToBeRemoved) {
        taskToBeUpdated.memberList.remove(memberToBeRemoved);
    }
}
