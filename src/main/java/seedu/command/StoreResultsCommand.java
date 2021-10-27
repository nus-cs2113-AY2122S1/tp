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

    public static final String commandSyntax = "store <GRADE> <MODULE_CODE>";
    public static final String commandAction =
            "Assigns a grade to a module and stores it on record ";

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
                GradedModule grModule = new GradedModule(module, grade);
                currentProfile.getRecord().addModuleToRecord(grModule);
            } else {
                UngradedModule ugModule = new UngradedModule(module, grade);
                currentProfile.getRecord().addModuleToRecord(ugModule);
            }
        } catch (FetchException e) {
            System.out.println(TextUi.ERROR_INVALID_MODULE_CODE);
        }
    }
}
