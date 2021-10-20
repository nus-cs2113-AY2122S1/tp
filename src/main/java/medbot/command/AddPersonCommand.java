package medbot.command;

import medbot.person.Person;

public abstract class AddPersonCommand extends Command {
    protected Person person;

    public AddPersonCommand(Person person) {
        this.person = person;
    }
}
