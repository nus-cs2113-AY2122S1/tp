package medbot.command;

import medbot.Ui;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

public class DeletePersonCommand extends Command {
    protected int personId;

    public DeletePersonCommand(int personId) {
        this.personId = personId;
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.deletePerson(personId);
    }
}
