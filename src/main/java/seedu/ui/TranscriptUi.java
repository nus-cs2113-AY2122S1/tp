package seedu.ui;

import seedu.module.GradedModule;
import seedu.module.UngradedModule;
import seedu.unimods.UniMods;
import seedu.user.Profile;

import java.time.LocalDateTime;

public class TranscriptUi {
    private static final String CENTER_ALIGN_SPACE = String.format("%32s","");
    private static final String UniName = CENTER_ALIGN_SPACE + "--\tNational University of Singapore\t--";
    private static final String transcriptName = CENTER_ALIGN_SPACE + "\t--\tUnofficial Transcript\t--";
    private static final String notice = "This is not an official transcript issued by the Office of the Registrar.";
    private static LocalDateTime now;
    private static String divider = "\n" + CENTER_ALIGN_SPACE + "\t------------------------------";
    private static final String SEPARATION_SPACE = "  ";
    private static final String DELIMITER_SPACE = " ";
    private static final String HEADING_GRADE = "GRADE";
    private static final String HEADING_MODULE = "MODULE";
    private static final String HEADING_CREDITS = "CREDITS";
    public static double mcsCompleted = 0;
    private static final String MODULE_CODE_LENGTH = "%-10.10s";
    private static final String MODULE_TITLE_LENGTH = "%-70.70s";
    private static final String MODULE_GRADE_LENGTH = "%-5.5s";
    private static final String MODULE_CREDITS_LENGTH = "%-7.7s";
    private static final String MODULE_SEPARATION_LENGTH = "%-2.2s";
    private static final String NEXT_LINE = "\n";


    public static void printIntroduction() {
        System.out.println(UniName);
        System.out.println(transcriptName + divider);
        now = LocalDateTime.now();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int year = now.getYear();
        String dateOfIssuance = day + "-" + month + "-" + year;
        System.out.println("\nDate Issued : " + dateOfIssuance + "\n");
    }

    public static void printHeadings() {
        System.out.printf(MODULE_CODE_LENGTH, HEADING_MODULE);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_TITLE_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_GRADE_LENGTH, HEADING_GRADE);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf((MODULE_CREDITS_LENGTH) + "%n", HEADING_CREDITS);
        System.out.print(NEXT_LINE);
    }

    public static void printGradedModules(GradedModule module) {

        String moduleCode = module.getModuleCode();
        String moduleTitle = module.getTitle();
        String moduleGrade = SEPARATION_SPACE + module.getGrade();
        double moduleCredits = module.getModuleCredit();
        System.out.printf(MODULE_CODE_LENGTH, moduleCode);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_TITLE_LENGTH, moduleTitle);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_GRADE_LENGTH, moduleGrade);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf((MODULE_CREDITS_LENGTH) + "%n", DELIMITER_SPACE + moduleCredits);
        mcsCompleted = mcsCompleted + moduleCredits;
    }

    public static void printUngradedModules(UngradedModule module) {
        String moduleCode = module.getModuleCode();
        String moduleTitle = module.getTitle();
        String moduleGrade = SEPARATION_SPACE + module.getGrade();
        double moduleCredits = module.getModuleCredit();
        System.out.printf(MODULE_CODE_LENGTH, moduleCode);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_TITLE_LENGTH, moduleTitle);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf(MODULE_GRADE_LENGTH, moduleGrade);
        System.out.printf(MODULE_SEPARATION_LENGTH, DELIMITER_SPACE);
        System.out.printf((MODULE_CREDITS_LENGTH) + "%n", DELIMITER_SPACE + moduleCredits);
        mcsCompleted = mcsCompleted + moduleCredits;
    }


    public static void printConclusion() {
        System.out.println("\nTotal Credits Fulfilled : " + mcsCompleted);
        mcsCompleted=0;
        System.out.println();
        Profile currentProfile = UniMods.getProfileInUse();
        TextUi.printCap(currentProfile.getCap());
        System.out.println("\n\t" + notice);
    }
}
