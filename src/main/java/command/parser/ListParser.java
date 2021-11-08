package command.parser;

import command.NoCap;
import command.Ui;
import module.Module;
import task.OverallTaskList;
import task.TaskList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class containing parser methods that specifically handles list methods.
 */
public class ListParser {
    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";
    public static final String SHOW_GRADABLE = "gradable";
    public static final String SHOW_NORMAL = "normal";
    private static final Logger logger = command.Logger.myLogger();

    public ListParser() {
    }

    private boolean isEmpty(Module module) {
        if (module.taskList.size() == 0) {
            Ui.printEmptyTaskList(module.getModuleName());
            return true;
        }
        return false;
    }

    private void sortByDate(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.sortTaskListByDate(module.getModuleName());
        }
    }

    private void sortByStatus(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.sortTaskListByStatus(module.getModuleName());
        }
    }

    private void listWeekly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllWeekly(module.getModuleName());
        }
    }

    private void listAll(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            Ui.printTaskList(module.getModuleName(), list.getTaskCount());
            list.printTasks(list.getTaskList());
        }
    }

    private void listMonthly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllMonthly(module.getModuleName());
        }
    }

    private void listYearly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllYearly(module.getModuleName());
        }
    }

    /**
     * Parser method for listing outside of modules.
     * Check if taskType is MODULE, SEMESTER or TASK. If not, print error message.
     * If taskType is Task, taskDescription is checked for optional sorting methods. If none matches,
     * a default listing method is used.
     *
     * @param taskType string indicating type of listing.
     * @param taskDescription string indicating sorting methods if taskType is task. Ignored otherwise.
     */
    public void overallListParser(String taskType, String taskDescription) {
        switch (taskType) {
        case Parser.MODULE:
            if (NoCap.moduleList.size() == 0) {
                Ui.emptyModuleListMessage();
            } else {
                NoCap.moduleList.printModules();
            }
            break;
        case Parser.SEMESTERS:
            NoCap.semesterList.printSemesters();
            break;
        case Parser.TASK:
            OverallTaskList allTaskList = new OverallTaskList(NoCap.moduleList);
            switch (taskDescription) {
            case SORT_BY_DATE:
                logger.log(Level.INFO, "Sort TaskList by date");
                allTaskList.sortByDateAndPrint();
                break;
            case SORT_BY_STATUS:
                logger.log(Level.INFO, "Sort TaskList by status");
                allTaskList.sortByStatusAndPrint();
                break;
            case SHOW_WEEK:
                logger.log(Level.INFO, "Print weekly TaskList");
                allTaskList.printWeeklyTasks();
                break;
            case SHOW_MONTH:
                logger.log(Level.INFO, "Print monthly TaskList");
                allTaskList.printMonthlyTasks();
                break;
            case SHOW_YEAR:
                allTaskList.printYearlyTasks();
                logger.log(Level.INFO, "Print yearly TaskList");
                break;
            case SHOW_GRADABLE:
                allTaskList.printGradableTasks();
                logger.log(Level.INFO, "Print gradable TaskList");
                break;
            case SHOW_NORMAL:
                allTaskList.printNormalTasks();
                logger.log(Level.INFO, "Print normal TaskList");
                break;
            default:
                allTaskList.printAllTasks();
                break;
            }
            break;
        default:
            Ui.printInvalidListFormat();
            break;
        }
    }

    /**
     * Parser method for listing tasks within a module.
     * if input matches a specific sorting method, then a specified list is printed. If not,
     * a default listing method is used.
     *
     * @param module the module in which the tasks are being accessed.
     * @param input string indicating sorting methods. Default sorting is used if sorting method stated is invalid.
     */
    public void moduleListParser(Module module, String input) {
        if (input.isBlank()) {
            listAll(module);
            return;
        }
        switch (input) {
        case SORT_BY_DATE:
            sortByDate(module);
            break;
        case SORT_BY_STATUS:
            sortByStatus(module);
            break;
        case SHOW_WEEK:
            listWeekly(module);
            break;
        case SHOW_MONTH:
            listMonthly(module);
            break;
        case SHOW_YEAR:
            listYearly(module);
            break;
        case SHOW_GRADABLE:
            Ui.visualiseGradableTask(module.getGradableTaskList());
            break;
        default:
            Ui.wrongListFormat();
            break;
        }
    }
}