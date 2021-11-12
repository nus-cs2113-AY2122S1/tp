package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.fluid.FluidExceptions;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.meal.MealException;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.workout.WorkoutException;
import seedu.duke.schedule.ScheduleTracker;
import seedu.duke.exceptions.weight.WeightException;
import seedu.duke.exceptions.LoadException;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.logging.LogManager;

import static seedu.duke.ClickfitMessages.IO_EXCEPTION_MESSAGE;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_INCORRECT_INPUT;

//@@author teoziyiivy
@SuppressWarnings("ALL")

/**
 * This <code>Duke</code> class contains the backbone for running our entire application.
 */
public class Duke {
    private Meal meal;
    private Ui ui;
    private Fluid fluid;
    private ScheduleTracker scheduleTracker;
    private WorkoutTracker workoutTracker;
    private WeightTracker weightTracker;
    private CommandManager commandManager;
    private UserHelp userHelp;
    private FoodBank foodbank;
    private DateTracker dateTracker;
    private Storage storage;
    
    public static void main(String[] args) throws DukeException, FoodBankException, FluidExceptions {
        new Duke().run();
    }

    public Duke() throws DukeException, FoodBankException, FluidExceptions {
        try {
            meal = new Meal();
            ui = new Ui();
            fluid = new Fluid();
            scheduleTracker = new ScheduleTracker();
            workoutTracker = new WorkoutTracker();
            weightTracker = new WeightTracker();
            userHelp = new UserHelp();
            storage = new Storage();
            foodbank = new FoodBank();
            ui.welcomeMessage();
            ui.getInfo();
            if (ui.memoryStartup()) {
                meal.meals = storage.loadMeals();
                fluid.fluidArray = storage.loadFluids();
                weightTracker.weightsArray = storage.loadWeights();
                FoodBank.meals = storage.loadMealLibrary();
                FoodBank.fluids = storage.loadFluidLibrary();
                workoutTracker.workouts = storage.loadWorkouts();
                scheduleTracker.loadScheduleData();
                storage.printLoadedLists();
                System.out.println(ClickfitMessages.INITIAL_PROMPT);
            }
            commandManager = new CommandManager(storage, fluid,
                    meal, scheduleTracker, workoutTracker,
                    weightTracker, userHelp);
        } catch (LoadException e) {
            System.out.println(MEMORY_STARTUP_INCORRECT_INPUT);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION_MESSAGE);
        }
    }

    public void run() {
        while (!commandManager.isExit) {
            try {
                System.out.println(Ui.HORIZONTAL_BAR_LONG);
                System.out.print(Ui.USER_PROMPT);
                commandManager.commandChecker();
            } catch (ScheduleException se) {
                System.out.println(se.getMessage());
            } catch (WorkoutException we) {
                System.out.println(we.getMessage());
            } catch (MealException e) {
                System.out.println(e.getMessage());
            } catch (FluidExceptions e) {
                System.out.println(e.getMessage());
            } catch (FoodBankException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(ClickfitMessages.IO_EXCEPTION_MESSAGE);
            } catch (WeightException weighte) {
                System.out.println(weighte.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println(ClickfitMessages.DATE_TIME_ERROR);
            } catch (NumberFormatException e) {
                System.out.println(ClickfitMessages.NUMBER_ERROR);
            } catch (NullPointerException e) {
                System.out.println(ClickfitMessages.INCORRECT_INPUT);
            } catch (DukeException e) {
                System.out.println("I'm sorry");
            } catch (Exception e) {
                System.out.println(ClickfitMessages.UNKNOWN_ERROR_MESSAGE);
            }
        }
        LogManager.getLogManager().reset();
    }
}
