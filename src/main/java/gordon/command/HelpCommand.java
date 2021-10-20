package gordon.command;

import gordon.kitchen.Cookbook;

public class HelpCommand extends Command {
    static final String helpString = "To add a recipe: add \"recipe name\" \"/ingredients\" 1+2+3 \"/steps\" 1+2+3 \"/calories\" 123";

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println(helpString);
    }
}
