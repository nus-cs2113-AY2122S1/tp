package medbot.command;

import medbot.Ui;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;
import medbot.person.Person;

public class EditPersonCommand extends Command {
    protected int personId;
    protected Person person;

    public EditPersonCommand(int personId, Person person) {
        this.personId = personId;
        this.person = person;
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.editPerson(personId, person);
    }
}
