package seedu.duke.commands.module;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ClickException;
import seedu.duke.exceptions.module.DuplicateModuleParamException;
import seedu.duke.exceptions.module.IllegalModularCreditException;
import seedu.duke.exceptions.module.IllegalModuleException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author nvbinh15

/**
 * A representation of the command for adding a Module.
 */
public class AddModuleCommand extends Command {
    public static final String MESSAGE_ADD_MODULE = "I have added this module:";
    public static final String PREFIX_CODE = "c/";
    public static final String PREFIX_NAME = "n/";
    public static final String PREFIX_MC = "m/";
    public static final String PREFIX_EXPECTED_GRADE = "e/";
    ModuleManager moduleManager = new ModuleManager();
    String commandArgs;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public AddModuleCommand() {
        syntax = "module add c/[MODULE_CODE] {n/[MODULE_NAME] m/[MODULAR_CREDITS] e/[EXPECTED_GRADE]}";
    }

    /**
     * Class constructor.
     *
     * @param commandArgs The command arguments.
     */
    public AddModuleCommand(String commandArgs) {
        this.commandArgs = commandArgs;
        this.helpMessage = "Add a module";
        this.syntax = "module add c/[MODULE_CODE] {n/[MODULE_NAME] m/[MODULAR_CREDITS] e/[EXPECTED_GRADE]}";
    }

    /**
     * Execute the AddModuleCommand.
     *
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws ClickException If there is an exception of type ClickException occurs.
     * @throws IOException If there is an error while saving data to file.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws ClickException, IOException {
        int indexOfCode = commandArgs.indexOf(PREFIX_CODE);
        int indexOfName = commandArgs.indexOf(PREFIX_NAME);
        int indexOfMc = commandArgs.indexOf(PREFIX_MC);
        int indexOfExpectedGrade = commandArgs.indexOf(PREFIX_EXPECTED_GRADE);
        if (hasDuplicatePrefix(commandArgs, indexOfCode, indexOfName, indexOfMc, indexOfExpectedGrade)) {
            throw new DuplicateModuleParamException();
        }
        Module module = getModule(indexOfCode, indexOfName, indexOfMc, indexOfExpectedGrade);
        moduleManager.addNewModule(module);
        ui.printLine();
        ui.printMessage(MESSAGE_ADD_MODULE);
        ui.printMessage(String.valueOf(module));
        ui.printLine();
    }

    /**
     * Checks if there is duplicate prefix in the user's command or not.
     *
     * @param commandArgs The command argument.
     * @param indexOfCode Index of Module code.
     * @param indexOfName Index of Module name.
     * @param indexOfMc Index of the modular credits.
     * @param indexOfExpectedGrade Index of Module expected grade.
     * @return true if there is duplicate prefix and false otherwise.
     */
    private boolean hasDuplicatePrefix(String commandArgs, int indexOfCode, int indexOfName, int indexOfMc,
                                       int indexOfExpectedGrade) {
        boolean duplicateCode = (indexOfCode != -1) && (indexOfCode != commandArgs.lastIndexOf(PREFIX_CODE));
        boolean duplicateName = (indexOfName != -1) && (indexOfName != commandArgs.lastIndexOf(PREFIX_NAME));
        boolean duplicateMC = (indexOfMc != -1) && (indexOfMc != commandArgs.lastIndexOf(PREFIX_MC));
        boolean duplicateExpectedGrade = (indexOfExpectedGrade != -1)
                && (indexOfExpectedGrade != commandArgs.lastIndexOf(PREFIX_EXPECTED_GRADE));
        if (duplicateCode || duplicateName || duplicateMC || duplicateExpectedGrade) {
            return true;
        }
        return false;
    }

    /**
     * Returns a new Module based on user's input.
     *
     * @param indexOfCode Index of Module code.
     * @param indexOfName Index of Module name.
     * @param indexOfMc Index of the modular credits.
     * @param indexOfExpectedGrade Index of Module expected grade.
     * @return A new Module based on user's input.
     * @throws IllegalModuleException If user's input is not in the correct format.
     * @throws IllegalModularCreditException If the modular credit is not an integer.
     */
    private Module getModule(int indexOfCode, int indexOfName, int indexOfMc, int indexOfExpectedGrade)
            throws IllegalModuleException, IllegalModularCreditException {
        String code;
        String name;
        int modularCredits;
        String expectedGrade;
        Module module;

        boolean isValidIndexes = isValidIndexes(indexOfCode, indexOfName, indexOfMc, indexOfExpectedGrade);

        if (!isValidIndexes) {
            throw new IllegalModuleException();
        }

        if (indexOfName == -1 && indexOfMc == -1 && indexOfExpectedGrade == -1) {
            code = commandArgs.substring(indexOfCode + 2).strip().toUpperCase();
            module = new Module(code);
        } else if (indexOfMc == -1 && indexOfExpectedGrade == -1) {
            code = commandArgs.substring(indexOfCode + 2, indexOfName).strip().toUpperCase();
            name = commandArgs.substring(indexOfName + 2).strip();
            module = new Module(code, name);
        } else if (indexOfExpectedGrade == -1) {
            code = commandArgs.substring(indexOfCode + 2, indexOfName).strip().toUpperCase();
            name = commandArgs.substring(indexOfName + 2, indexOfMc).strip();
            try {
                modularCredits = Integer.parseInt(commandArgs.substring(indexOfMc + 2).strip());
            } catch (Exception e) {
                throw new IllegalModularCreditException();
            }
            module = new Module(code, name, modularCredits);
        } else {
            code = commandArgs.substring(indexOfCode + 2, indexOfName).strip().toUpperCase();
            name = commandArgs.substring(indexOfName + 2, indexOfMc).strip();
            try {
                modularCredits = Integer.parseInt(commandArgs.substring(indexOfMc + 2, indexOfExpectedGrade).strip());
            } catch (Exception e) {
                throw new IllegalModularCreditException();
            }
            expectedGrade = commandArgs.substring(indexOfExpectedGrade + 2).strip().toUpperCase();
            module = new Module(code, name, modularCredits, expectedGrade);
        }

        return module;
    }

    /**
     * Checks if the indexes of the parameters from user's input is correct or not.
     *
     * @param indexOfCode Index of Module code.
     * @param indexOfName Index of Module name.
     * @param indexOfMc Index of the modular credits.
     * @param indexOfExpectedGrade Index of Module expected grade.
     * @return true if the indexes are valid, false otherwise.
     */
    private boolean isValidIndexes(int indexOfCode, int indexOfName, int indexOfMc, int indexOfExpectedGrade) {
        boolean hasCode = indexOfCode != -1;
        boolean isValidCodeOnly = (indexOfName == -1) && (indexOfMc == -1) && (indexOfExpectedGrade == -1);
        boolean isValidCodeAndName = (indexOfName != -1) && (indexOfMc == -1) && (indexOfExpectedGrade == -1)
                && (indexOfName > indexOfCode);
        boolean isValidCodeNameAndMc = (indexOfName != -1) && (indexOfMc != -1) && (indexOfExpectedGrade == -1)
                && (indexOfName > indexOfCode) && (indexOfMc > indexOfName);
        boolean isValidCodeNameMcAndGrade = (indexOfName != -1) && (indexOfMc != -1) && (indexOfExpectedGrade != -1)
                && (indexOfName > indexOfCode) && (indexOfMc > indexOfName) && (indexOfExpectedGrade > indexOfMc);

        boolean isValidIndexes = hasCode && (isValidCodeOnly || isValidCodeAndName
                || isValidCodeNameAndMc || isValidCodeNameMcAndGrade);
        return isValidIndexes;
    }
}
