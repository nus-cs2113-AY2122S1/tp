package gordon.command.set;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

public class SetPriceCommand extends Command {
    String recipeName;
    float newPrice;

    public SetPriceCommand(String recipeName, float newPrice) {
        this.recipeName = recipeName;
        this.newPrice = newPrice;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the setPrice function and prints the corresponding message</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Setting price...");
        try {
            cookbook.setPrice(recipeName, newPrice);
            System.out.println("Price set successfully.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
