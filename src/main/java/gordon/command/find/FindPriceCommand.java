package gordon.command.find;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;

public class FindPriceCommand extends Command {
    float price;

    public FindPriceCommand(float price) {
        this.price = price;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by price...");
        ArrayList<Recipe> priceFilter = cookbook.filterByPrice(price);
        try {
            assert priceFilter.size() > 0;
            if (priceFilter.get(0).getTotalPrice() == -1) {
                throw new GordonException(GordonException.NO_RESULT_FOUND);
            }
            for (int i = 0; i < priceFilter.size(); i++) {
                float getPrice = priceFilter.get(i).getTotalPrice();
                if (getPrice > 0) {
                    System.out.println((i + 1) + ". " + priceFilter.get(i).getName()
                            + " (Price: $" + String.format("%.2f", getPrice) + ")");
                }
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
