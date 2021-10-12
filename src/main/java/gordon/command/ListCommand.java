package gordon.command;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

public class ListCommand extends Command {
    @Override
    public void execute(Cookbook cookbook) {
        System.out.print(cookbook);
    }
}
