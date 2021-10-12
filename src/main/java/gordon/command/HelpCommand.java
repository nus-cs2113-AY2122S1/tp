package gordon.command;

import gordon.kitchen.Cookbook;

public class HelpCommand extends Command {
    static final String helpString = "add \"recipe name\" \"/ingredients\" 1+2+3 \"/steps\" 1+2+3";

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println(helpString);
    }
}
