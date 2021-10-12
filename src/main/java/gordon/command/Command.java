package gordon.command;

import gordon.kitchen.Cookbook;

public abstract class Command {
    public abstract void execute(Cookbook cookbook);
}
