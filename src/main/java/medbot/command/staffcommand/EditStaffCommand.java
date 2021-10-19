package medbot.command.staffcommand;

import medbot.command.EditCommand;
import medbot.person.Staff;

public class EditStaffCommand extends EditCommand {
    public EditStaffCommand(int staffId, Staff staff) {
        super(staffId, staff);
    }
}
