package gordon.command.find;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;

public class FindTimeCommand extends Command {
    int time;

    public FindTimeCommand(int time) {
        this.time = time;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by total time...");
        ArrayList<Recipe> timeFilter = cookbook.filterByTime(time);
        try {
            assert timeFilter.size() > 0;
            if (timeFilter.get(0).getTotalTime() == -1) {
                throw new GordonException(GordonException.NO_RESULT_FOUND);
            }
            for (int i = 0; i < timeFilter.size(); i++) {
                int timeGet = timeFilter.get(i).getTotalTime();
                if (timeGet > 0) {
                    System.out.println((i + 1) + ". " + timeFilter.get(i).getName()
                            + " (Total Time: " + timeGet + ")");
                }
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
