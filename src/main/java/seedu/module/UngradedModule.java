package seedu.module;

public class UngradedModule extends BaseModule {
    private final String grade;

    public UngradedModule(Module module, String grade) {
        super(module.getModuleCode());
        this.grade = grade;
        this.moduleCredit = module.getModuleCredit();
        this.title = module.getTitle();
        this.description = module.getDescription();
    }

    public String getGrade() {
        return grade;
    }

}

