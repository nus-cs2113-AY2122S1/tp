package terminus.command.content.question;

import java.util.ArrayList;
import terminus.activerecall.GameEnvironment;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Question;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class TestCommand extends Command {

    private int questionCount;

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_TEST_QUESTION_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_TEST_QUESTION;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            this.questionCount = 10;
            return;
        }
        TerminusLogger.info("Parsing question test arguments");
        try {
            questionCount = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            TerminusLogger.warning(String.format("Failed to parse number of questions : %s", arguments));
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
        if (questionCount <= 0) {
            TerminusLogger.warning(String.format("Invalid number of questions : %d", questionCount));
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
    }

    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
        throws InvalidArgumentException, InvalidCommandException {
        assert getModuleName() != null;
        assert questionCount > 0;
        NusModule module = moduleManager.getModule(getModuleName());
        ContentManager<Question> contentManager = module.getContentManager(Question.class);
        ArrayList<Question> questions = contentManager.getContents();
        if (questions.isEmpty()) {
            throw new InvalidCommandException(Messages.NO_QUESTIONS_ERROR_MESSAGE);
        }

        new GameEnvironment(ui, questions, questionCount).run(); 
        
        return new CommandResult(true);
    }
    
}
