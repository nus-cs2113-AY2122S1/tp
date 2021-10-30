package terminus.storage;

import java.util.ArrayList;
import terminus.activerecall.DifficultyModifier;
import terminus.command.content.link.AddLinkCommand;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Question;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class FilterManager {

    public FilterManager() {
    }

    public void filter(ModuleManager moduleManager) {
        assert moduleManager != null;
        String[] listOfModules = moduleManager.getAllModules();
        if (listOfModules == null) {
            return;
        }
        for (String module : listOfModules) {
            if (!isModuleValid(module)) {
                moduleManager.removeModule(module);
            } else {
                NusModule moduleObject = moduleManager.getModule(module);
                ContentManager<Link> linkContentManager = moduleObject.getContentManager(Link.class);
                filterLink(linkContentManager);
                ContentManager<Question> questionContentManager = moduleObject.getContentManager(Question.class);
                filterQuestion(questionContentManager);
            }
        }
    }

    private void filterLink(ContentManager<Link> linkContentManager) {
        if (linkContentManager == null) {
            return;
        }
        ArrayList<Link> linkArrayList = linkContentManager.getContents();
        for (Link link : linkArrayList) {
            if (!isScheduleValid(link)) {
                linkArrayList.remove(link);
            }
        }
    }

    private void filterQuestion(ContentManager<Question> questionContentManager) {
        if (questionContentManager == null) {
            return;
        }
        ArrayList<Question> questionArrayList = questionContentManager.getContents();
        for (Question question : questionArrayList) {
            if (!isQuestionValid(question)) {
                questionArrayList.remove(question);
            }
        }
    }

    private boolean isModuleValid(String module) {
        boolean isValid = true;
        if (CommonUtils.isStringNullOrEmpty(module)) {
            isValid = false;
        } else if (!CommonUtils.isValidFileName(module)) {
            isValid = false;
        } else if (!module.matches(CommonFormat.SPACE_NEGATED_DELIMITER)) {
            isValid = false;
        } else if (!module.equals(module.toUpperCase())) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isQuestionValid(Question question) {
        boolean isValid = true;
        if (question == null) {
            isValid = false;
        } else if (CommonUtils.isStringNullOrEmpty(question.getAnswer())) {
            isValid = false;
        } else if (CommonUtils.isStringNullOrEmpty(question.getQuestion())) {
            isValid = false;
        } else if (question.getWeight() < DifficultyModifier.MIN_VALUE
                || question.getWeight() > DifficultyModifier.MAX_VALUE) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isScheduleValid(Link link) {
        try {
            ArrayList<String> arguments = new ArrayList<>();
            arguments.add(link.getName());
            arguments.add(link.getDay());
            arguments.add(link.getStartTime().toString());
            arguments.add(String.valueOf(link.getDuration()));
            arguments.add(link.getLink());

            AddLinkCommand addLinkCommand = new AddLinkCommand();
            addLinkCommand.parseArguments(concatStringArguments(arguments));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String concatStringArguments(ArrayList<String> stringArrayList) {
        String result = "";
        for (String string : stringArrayList) {
            if (string == null) {
                return "";
            }
            result += "\"" + string + "\"";
        }
        return result;
    }


}
