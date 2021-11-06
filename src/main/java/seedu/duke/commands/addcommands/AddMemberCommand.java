package seedu.duke.commands.addcommands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.characteristics.Member;

public class AddMemberCommand extends Command {

    private final String[] memberNames;

    public AddMemberCommand(String[] memberNames) {
        this.memberNames = memberNames;
    }

    private void addToMemberRoster(Member member) {
        Duke.memberRoster.add(member);
        Duke.memberRoster.sortRoster();
    }

    public CommandResult execute() {
        for (String memberName : memberNames) {
            Member member = new Member(memberName);
            addToMemberRoster(member);
        }
        return new CommandResult(Ui.getMembersAddedMessage(memberNames));
    }
}
