package fitnus.command;

import fitnus.database.EntryDatabase;
import fitnus.database.FoodDatabase;
import fitnus.database.MealPlanDatabase;
import fitnus.exception.FitNusException;
import fitnus.tracker.Food;
import fitnus.utility.Ui;
import fitnus.utility.User;

import java.util.ArrayList;

public class FindFoodsCommand extends Command {
    private final String keyword;

    public FindFoodsCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(EntryDatabase ed, FoodDatabase fd, MealPlanDatabase md, User us) throws FitNusException {
        ArrayList<Food> matchingFoods = fd.findFoods(keyword);
        if (matchingFoods.size() > 0) {
            System.out.println("Here are the matching foods in your database:");
            Ui.printMatchingFoods(matchingFoods);
        }
        return "Found " + matchingFoods.size() + " matching foods";
    }

}
