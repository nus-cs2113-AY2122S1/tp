package gordon.command.find;

import gordon.command.Command;
import gordon.exception.GordonException;
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
        try {
            assert calFilter.size() > 0;
            if (calFilter.get(0).getCalories() == -1) {
                throw new GordonException(GordonException.NO_RESULT_FOUND);
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
        for (int i = 0; i < calFilter.size(); i++) {
            int calGet = calFilter.get(i).getCalories();
            if (calGet > 0) {
                System.out.println((i + 1) + ". " + calFilter.get(i).getName()
                        + " (Calories (kcal): " + calGet + ")");
            }
        }
    }
}
