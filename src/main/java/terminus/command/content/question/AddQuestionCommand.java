package terminus.command.content.question;

import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Question;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class AddQuestionCommand extends Command {
    
    private String question;
    private String answer;

    private static final int ADD_NOTE_ARGUMENTS = 2;

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_QUESTION_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    /**
     * Parses the arguments to the AddQuestionCommand object.
     * The arguments are attributes for a new Question object.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments are empty or missing.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        TerminusLogger.info("Parsing add question arguments");
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            TerminusLogger.warning("Failed to parse arguments: arguments is empty");
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        // Regex to find arguments
        ArrayList<String> argArray = CommonUtils.findArguments(arguments);
        if (!isValidQuestionsArgument(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        this.question = argArray.get(0);
        this.answer = argArray.get(1);
        TerminusLogger.info(String.format("Parsed argument (question = %s, answer = %s) to Add Question Command", 
            question, answer));
    }

    /**
     * Executes the add Question command.
     * Prints the relevant response to the Ui and a new Note will be added into the arraylist of Notes.
     *
     * @param ui The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     */
    public CommandResult execute(Ui ui, ModuleManager moduleManager) {
        assert getModuleName() != null;
        TerminusLogger.info("Executing Add Question Command");
        NusModule module = moduleManager.getModule(getModuleName());
        ContentManager<Question> contentManager = module.getContentManager(Question.class);
        assert contentManager != null;

        contentManager.add(new Question(question, answer));
        TerminusLogger.info(String.format("Question (\"%s\",\"%s\") has been added", question, answer));
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_QUESTION, question));
        return new CommandResult(true, false);
    }

    /**
     * Checks if arguments are non-empty and valid.
     *
     * @param argArray The command arguments in an array list.
     * @return True if the appropriate number of arguments are present, false otherwise.
     */
    private boolean isValidQuestionsArgument(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_NOTE_ARGUMENTS) {
            TerminusLogger.warning(String.format("Failed to find %d arguments: %d arguments found",
                ADD_NOTE_ARGUMENTS, argArray.size()));
            isValid = false;
        } else if (CommonUtils.hasEmptyString(argArray)) {
            TerminusLogger.warning("Failed to parse arguments: some arguments found is empty");
            isValid = false;
        }
        return isValid;
    }
}
