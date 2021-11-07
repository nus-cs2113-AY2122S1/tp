package terminus.command.content;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.parser.QuestionCommandParser;

public class QuestionCommand extends InnerModuleCommand {

    public QuestionCommand() {
        super(QuestionCommandParser.getInstance());
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_QUESTION;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_QUESTION;
    }
}
