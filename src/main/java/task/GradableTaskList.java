package task;


import command.NoCap;
import command.VisualiseGradable;
import command.parser.DateParser;
import command.parser.Parser;
import command.Ui;
import exceptions.NoCapExceptions;
import command.Ui;
import command.parser.ParserChecks;


import java.net.StandardSocketOptions;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradableTaskList extends TaskList {
    public static final int MAXWEIGHTAGE = 100;
    public static final int MINWEIGHTAGE = 5;

    protected ArrayList<GradableTask> gradableTaskList;
    private static final Logger logger = command.Logger.myLogger();

    public GradableTaskList() {
        this.gradableTaskList = new ArrayList<>();
    }

    public ArrayList<GradableTask> getGradableTaskList() {
        return gradableTaskList;
    }

    protected static String getDate(String description) {
        try {
            int datePos = description.indexOf(ParserChecks.START_OF_DATE) + LENGTH_OF_MARKER;
            int weightagePos = description.indexOf(ParserChecks.START_OF_WEIGHTAGE);
            return description.substring(datePos, weightagePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    private static int getWeightage(String description) {
        try {
            int weightagePos = description.indexOf(ParserChecks.START_OF_WEIGHTAGE) + 2;
            int weightage = Integer.parseInt(description.substring(weightagePos).trim());
            if (weightage < MINWEIGHTAGE || weightage > MAXWEIGHTAGE) {
                throw new NoCapExceptions("wrong weightage");
            }
            return weightage;
        } catch (NumberFormatException | NoCapExceptions e) {
            Ui.wrongWeightage();
            return 0;
        }
    }

    private boolean checkTotalWeightage(int w) {
        int total = 0;
        for (GradableTask g : gradableTaskList) {
            total += g.getWeightage();
        }
        total += w;

        return total <= MAXWEIGHTAGE;
    }

    private boolean inputChecks(int weightage, String date, String description) {
        if (weightage == 0) {
            return false;
        }
        if (!checkTotalWeightage(weightage)) {
            Ui.wrongWeightageSplits();
            return false;
        } else if (date.isBlank()) {
            Ui.missingDate();
            return false;
        } else if (description.isBlank()) {
            Ui.missingDescription();
            return false;
        }
        return true;
    }

    /**
     * Method to add a gradableTask to GradableTaskList.
     * Input format is checked before calling the constructor for GradableTask.
     * Total weightage for all GradableTasks in GradableTaskList is checked. If total weightage is more than 100
     * an error message is shown.
     *
     * @param module    Name of module GradableTask is apart of
     * @param userInput Input of GradableTask data from User.
     */
    public void addGradableTask(String module, String userInput) {
        logger.log(Level.INFO, "Successfully added task");
        String date = getDate(userInput);
        int weightage = getWeightage(userInput);
        String description = removeDate(userInput);
        if (inputChecks(weightage, date, description)) {
            try {
                DateParser.parseDate(date);
                GradableTask newGradableTask = new GradableTask(description, date, weightage);
                this.gradableTaskList.add(taskCount, newGradableTask);
                this.taskCount = gradableTaskList.size();
                Ui.addTaskMessage(newGradableTask, module);
            } catch (DateTimeException e) {
                Ui.wrongDateTimeFormat();
            }
        }
    }

    public GradableTask getGradableTask(int index) {
        logger.log(Level.INFO, "Get GradableTask");
        return this.gradableTaskList.get(index);
    }

    public int size() {
        logger.log(Level.INFO, "Get size of task list");
        return this.gradableTaskList.size();
    }

    public GradableTask getGradableTaskFromIndex(String input) {
        int index;
        GradableTask g = null;
        try {
            index = Integer.parseInt(input) - 1;
            if (isValidIndex(index)) {
                g = gradableTaskList.get(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex();
        }
        return g;
    }

    boolean isValidIndex(int index) {
        if (index < 0) {
            Ui.printInvalidIndex();
            return false;
        }
        return true;
    }

    /**
     * Overrides toString() to show a formatted GradableTaskList when printed.
     *
     * @return String of formatted GradableTaskList
     */
    @Override
    public String toString() {
        int index = 1;
        String gradableTaskPrint = "";
        for (GradableTask g : gradableTaskList) {
            if (g != null) {
                gradableTaskPrint = gradableTaskPrint + String.valueOf(index) + " ";
                gradableTaskPrint = gradableTaskPrint + g.toString() + "\r\n";
                index++;
            }
        }
        return gradableTaskPrint;
    }

}
