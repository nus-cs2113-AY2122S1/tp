package medbot.command;

import medbot.Ui;
import medbot.list.PersonList;
import medbot.person.Person;

public class AddCommand extends Command {
    protected Person person;

    public AddCommand(Person person) {
        this.person = person;
    }

    @Override
    public void execute(PersonList personList, Ui ui) {
        int personId = personList.addPerson(person);
    }
}
