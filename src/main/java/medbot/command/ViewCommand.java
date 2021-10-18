package medbot.command;

import medbot.Ui;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

public class ViewCommand extends Command {
    protected int personId = 0;

    public ViewCommand(int personId) {
        this.personId = personId;
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        String patientInfo = personList.getPersonInfo(personId);
    }
}
