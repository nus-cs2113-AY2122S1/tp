package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.ListPersonCommand;
import medbot.list.PersonList;

public class ListStaffCommand extends ListPersonCommand {

    @Override
    public void execute(PersonList personList, Ui ui) {
        String allPatientsString = ui.getAllPatientsString(personList);
        ui.printOutput(allPatientsString);
    }
}
