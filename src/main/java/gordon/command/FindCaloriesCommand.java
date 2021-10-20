package gordon.command;

import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;

public class FindCaloriesCommand extends Command {
    int calories;

    public FindCaloriesCommand(int calories) {
        this.calories = calories;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by calories...");
        ArrayList<Recipe> calFilter = cookbook.filterByCalories(calories);
        for (int i = 0; i < calFilter.size(); i++) {
            System.out.println((i + 1) + ". " + calFilter.get(i).getName()
                    + " (Calories (kcal): " + calFilter.get(i).getCalories() + ")");
        }
    }
}
