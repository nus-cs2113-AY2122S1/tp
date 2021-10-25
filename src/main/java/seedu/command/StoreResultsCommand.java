package seedu.command;


import seedu.unimods.UniMods;
import seedu.exceptions.FetchException;
import seedu.module.GradedModule;
import seedu.module.Module;
import seedu.module.UngradedModule;
import seedu.online.NusMods;
import seedu.ui.TextUi;
import seedu.user.Profile;

public class StoreResultsCommand extends Command {
    private String moduleCode;
    private String grade;
    private String gradeType;
    private boolean isErrorThrown;

    public StoreResultsCommand(String grade, String moduleCode, String gradeType, boolean isErrorThrown) {
        this.grade = grade;
        this.moduleCode = moduleCode;
        this.gradeType = gradeType;
        this.isErrorThrown = isErrorThrown;
    }

    public void execute() {
        Module module = null;
        try {
            if (isErrorThrown) {
                return;
            }
            module = NusMods.fetchModOnline(moduleCode);
            Profile currentProfile = UniMods.getProfileInUse();
            if (gradeType.equals(TextUi.GRADED)) {
                currentProfile.getRecord().addModuleToRecord(new GradedModule(module, grade));
            } else {
                currentProfile.getRecord().addModuleToRecord(new UngradedModule(module, grade));
            }
        } catch (FetchException e) {
            System.out.println(TextUi.ERROR_INVALID_MODULE_CODE);
        }
    }
}
