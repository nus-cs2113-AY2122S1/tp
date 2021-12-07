package seedu.duke.module;

import seedu.duke.exceptions.module.DuplicateModuleException;
import seedu.duke.exceptions.module.IllegalExpectedGradeException;
import seedu.duke.exceptions.module.IllegalModularCreditException;
import seedu.duke.exceptions.module.IllegalModuleIndexException;
import seedu.duke.parser.module.ParserModule;
import seedu.duke.storage.StorageModule;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

//@@author nvbinh15

/**
 * A class that deals with the operations related to Modules.
 */
public class ModuleManager {
    public static final String MESSAGE_DELETE_MODULE = "I have deleted this module:";

    private static Ui ui = new Ui();
    private static GradePoints gradePoints = new GradePoints();
    private static StorageModule storageModule = new StorageModule();
    private static double currentCap = 0.0;
    private static int totalMcTaken = 0;

    /**
     * Default constructor.
     */
    public ModuleManager() {

    }

    /**
     * Adds a new module to the ModuleList.
     *
     * @param module The Module to be added.
     * @throws IOException If there is an exceptions when reading or writing data to file.
     * @throws IllegalExpectedGradeException If the expected grade of the Module is illegal.
     * @throws IllegalModularCreditException If the modular credit of Module is illegal.
     * @throws DuplicateModuleException If the module is already in the module list.
     */
    public void addNewModule(Module module) throws IOException, IllegalExpectedGradeException,
            IllegalModularCreditException, DuplicateModuleException {
        ModuleList moduleList = storageModule.readModulesFromFile();
        String expectedGrade = module.getExpectedGrade();
        if (!gradePoints.isValidGrade(expectedGrade)) {
            throw new IllegalExpectedGradeException();
        }
        int modularCredits = module.getModularCredits();
        if (modularCredits < 0) {
            throw new IllegalModularCreditException();
        }
        if (moduleList.exist(module)) {
            throw new DuplicateModuleException();
        }
        moduleList.addModule(module);
        storageModule.saveModuleToFile(moduleList);
    }

    /**
     * Deletes a module from the ModuleList.
     *
     * @param moduleIndex Index of the Module to be deleted.
     * @throws IOException If there is an exceptions when reading or writing data to file.
     * @throws IllegalModuleIndexException If the index of the Module is illegal.
     */
    public void deleteModule(int moduleIndex) throws IOException, IllegalModuleIndexException {
        ModuleList moduleList = storageModule.readModulesFromFile();
        boolean isValidIndex = (moduleIndex >= 0) && (moduleIndex < moduleList.getNumberOfModules());
        if (!isValidIndex) {
            throw new IllegalModuleIndexException();
        }
        ui.printLine();
        ui.printMessage(MESSAGE_DELETE_MODULE);
        ui.printMessage(String.valueOf(moduleList.getModuleByIndex(moduleIndex)));
        ui.printLine();
        moduleList.removeModuleByIndex(moduleIndex);
        storageModule.saveModuleToFile(moduleList);
    }

    public static void setCapInfo(double cap, int mc) throws IOException {
        currentCap = cap;
        totalMcTaken = mc;
        storageModule.saveCapInfoToFile(cap, mc);
    }

    public double getCurrentCap() {
        return currentCap;
    }

    public int getTotalMcTaken() {
        return totalMcTaken;
    }

    /**
     * Returns the expected Cumulative Average Point (CAP).
     *
     * @return The expected CAP.
     * @throws IOException If there is an exceptions when reading data to file.
     */
    public double getExpectedCap() throws IOException {
        ArrayList<Double> capAndMc = storageModule.readCapInfoFromFile();
        assert capAndMc.size() == 2;
        currentCap = capAndMc.get(0);
        totalMcTaken = capAndMc.get(1).intValue();
        double totalPoints = currentCap * totalMcTaken;
        int totalModularCredits = totalMcTaken;
        ModuleList moduleList = storageModule.readModulesFromFile();
        for (Module module : moduleList.getModules()) {
            double point = gradePoints.getPoint(module.getExpectedGrade());
            if (point != -1) {
                int credits = module.getModularCredits();
                totalPoints += point * credits;
                totalModularCredits += credits;
            }
        }
        return totalPoints / totalModularCredits;
    }

}
