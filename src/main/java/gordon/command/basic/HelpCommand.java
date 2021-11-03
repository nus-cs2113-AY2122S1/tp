package gordon.command.basic;

import gordon.command.Command;
import gordon.kitchen.Cookbook;
import gordon.util.UI;

public class HelpCommand extends Command {
    UI message = new UI();


    @Override
    public void execute(Cookbook cookbook) {
        message.printHelp();
    }
}
