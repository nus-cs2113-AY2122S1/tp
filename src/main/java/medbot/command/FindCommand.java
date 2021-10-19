package medbot.command;

import medbot.Ui;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

public class FindCommand extends Command {
    protected String[] parameters;

    public FindCommand(String[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.findPersons(parameters);
    }
}
