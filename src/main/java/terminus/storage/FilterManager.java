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

/**
 * FilterManager class handles the filtration of the ModuleManager from json file.
 */
public class FilterManager {

    /**
     * Filters the data in ModuleManager.
     *
     * @param moduleManager The data from the main json used throughout TermiNUS.
     */
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

    /**
     * Filters the link in link content manager.
     *
     * @param linkContentManager The link content manager storing the list of links from a module.
     */
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

    /**
     * Filters the question in question content manager.
     *
     * @param questionContentManager The question content manager storing the list of questions from a module.
     */
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

    /**
     * Checks if given Module name is valid for TermiNUS.
     *
     * @param module The module name to be tested on.
     * @return True if given module name is valid, false otherwise.
     */
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

    /**
     * Checks if given QUestion object is valid for TermiNUS.
     *
     * @param question The given question to be tested on.
     * @return True if given question object is valid, false otherwise.
     */
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

    /**
     * Checks if given Schedule object is valid for TermiNUS.
     *
     * @param link The given Schedule to be tested on.
     * @return True if given schedule object is valid, false otherwise.
     */
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

    /**
     * Joins all elements in a given array list with double quotes.
     *
     * @param stringArrayList The given arraylist to be joined.
     * @return A string joined from the given arraylist.
     */
    private String concatStringArguments(ArrayList<String> stringArrayList) {
        String result = "";
        for (String string : stringArrayList) {
            if (string == null) {
                return "";
            }
            result += "\"" + string + "\" ";
        }
        return result;
    }


}
