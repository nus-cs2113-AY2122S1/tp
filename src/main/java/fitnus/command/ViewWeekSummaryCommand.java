package fitnus.command;

import fitnus.database.EntryDatabase;
import fitnus.database.FoodDatabase;
import fitnus.database.MealPlanDatabase;
import fitnus.exception.FitNusException;
import fitnus.tracker.Summary;
import fitnus.utility.User;

public class ViewWeekSummaryCommand extends Command {

    public static final int DAYS_IN_WEEK = 7;

    public ViewWeekSummaryCommand() {
    }

    @Override
    public String execute(EntryDatabase ed, FoodDatabase fd, MealPlanDatabase md, User us) throws FitNusException {
        // Retrieves all entries that fall in the past week
        EntryDatabase pastWeekEntries = ed.getPastDaysEntryDatabase(DAYS_IN_WEEK);
        Summary sum = new Summary(pastWeekEntries, DAYS_IN_WEEK);

        return sum.generateWeekSummaryReport();
    }
}
