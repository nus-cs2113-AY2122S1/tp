package medbot.command;

import medbot.Ui;
import medbot.list.PersonList;

public class ListCommand extends Command {
    @Override
    public void execute(PersonList personList, Ui ui) {
        System.out.println("Print a list of people");
    }
}
