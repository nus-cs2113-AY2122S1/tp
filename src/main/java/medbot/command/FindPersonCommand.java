package medbot.command;

import medbot.Ui;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

public class FindPersonCommand extends Command {
    protected String[] parameters;

    public FindPersonCommand(String[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.findPersons(parameters);
    }
}
