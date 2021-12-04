package seedu.duke.logic;

import seedu.duke.data.DataManager;
import seedu.duke.logic.commands.ByeCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandResult;
import seedu.duke.logic.parser.ParserManager;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

//@@author tlyi

/**
 * Handles the parsing and execution of all commands.
 */
public class LogicManager {
    final ParserManager parserManager;
    final StorageManager storageManager;
    final DataManager dataManager;

    public LogicManager(StorageManager storageManager, DataManager dataManager) {
        this.parserManager = new ParserManager();
        this.storageManager = storageManager;
        this.dataManager = dataManager;
    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param userInput Raw user input
     * @return CommandResult representing result of execution of the command
     */
    public CommandResult execute(String userInput) {
        final Command command = parserManager.parseCommand(userInput);
        command.setData(dataManager);
        final CommandResult result = command.execute();
        try {
            if (ByeCommand.isBye(command)) {
                storageManager.saveAll(dataManager);
            }
            if (Command.requiresProfileStorageRewrite(command)) {
                storageManager.saveProfile(dataManager.getProfile());
            }
            if (Command.requiresExerciseListStorageRewrite(command)) {
                dataManager.getExerciseItems().addAll(dataManager.getFilteredExerciseItems());
                storageManager.saveExerciseList(dataManager.getExerciseItems());
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                dataManager.getFoodItems().addAll(dataManager.getFilteredFoodItems());
                storageManager.saveFoodList(dataManager.getFoodItems());
            }
            if (Command.requiresFutureExerciseListStorageRewrite(command)) {
                storageManager.saveFutureExerciseList(dataManager.getFutureExerciseItems());
            }
            if (Command.requiresFoodBankStorageRewrite(command)) {
                storageManager.saveFoodBank(dataManager.getFoodBank());
            }
            if (Command.requiresExerciseBankStorageRewrite(command)) {
                storageManager.saveExerciseBank(dataManager.getExerciseBank());
            }
        } catch (UnableToWriteFileException e) {
            return new CommandResult(e.getMessage());
        }
        return result;
    }


}
