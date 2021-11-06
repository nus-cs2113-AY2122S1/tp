package seedu.command;


import seedu.exceptions.UniModsException;
import seedu.unimods.UniMods;
import seedu.exceptions.FetchException;
import seedu.module.GradedModule;
import seedu.module.Module;
import seedu.module.UngradedModule;
import seedu.online.NusMods;
import seedu.ui.TextUi;
import seedu.user.Profile;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreResultsCommand extends Command {

    public static final String commandSyntax = "store <GRADE> <MODULE_CODE>";
    public static final String commandAction =
            "Assigns a grade to a module and stores it on record ";

    private final String moduleCode;
    private final String grade;
    private final String gradeType;
    private boolean isErrorThrown;
    private static final Logger logger = Logger.getLogger("");

    public StoreResultsCommand(String grade, String moduleCode, String gradeType, boolean isErrorThrown) {
        this.grade = grade;
        this.moduleCode = moduleCode;
        this.gradeType = gradeType;
        this.isErrorThrown = isErrorThrown;
    }

    public void execute() {
        Module module;
        try {
            if (isErrorThrown) {
                isErrorThrown = false;
                return;
            }
            module = NusMods.fetchModOnline(moduleCode);
            Profile currentProfile = UniMods.getProfileInUse();
            if (gradeType.equals(TextUi.GRADED)) {
                GradedModule grModule = new GradedModule(module, grade);
                currentProfile.getRecord().addModuleToRecord(grModule);
            } else if (gradeType.equals(TextUi.UNGRADED)){
                UngradedModule ugModule = new UngradedModule(module, grade);
                currentProfile.getRecord().addModuleToRecord(ugModule);
            }else{
                System.out.print("INVALID GRADE");
            }
        } catch (FetchException e) {
            System.out.println(TextUi.ERROR_INVALID_MODULE_CODE);
            logger.log(Level.WARNING, "Attempt to store a module grade has failed");
        }
        catch (UniModsException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
