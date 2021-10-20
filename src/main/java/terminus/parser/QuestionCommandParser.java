package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.content.DeleteCommand;
import terminus.command.content.ViewCommand;
import terminus.command.content.question.AddQuestionCommand;
import terminus.command.content.question.TestCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.Question;
import terminus.module.ModuleManager;

public class QuestionCommandParser extends InnerModuleCommandParser {
    
    public QuestionCommandParser() {
        super(CommonFormat.COMMAND_QUESTION);
    }

    public static QuestionCommandParser getInstance() {
        QuestionCommandParser parser = new QuestionCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_ADD, new AddQuestionCommand());
        parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand<>(Question.class));
        parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand<>(Question.class));
        parser.addCommand(CommonFormat.COMMAND_TEST, new TestCommand());
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return String.format(Messages.QUESTION_BANNER,
            moduleManager.getModule(getModuleName()).getContentManager(Question.class).getContents().size());
    }
}
