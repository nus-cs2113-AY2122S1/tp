package seedu.module;

public class GradedModule extends BaseModule {

    private final char grade;
    private double equivalentCap;

    public GradedModule(Module module, char grade) {
        super(module.getModuleCode());
        this.grade = grade;
        this.moduleCredit = module.getModuleCredit();
        this.title = module.getTitle();
        this.description = module.getDescription();
    }

    private double getEquivalentCap() {
        // TODO set equivalent CAP (e.g. A = 5.0) for grade
        return 0;
    }

}
