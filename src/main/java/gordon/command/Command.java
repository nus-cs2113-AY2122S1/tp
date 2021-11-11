package gordon.command;

import gordon.kitchen.Cookbook;

/** <h1>Command class.</h1>
 *
 * <p> Handles the giving of commands to cookbook.</p>
 */
public abstract class Command {
    public abstract void execute(Cookbook cookbook);
}
