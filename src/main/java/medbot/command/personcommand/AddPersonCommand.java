package medbot.command.personcommand;

import medbot.command.Command;
import medbot.person.Person;

public abstract class AddPersonCommand extends Command {
    protected Person person;

    public AddPersonCommand(Person person) {
        this.person = person;
    }
}
