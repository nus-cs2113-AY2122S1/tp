package seedu.module;

public class GradedModule extends BaseModule {

    private final String grade;
    private double equivalentCap;

    public GradedModule(Module module, String grade) {
        super(module.getModuleCode());
        this.grade = grade;
        this.moduleCredit = module.getModuleCredit();
        this.title = module.getTitle();
        this.description = module.getDescription();
    }

    public String getGrade() {
        return grade;
    }

    /* Returns the grade point corresponding to the grade passed in the arguments */
    public double getEquivalentCap(String grade) {
        switch (grade) {
        case "A+":
        case "A":
            return 5;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        case "F":
            return 0.0;
        default:
            return 0.0;
        }
    }

}
