package medbot.command;

import medbot.Ui;
import medbot.list.PersonList;

public class ListPersonCommand extends Command {
    @Override
    public void execute(PersonList personList, Ui ui) {
        System.out.println("Print a list of people");
    }
}
