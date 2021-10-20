package medbot.command.personcommand;

import medbot.command.Command;
import medbot.person.Person;

public abstract class EditPersonCommand extends Command {
    protected int personId;
    protected Person person;

    public EditPersonCommand(int personId, Person person) {
        this.personId = personId;
        this.person = person;
    }
}
