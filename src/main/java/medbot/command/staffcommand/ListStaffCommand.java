package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.ListCommand;
import medbot.list.PersonList;

public class ListStaffCommand extends ListCommand {

    @Override
    public void execute(PersonList personList, Ui ui) {
        String allPatientsString = ui.getAllPatientsString(personList);
        ui.printOutput(allPatientsString);
    }
}
