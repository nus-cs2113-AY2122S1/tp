package gordon.command;

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
        for (int i = 0; i < priceFilter.size(); i++) {
            System.out.println((i + 1) + ". " + priceFilter.get(i).getName()
                    + " (Price: $" + String.format("%.2f", priceFilter.get(i).getTotalPrice()) + ")");
        }
    }
}
